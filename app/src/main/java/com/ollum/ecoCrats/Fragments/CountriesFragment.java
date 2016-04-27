package com.ollum.ecoCrats.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ollum.ecoCrats.Activities.MainActivity;
import com.ollum.ecoCrats.BackgroundTasks.BackgroundTaskCountries;
import com.ollum.ecoCrats.R;

public class CountriesFragment extends Fragment {
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_countries, container, false);

        MainActivity.actionBar.setTitle(R.string.countries_title);

        recyclerView = (RecyclerView) view.findViewById(R.id.countries_recyclerView);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    MainActivity.fabMenu.hideMenuButton(true);
                } else {
                    MainActivity.fabMenu.showMenuButton(true);
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        BackgroundTaskCountries backgroundTaskRecyclerView = new BackgroundTaskCountries(getContext(), recyclerView);
        backgroundTaskRecyclerView.execute();

        return view;
    }

}
