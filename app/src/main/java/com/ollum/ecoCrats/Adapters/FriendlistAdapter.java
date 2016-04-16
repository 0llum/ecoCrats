package com.ollum.ecoCrats.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ollum.ecoCrats.Activities.MainActivity;
import com.ollum.ecoCrats.BackgroundTasks.BackgroundTask;
import com.ollum.ecoCrats.Classes.User;
import com.ollum.ecoCrats.Fragments.NewMessageFragment;
import com.ollum.ecoCrats.R;

import java.util.ArrayList;

public class FriendlistAdapter extends RecyclerView.Adapter<FriendlistAdapter.RecyclerViewHolder> {
    public static Bundle bundle;
    ArrayList<User> arrayList = new ArrayList<>();
    Context ctx;

    public FriendlistAdapter(ArrayList<User> arrayList, Context ctx) {
        this.arrayList = arrayList;
        this.ctx = ctx;
    }

    @Override
    public FriendlistAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_friends_row, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view, ctx, arrayList);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(FriendlistAdapter.RecyclerViewHolder holder, int position) {
        User user = arrayList.get(position);
        if (user.getStatus() == 0) {
            holder.status.setImageResource(R.mipmap.offline);
        } else if (user.getStatus() == 1) {
            holder.status.setImageResource(R.mipmap.online);
        } else if (user.getStatus() == 2) {
            holder.status.setImageResource(R.mipmap.afk);
        }
        holder.username.setText(user.getUsername());
        holder.lastOnline.setText(user.getLastOnline());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView username, lastOnline;
        ImageView status;
        ArrayList<User> users = new ArrayList<User>();
        Context ctx;

        public RecyclerViewHolder(View view, Context ctx, ArrayList<User> users) {
            super(view);
            this.users = users;
            this.ctx = ctx;
            view.setOnClickListener(this);

            status = (ImageView) view.findViewById(R.id.display_friends_row_status);
            username = (TextView) view.findViewById(R.id.display_friends_row_username);
            lastOnline = (TextView) view.findViewById(R.id.display_friends_row_last_online);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            final User user = this.users.get(position);

            AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
            dialog.setItems(R.array.friendlist_options, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case 0:
                            bundle = new Bundle();
                            bundle.putString("receiver", user.getUsername());

                            NewMessageFragment newMessageFragment = new NewMessageFragment();
                            newMessageFragment.setArguments(bundle);
                            FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                            transaction.replace(R.id.mainContent, newMessageFragment, "NewMessageFragment");
                            transaction.addToBackStack("NewMessageFragment");
                            transaction.commit();
                            break;
                        case 1:
                            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(ctx);
                            alertDialog.setTitle(R.string.select_ecos);
                            final EditText input = new EditText(ctx);
                            input.setInputType(InputType.TYPE_CLASS_NUMBER);
                            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.MATCH_PARENT);
                            input.setLayoutParams(lp);
                            alertDialog.setView(input);
                            alertDialog.setPositiveButton(R.string.send, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String method = "sendECOs";
                                    BackgroundTask backgroundTask = new BackgroundTask(ctx);
                                    backgroundTask.execute(method, MainActivity.user.getUsername(), user.getUsername(), input.getText().toString());
                                }
                            });
                            alertDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            alertDialog.create();
                            alertDialog.show();
                            break;
                        case 2:
                            String method = "removeFriend";
                            BackgroundTask backgroundTask = new BackgroundTask(ctx);
                            backgroundTask.execute(method, MainActivity.user.username, user.getUsername());
                            break;
                    }
                }
            });
            dialog.create();
            dialog.show();


        }
    }
}
