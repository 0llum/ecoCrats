package com.ollum.ecoCrats.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.ollum.ecoCrats.Activities.MainActivity;
import com.ollum.ecoCrats.Adapters.StoresAdapter;
import com.ollum.ecoCrats.BackgroundTasks.BackgroundTask;
import com.ollum.ecoCrats.BackgroundTasks.BackgroundTaskStoreDetails;
import com.ollum.ecoCrats.R;

public class StoreDetailsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    public static int ID;
    String region;
    int capacity;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    Bundle bundle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Bundle bundle = StoresAdapter.bundle;

        if (bundle != null) {
            ID = bundle.getInt("ID");
            region = bundle.getString("region");
            capacity = bundle.getInt("capacity");
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store_details, container, false);

        setHasOptionsMenu(true);
        MainActivity.actionBar.setTitle(region);

        recyclerView = (RecyclerView) view.findViewById(R.id.store_details_recyclerView);
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

        BackgroundTaskStoreDetails backgroundTaskStoreDetails = new BackgroundTaskStoreDetails(getContext(), recyclerView);
        backgroundTaskStoreDetails.execute("" + ID);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.store_details_swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);

        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }

        return view;
    }

    @Override
    public void onRefresh() {
        String method = "updateTransport";
        BackgroundTask backgroundTask = new BackgroundTask(getContext());
        backgroundTask.execute(method);

        BackgroundTaskStoreDetails backgroundTaskStoreDetails = new BackgroundTaskStoreDetails(getContext(), recyclerView);
        backgroundTaskStoreDetails.execute("" + ID);

        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem addItem = menu.findItem(R.id.addItem);
        addItem.setVisible(true);
    }
}
