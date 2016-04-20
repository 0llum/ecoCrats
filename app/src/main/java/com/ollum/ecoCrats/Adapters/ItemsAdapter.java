package com.ollum.ecoCrats.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.ollum.ecoCrats.Activities.MainActivity;
import com.ollum.ecoCrats.BackgroundTasks.BackgroundTask;
import com.ollum.ecoCrats.Classes.Item;
import com.ollum.ecoCrats.Fragments.MarketSalesFragment;
import com.ollum.ecoCrats.Fragments.StoreDetailsFragment;
import com.ollum.ecoCrats.R;

import java.util.ArrayList;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.RecyclerViewHolder> {
    public static Bundle bundle;
    ArrayList<Item> arrayList = new ArrayList<>();
    Context ctx;

    public ItemsAdapter(ArrayList<Item> arrayList, Context ctx) {
        this.arrayList = arrayList;
        this.ctx = ctx;
    }

    @Override
    public ItemsAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_items_row, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view, ctx, arrayList);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Item item = arrayList.get(position);
        holder.name.setText(item.getName());
        holder.company.setText(item.getCompany());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, company;
        ArrayList<Item> items = new ArrayList<Item>();
        Context ctx;
        Item item;

        public RecyclerViewHolder(View view, Context ctx, ArrayList<Item> items) {
            super(view);
            this.items = items;
            this.ctx = ctx;
            view.setOnClickListener(this);

            name = (TextView) view.findViewById(R.id.items_row_name);
            company = (TextView) view.findViewById(R.id.items_row_company);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            item = this.items.get(position);

            bundle = new Bundle();
            bundle.putInt("ID", item.getID());
            bundle.putString("Name", item.getName());

            MarketSalesFragment marketSalesFragment = new MarketSalesFragment();
            FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
            transaction.replace(R.id.mainContent, marketSalesFragment, "MarketSalesFragment");
            transaction.addToBackStack("MarketSalesFragment");
            transaction.commit();
        }
    }
}
