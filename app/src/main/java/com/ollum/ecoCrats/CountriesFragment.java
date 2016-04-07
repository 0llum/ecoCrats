package com.ollum.ecoCrats;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CountriesFragment extends Fragment {
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_countries, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.countries_recyclerView);

        BackgroundTaskCountries backgroundTaskRecyclerView = new BackgroundTaskCountries(getContext(), recyclerView);
        backgroundTaskRecyclerView.execute();

        return view;
    }

}
