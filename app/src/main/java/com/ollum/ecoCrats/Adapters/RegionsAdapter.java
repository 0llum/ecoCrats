package com.ollum.ecoCrats.Adapters;

import android.content.Context;
import android.graphics.Color;
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
import com.ollum.ecoCrats.Classes.Region;
import com.ollum.ecoCrats.Fragments.RegionDetailsFragment;
import com.ollum.ecoCrats.R;

import java.util.ArrayList;

public class RegionsAdapter extends RecyclerView.Adapter<RegionsAdapter.RecyclerViewHolder> {
    public static Bundle bundle;
    ArrayList<Region> arrayList = new ArrayList<>();
    Context ctx;

    public RegionsAdapter(ArrayList<Region> arrayList, Context ctx) {
        this.arrayList = arrayList;
        this.ctx = ctx;
    }

    @Override
    public RegionsAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_regions_row, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view, ctx, arrayList);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Region region = arrayList.get(position);
        holder.region.setText(region.getName());
        holder.capital.setText(region.getCapital());

        holder.imageView.setImageResource(R.mipmap.afghanistan);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView region, capital;
        ImageView imageView, info;
        ArrayList<Region> regions = new ArrayList<Region>();
        Context ctx;

        public RecyclerViewHolder(View view, Context ctx, ArrayList<Region> regions) {
            super(view);
            this.regions = regions;
            this.ctx = ctx;
            view.setOnClickListener(this);

            region = (TextView) view.findViewById(R.id.regions_row_region);
            capital = (TextView) view.findViewById(R.id.regions_row_capital);
            imageView = (ImageView) view.findViewById(R.id.regions_row_image);
            info = (ImageView) view.findViewById(R.id.regions_row_info);
            info.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Region region = this.regions.get(position);

            if (v.getId() == R.id.regions_row_info) {
                Snackbar snackbar = Snackbar.make(MainActivity.coordinatorLayout, region.getName(), Snackbar.LENGTH_LONG);
                TextView tv = (TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
                tv.setTextColor(Color.BLACK);
                snackbar.getView().setBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                snackbar.show();
            } else {
                bundle = new Bundle();
                bundle.putInt("ID", region.getID());
                bundle.putString("region", region.getName());
                bundle.putString("capital", region.getCapital());
                bundle.putInt("area", region.getArea());
                bundle.putInt("population", region.getPopulation());

                RegionDetailsFragment regionDetailsFragment = new RegionDetailsFragment();
                regionDetailsFragment.setArguments(bundle);
                FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                transaction.replace(R.id.mainContent, regionDetailsFragment, "RegionDetailsFragment");
                transaction.addToBackStack("RegionDetailsFragment");
                transaction.commit();
            }
        }
    }
}
