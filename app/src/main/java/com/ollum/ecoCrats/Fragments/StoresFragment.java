package com.ollum.ecoCrats.Fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ollum.ecoCrats.Activities.MainActivity;
import com.ollum.ecoCrats.BackgroundTasks.BackgroundTaskStores;
import com.ollum.ecoCrats.R;

public class StoresFragment extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    Button newStore;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stores, container, false);

        MainActivity.setTitle("Stores");

        recyclerView = (RecyclerView) view.findViewById(R.id.stores_recyclerView);

        newStore = (Button) view.findViewById(R.id.stores_button_new);
        newStore.setOnClickListener(this);

        BackgroundTaskStores backgroundTaskStores = new BackgroundTaskStores(getContext(), recyclerView);
        backgroundTaskStores.execute(MainActivity.user.getUsername());

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.stores_swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);

        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.stores_button_new:
                break;
        }
    }

    @Override
    public void onRefresh() {
        BackgroundTaskStores backgroundTaskStores = new BackgroundTaskStores(getContext(), recyclerView);
        backgroundTaskStores.execute(MainActivity.user.getUsername());

        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}
