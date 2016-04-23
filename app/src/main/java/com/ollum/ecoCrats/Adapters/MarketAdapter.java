package com.ollum.ecoCrats.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ollum.ecoCrats.Classes.Item;
import com.ollum.ecoCrats.R;

import java.util.ArrayList;

public class MarketAdapter extends RecyclerView.Adapter<MarketAdapter.RecyclerViewHolder> {
    public static Bundle bundle;
    ArrayList<Item> arrayList = new ArrayList<>();
    Context ctx;

    public MarketAdapter(ArrayList<Item> arrayList, Context ctx) {
        this.arrayList = arrayList;
        this.ctx = ctx;
    }

    @Override
    public MarketAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_market_row, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view, ctx, arrayList);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Item item = arrayList.get(position);
        holder.region.setText(item.getRegion());
        holder.username.setText(item.getUsername());
        holder.price.setText(item.getPrice() + "");
        holder.quantity.setText(item.getQuantity() + "");
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView region, username, price, quantity;
        ArrayList<Item> items = new ArrayList<Item>();
        Context ctx;
        Item item;

        public RecyclerViewHolder(View view, Context ctx, ArrayList<Item> items) {
            super(view);
            this.items = items;
            this.ctx = ctx;
            view.setOnClickListener(this);

            region = (TextView) view.findViewById(R.id.display_market_row_region);
            username = (TextView) view.findViewById(R.id.display_market_row_username);
            price = (TextView) view.findViewById(R.id.display_market_row_price);
            quantity = (TextView) view.findViewById(R.id.display_market_row_quantity);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            item = this.items.get(position);
        }
    }
}
