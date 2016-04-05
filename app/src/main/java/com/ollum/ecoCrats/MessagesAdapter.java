package com.ollum.ecoCrats;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.RecyclerViewHolder> {
    ArrayList<Message> arrayList = new ArrayList<>();
    Context ctx;

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

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
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
            Intent intent = new Intent(this.ctx, MessageDetails.class);
            intent.putExtra("sender", message.getSender());
            intent.putExtra("subject", message.getSubject());
            intent.putExtra("message", message.getMessage());
            intent.putExtra("time", message.getTime());
            this.ctx.startActivity(intent);
            ((Activity) ctx).overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
        }
    }
    
}
