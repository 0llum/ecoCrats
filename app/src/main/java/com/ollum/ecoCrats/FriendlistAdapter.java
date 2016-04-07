package com.ollum.ecoCrats;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FriendlistAdapter extends RecyclerView.Adapter<FriendlistAdapter.RecyclerViewHolder> {
    ArrayList<User> arrayList = new ArrayList<>();
    Context ctx;
    public static Bundle bundle;

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
            holder.status.setImageResource(R.drawable.offline);
        } else if (user.getStatus() == 1) {
            holder.status.setImageResource(R.drawable.online);
        } else if (user.getStatus() == 2) {
            holder.status.setImageResource(R.drawable.afk);
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
            User user = this.users.get(position);

            bundle = new Bundle();
            bundle.putString("receiver", user.getUsername());

            NewMessageFragment newMessageFragment = new NewMessageFragment();
            newMessageFragment.setArguments(bundle);
            FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
            transaction.replace(R.id.mainContent, newMessageFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
