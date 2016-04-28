package com.ollum.ecoCrats.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ollum.ecoCrats.Classes.Transport;
import com.ollum.ecoCrats.R;

import java.util.ArrayList;

public class ActiveTransportAdapter extends RecyclerView.Adapter<ActiveTransportAdapter.RecyclerViewHolder> {
    public static Bundle bundle;
    ArrayList<Transport> arrayList = new ArrayList<>();
    Context ctx;

    public ActiveTransportAdapter(ArrayList<Transport> arrayList, Context ctx) {
        this.arrayList = arrayList;
        this.ctx = ctx;
    }

    @Override
    public ActiveTransportAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_active_transport_row, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view, ctx, arrayList);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(ActiveTransportAdapter.RecyclerViewHolder holder, int position) {
        Transport transport = arrayList.get(position);
        holder.startRegion.setText(transport.getStartRegion());
        holder.destinationRegion.setText(transport.getDestinationRegion());
        holder.sender.setText(transport.getSender());
        holder.receiver.setText(transport.getReceiver());
        holder.item.setText(transport.getItem());
        holder.quantity.setText(transport.getQuantity() + "");
        holder.minutes.setText(transport.getMinutes() + "");
        holder.endTime.setText(transport.getTime());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView startRegion, destinationRegion, sender, receiver, item, quantity, minutes, endTime;
        ArrayList<Transport> transports = new ArrayList<Transport>();
        Context ctx;

        public RecyclerViewHolder(View view, Context ctx, ArrayList<Transport> transports) {
            super(view);
            this.transports = transports;
            this.ctx = ctx;
            view.setOnClickListener(this);

            startRegion = (TextView) view.findViewById(R.id.active_transport_row_start);
            destinationRegion = (TextView) view.findViewById(R.id.active_transport_row_destination);
            sender = (TextView) view.findViewById(R.id.active_transport_row_sender);
            receiver = (TextView) view.findViewById(R.id.active_transport_row_receiver);
            item = (TextView) view.findViewById(R.id.active_transport_row_item);
            quantity = (TextView) view.findViewById(R.id.active_transport_row_quantity);
            minutes = (TextView) view.findViewById(R.id.active_transport_row_minutes);
            endTime = (TextView) view.findViewById(R.id.active_transport_row_time);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Transport transport = this.transports.get(position);
        }
    }
}
