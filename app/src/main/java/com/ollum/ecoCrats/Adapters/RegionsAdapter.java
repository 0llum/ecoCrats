package com.ollum.ecoCrats.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ollum.ecoCrats.R;
import com.ollum.ecoCrats.Classes.Region;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class RegionsAdapter extends RecyclerView.Adapter<RegionsAdapter.RecyclerViewHolder> {
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
        holder.area.setText("" + NumberFormat.getNumberInstance(Locale.GERMANY).format(region.getArea()));
        holder.population.setText("" + NumberFormat.getNumberInstance(Locale.GERMANY).format(region.getPopulation()));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView region, capital, area, population;
        ArrayList<Region> regions = new ArrayList<Region>();
        Context ctx;

        public RecyclerViewHolder(View view, Context ctx, ArrayList<Region> regions) {
            super(view);
            this.regions = regions;
            this.ctx = ctx;
            view.setOnClickListener(this);

            region = (TextView) view.findViewById(R.id.display_regions_row_region);
            capital = (TextView) view.findViewById(R.id.display_regions_row_capital);
            area = (TextView) view.findViewById(R.id.display_regions_row_area);
            population = (TextView) view.findViewById(R.id.display_regions_row_population);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Region region = this.regions.get(position);
        }
    }
}
