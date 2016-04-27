package com.ollum.ecoCrats.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ollum.ecoCrats.Activities.MainActivity;
import com.ollum.ecoCrats.Classes.Item;
import com.ollum.ecoCrats.Fragments.MarketSalesFragment;
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

        switch (item.getID()) {
            case 1:
                holder.imageView.setImageResource(R.mipmap.eisenerz);
                break;
            case 2:
                holder.imageView.setImageResource(R.mipmap.holz);
                break;
            case 3:
                holder.imageView.setImageResource(R.mipmap.erdoel);
                break;
            case 4:
                holder.imageView.setImageResource(R.mipmap.erdgas);
                break;
            case 5:
                holder.imageView.setImageResource(R.mipmap.kautschuk);
                break;
            case 6:
                holder.imageView.setImageResource(R.mipmap.gold);
                break;
            case 7:
                holder.imageView.setImageResource(R.mipmap.kohle);
                break;
            case 8:
                holder.imageView.setImageResource(R.mipmap.sand);
                break;
            case 9:
                holder.imageView.setImageResource(R.mipmap.wasser);
                break;
            case 10:
                holder.imageView.setImageResource(R.mipmap.stein);
                break;
            case 11:
                holder.imageView.setImageResource(R.mipmap.kupfererz);
                break;
            case 12:
                holder.imageView.setImageResource(R.mipmap.baumwolle);
                break;
            case 13:
                holder.imageView.setImageResource(R.mipmap.leder);
                break;
            case 14:
                holder.imageView.setImageResource(R.mipmap.kalkstein);
                break;
            case 15:
                holder.imageView.setImageResource(R.mipmap.ton);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, company;
        ImageView imageView, info;
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
            imageView = (ImageView) view.findViewById(R.id.items_row_image);
            info = (ImageView) view.findViewById(R.id.items_row_info);
            info.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            item = this.items.get(position);

            if (v.getId() == R.id.items_row_info) {
                Snackbar.make(MainActivity.coordinatorLayout, item.getName(), Snackbar.LENGTH_LONG).show();
            } else {
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
}
