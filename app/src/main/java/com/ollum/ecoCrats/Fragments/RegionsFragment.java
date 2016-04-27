package com.ollum.ecoCrats.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ollum.ecoCrats.Activities.MainActivity;
import com.ollum.ecoCrats.Adapters.CountriesAdapter;
import com.ollum.ecoCrats.BackgroundTasks.BackgroundTaskRegions;
import com.ollum.ecoCrats.R;

public class RegionsFragment extends Fragment {
    RecyclerView recyclerView;
    String country;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Bundle bundle = CountriesAdapter.bundle;

        if (bundle != null) {
            country = bundle.getString("country");
        }

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_regions, container, false);

        MainActivity.actionBar.setTitle(country);

        recyclerView = (RecyclerView) view.findViewById(R.id.regions_recyclerView);
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

        BackgroundTaskRegions backgroundTaskRecyclerView = new BackgroundTaskRegions(getContext(), recyclerView);
        backgroundTaskRecyclerView.execute(country);

        return view;
    }

}
