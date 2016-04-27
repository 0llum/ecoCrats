package com.ollum.ecoCrats.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ollum.ecoCrats.Activities.MainActivity;
import com.ollum.ecoCrats.BackgroundTasks.BackgroundTaskAddItems;
import com.ollum.ecoCrats.R;

public class AddItemsFragment extends Fragment {
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_items, container, false);

        MainActivity.actionBar.setTitle(R.string.items_title);

        recyclerView = (RecyclerView) view.findViewById(R.id.items_recyclerView);
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

        BackgroundTaskAddItems backgroundTaskAddItems = new BackgroundTaskAddItems(getContext(), recyclerView);
        backgroundTaskAddItems.execute();

        return view;
    }
}
