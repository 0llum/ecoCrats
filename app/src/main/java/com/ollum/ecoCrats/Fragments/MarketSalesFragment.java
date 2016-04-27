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
import com.ollum.ecoCrats.Adapters.ItemsAdapter;
import com.ollum.ecoCrats.BackgroundTasks.BackgroundTaskSales;
import com.ollum.ecoCrats.R;

public class MarketSalesFragment extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    Button purchases;
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

        View view = inflater.inflate(R.layout.fragment_market_sales, container, false);

        MainActivity.actionBar.setTitle(name);

        recyclerView = (RecyclerView) view.findViewById(R.id.sales_recyclerView);
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
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.sales_swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        purchases = (Button) view.findViewById(R.id.sales_button_purchases);
        purchases.setOnClickListener(this);

        BackgroundTaskSales backgroundTaskSales = new BackgroundTaskSales(getContext(), recyclerView);
        backgroundTaskSales.execute(ID + "");

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sales_button_purchases:
                MarketPurchasesFragment marketPurchasesFragment = new MarketPurchasesFragment();
                FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                transaction.replace(R.id.mainContent, marketPurchasesFragment, "MarketPurchasesFragment");
                transaction.addToBackStack("MarketPurchasesFragment");
                transaction.commit();
                break;
        }
    }

    @Override
    public void onRefresh() {
        BackgroundTaskSales backgroundTaskSales = new BackgroundTaskSales(getContext(), recyclerView);
        backgroundTaskSales.execute(ID + "");

        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}
