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
import com.ollum.ecoCrats.Fragments.MessageDetailsFragment;
import com.ollum.ecoCrats.Classes.Message;
import com.ollum.ecoCrats.R;

import java.util.ArrayList;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.RecyclerViewHolder> {
    ArrayList<Message> arrayList = new ArrayList<>();
    Context ctx;
    public static Bundle bundle;

    public MessagesAdapter(ArrayList<Message> arrayList, Context ctx) {
        this.arrayList = arrayList;
        this.ctx = ctx;
    }

    @Override
    public MessagesAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_messages_row, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view, ctx, arrayList);
        return recyclerViewHolder;
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

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView sender, subject, time;
        ArrayList<Message> messages = new ArrayList<Message>();
        Context ctx;

        public RecyclerViewHolder(View view, Context ctx, ArrayList<Message> messages) {
            super(view);
            this.messages = messages;
            this.ctx = ctx;
            view.setOnClickListener(this);

            sender = (TextView) view.findViewById(R.id.display_messages_row_sender);
            subject = (TextView) view.findViewById(R.id.display_messages_row_subject);
            time = (TextView) view.findViewById(R.id.display_messages_row_time);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Message message = this.messages.get(position);

            bundle = new Bundle();
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
