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
import com.ollum.ecoCrats.Classes.Country;
import com.ollum.ecoCrats.Fragments.RegionsFragment;
import com.ollum.ecoCrats.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.RecyclerViewHolder> {
    public static Bundle bundle;
    ArrayList<Country> arrayList = new ArrayList<>();
    Context ctx;

    public CountriesAdapter(ArrayList<Country> arrayList, Context ctx) {
        this.arrayList = arrayList;
        this.ctx = ctx;
    }

    @Override
    public CountriesAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_countries_row, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view, ctx, arrayList);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Country country = arrayList.get(position);
        holder.country.setText(country.getName());
        holder.capital.setText(country.getCapital());
        holder.area.setText("" + NumberFormat.getNumberInstance(Locale.GERMANY).format(country.getArea()));
        holder.population.setText("" + NumberFormat.getNumberInstance(Locale.GERMANY).format(country.getPopulation()));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView country, capital, area, population;
        ArrayList<Country> countries = new ArrayList<Country>();
        Context ctx;

        public RecyclerViewHolder(View view, Context ctx, ArrayList<Country> countries) {
            super(view);
            this.countries = countries;
            this.ctx = ctx;
            view.setOnClickListener(this);

            country = (TextView) view.findViewById(R.id.display_countries_row_country);
            capital = (TextView) view.findViewById(R.id.display_countries_row_capital);
            area = (TextView) view.findViewById(R.id.display_countries_row_area);
            population = (TextView) view.findViewById(R.id.display_countries_row_population);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Country country = this.countries.get(position);

            bundle = new Bundle();
            bundle.putString("country", country.getName());

            RegionsFragment regionsFragment = new RegionsFragment();
            regionsFragment.setArguments(bundle);
            FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
            transaction.replace(R.id.mainContent, regionsFragment, "RegionsFragment");
            transaction.addToBackStack("RegionsFragment");
            transaction.commit();
        }
    }
}
