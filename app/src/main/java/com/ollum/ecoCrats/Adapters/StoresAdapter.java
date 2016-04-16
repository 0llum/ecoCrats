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
import com.ollum.ecoCrats.Classes.Store;
import com.ollum.ecoCrats.Fragments.StoreDetailsFragment;
import com.ollum.ecoCrats.R;

import java.util.ArrayList;

public class StoresAdapter extends RecyclerView.Adapter<StoresAdapter.RecyclerViewHolder> {
    public static Bundle bundle;
    ArrayList<Store> arrayList = new ArrayList<>();
    Context ctx;

    public StoresAdapter(ArrayList<Store> arrayList, Context ctx) {
        this.arrayList = arrayList;
        this.ctx = ctx;
    }

    @Override
    public StoresAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_stores_row, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view, ctx, arrayList);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(StoresAdapter.RecyclerViewHolder holder, int position) {
        Store store = arrayList.get(position);
        holder.region.setText(store.getRegion());
        holder.capacity.setText("" + store.getCapacity());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView region, capacity;
        ArrayList<Store> stores = new ArrayList<Store>();
        Context ctx;

        public RecyclerViewHolder(View view, Context ctx, ArrayList<Store> stores) {
            super(view);
            this.stores = stores;
            this.ctx = ctx;
            view.setOnClickListener(this);

            region = (TextView) view.findViewById(R.id.display_stores_row_region);
            capacity = (TextView) view.findViewById(R.id.display_stores_row_capacity);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Store store = this.stores.get(position);

            bundle = new Bundle();
            bundle.putInt("ID", store.getID());
            bundle.putString("region", store.getRegion());
            bundle.putInt("capacity", store.getCapacity());

            StoreDetailsFragment storeDetailsFragment = new StoreDetailsFragment();
            storeDetailsFragment.setArguments(bundle);
            FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
            transaction.replace(R.id.mainContent, storeDetailsFragment, "StoreDetailsFragment");
            transaction.addToBackStack("StoreDetailsFragment");
            transaction.commit();
        }
    }
}
