package com.ollum.ecoCrats.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ollum.ecoCrats.Activities.MainActivity;
import com.ollum.ecoCrats.Classes.Message;
import com.ollum.ecoCrats.Fragments.MessageDetailsFragment;
import com.ollum.ecoCrats.R;

import java.util.ArrayList;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.RecyclerViewHolder> {
    public static final int TYPE_UNREAD = 0;
    public static final int TYPE_READ = 1;
    public static Bundle bundle;
    ArrayList<Message> arrayList = new ArrayList<>();
    Context ctx;

    public MessagesAdapter(ArrayList<Message> arrayList, Context ctx) {
        this.arrayList = arrayList;
        this.ctx = ctx;
    }

    @Override
    public MessagesAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_UNREAD) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_messages_row_unread, parent, false);
            RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view, ctx, arrayList, viewType);
            return recyclerViewHolder;
        } else if (viewType == TYPE_READ) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_messages_row_read, parent, false);
            RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view, ctx, arrayList, viewType);
            return recyclerViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(MessagesAdapter.RecyclerViewHolder holder, int position) {
        Message message = arrayList.get(position);

        holder.sender.setText(message.getSender());
        holder.subject.setText(message.getSubject());
        holder.time.setText(message.getTime());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        Message message = arrayList.get(position);
        if (message.getSeen() == 0) {
            return TYPE_UNREAD;
        } else if (message.getSeen() == 1) {
            return TYPE_READ;
        }
        return 1;
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView sender, subject, time;
        ArrayList<Message> messages = new ArrayList<Message>();
        int viewType;
        Context ctx;

        public RecyclerViewHolder(View view, Context ctx, ArrayList<Message> messages, int viewType) {
            super(view);
            this.messages = messages;
            this.ctx = ctx;
            view.setOnClickListener(this);

            if (viewType == TYPE_READ) {
                sender = (TextView) view.findViewById(R.id.display_messages_row_read_sender);
                subject = (TextView) view.findViewById(R.id.display_messages_row_read_subject);
                time = (TextView) view.findViewById(R.id.display_messages_row_read_time);
                this.viewType = TYPE_READ;
            } else if (viewType == TYPE_UNREAD) {
                sender = (TextView) view.findViewById(R.id.display_messages_row_unread_sender);
                subject = (TextView) view.findViewById(R.id.display_messages_row_unread_subject);
                time = (TextView) view.findViewById(R.id.display_messages_row_unread_time);
                this.viewType = TYPE_UNREAD;
            }
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Message message = this.messages.get(position);

            bundle = new Bundle();
            bundle.putInt("ID", message.getID());
            bundle.putString("sender", message.getSender());
            bundle.putString("subject", message.getSubject());
            bundle.putString("message", message.getMessage());
            bundle.putString("time", message.getTime());

            MessageDetailsFragment messageDetailsFragment = new MessageDetailsFragment();
            messageDetailsFragment.setArguments(bundle);
            FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
            transaction.replace(R.id.mainContent, messageDetailsFragment, "MessageDetailsFragment");
            transaction.addToBackStack("MessageDetailsFragment");
            transaction.commit();
        }


    }


}
