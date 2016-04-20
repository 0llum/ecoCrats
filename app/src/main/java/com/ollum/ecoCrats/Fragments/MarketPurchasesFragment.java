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
import android.widget.Toast;

import com.ollum.ecoCrats.Activities.MainActivity;
import com.ollum.ecoCrats.Adapters.ItemsAdapter;
import com.ollum.ecoCrats.BackgroundTasks.BackgroundTaskPurchases;
import com.ollum.ecoCrats.BackgroundTasks.BackgroundTaskSales;
import com.ollum.ecoCrats.R;

public class MarketPurchasesFragment extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    Button sales;
    int ID;
    String name = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Bundle bundle = ItemsAdapter.bundle;

        if (bundle != null) {
            ID = bundle.getInt("ID");
            name = bundle.getString("Name");
        }

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_market_purchases, container, false);

        MainActivity.actionBar.setTitle(name);

        recyclerView = (RecyclerView) view.findViewById(R.id.purchases_recyclerView);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.purchases_swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        sales = (Button) view.findViewById(R.id.purchases_button_sales);
        sales.setOnClickListener(this);

        BackgroundTaskPurchases backgroundTaskPurchases = new BackgroundTaskPurchases(getContext(), recyclerView);
        backgroundTaskPurchases.execute(ID + "");

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.purchases_button_sales:
                MarketSalesFragment marketSalesFragment = new MarketSalesFragment();
                FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                transaction.replace(R.id.mainContent, marketSalesFragment, "MarketSalesFragment");
                transaction.addToBackStack("MarketSalesFragment");
                transaction.commit();
                break;
        }
    }

    @Override
    public void onRefresh() {
        BackgroundTaskPurchases backgroundTaskPurchases = new BackgroundTaskPurchases(getContext(), recyclerView);
        backgroundTaskPurchases.execute(ID + "");

        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}
