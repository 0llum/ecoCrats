package com.ollum.ecoCrats.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(name);

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

        if (isOnline()) {
            BackgroundTaskSales backgroundTaskSales = new BackgroundTaskSales(getContext(), recyclerView);
            backgroundTaskSales.execute(ID + "");
        } else {
            Snackbar snackbar = Snackbar.make(MainActivity.coordinatorLayout, R.string.no_internet, Snackbar.LENGTH_LONG);
            TextView tv = (TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
            tv.setTextColor(Color.BLACK);
            snackbar.getView().setBackgroundColor(getResources().getColor(R.color.colorAccent));
            snackbar.show();
        }

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
        if (isOnline()) {
            BackgroundTaskSales backgroundTaskSales = new BackgroundTaskSales(getContext(), recyclerView);
            backgroundTaskSales.execute(ID + "");
        } else {
            Snackbar snackbar = Snackbar.make(MainActivity.coordinatorLayout, R.string.no_internet, Snackbar.LENGTH_LONG);
            TextView tv = (TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
            tv.setTextColor(Color.BLACK);
            snackbar.getView().setBackgroundColor(getResources().getColor(R.color.colorAccent));
            snackbar.show();
        }

        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
