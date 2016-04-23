package com.ollum.ecoCrats.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ollum.ecoCrats.Activities.MainActivity;
import com.ollum.ecoCrats.BackgroundTasks.BackgroundTaskItems;
import com.ollum.ecoCrats.R;

public class ItemsFragment extends Fragment {
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_items, container, false);

        MainActivity.actionBar.setTitle(R.string.market_title);

        recyclerView = (RecyclerView) view.findViewById(R.id.items_recyclerView);

        BackgroundTaskItems backgroundTaskItems = new BackgroundTaskItems(getContext(), recyclerView);
        backgroundTaskItems.execute();

        return view;
    }
}
