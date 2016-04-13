package com.ollum.ecoCrats.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ollum.ecoCrats.Activities.MainActivity;
import com.ollum.ecoCrats.Adapters.StoresAdapter;
import com.ollum.ecoCrats.BackgroundTasks.BackgroundTaskStoreDetails;
import com.ollum.ecoCrats.R;

public class StoreDetailsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {
    String region;
    public static int ID;
    int capacity;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    Button add;
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

        MainActivity.setTitle(region);

        recyclerView = (RecyclerView) view.findViewById(R.id.store_details_recyclerView);

        add = (Button) view.findViewById(R.id.store_details_button_add);
        add.setOnClickListener(this);

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
        BackgroundTaskStoreDetails backgroundTaskStoreDetails = new BackgroundTaskStoreDetails(getContext(), recyclerView);
        backgroundTaskStoreDetails.execute("" + ID);

        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.store_details_button_add:
                bundle = new Bundle();
                bundle.putInt("ID", ID);

                ItemsFragment itemsFragment = new ItemsFragment();
                itemsFragment.setArguments(bundle);
                FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                transaction.replace(R.id.mainContent, itemsFragment, "ItemsFragment");
                transaction.addToBackStack("ItemsFragment");
                transaction.commit();
                break;
        }
    }
}
