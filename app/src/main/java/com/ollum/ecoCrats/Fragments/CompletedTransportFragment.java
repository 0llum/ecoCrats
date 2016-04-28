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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ollum.ecoCrats.Activities.MainActivity;
import com.ollum.ecoCrats.BackgroundTasks.BackgroundTask;
import com.ollum.ecoCrats.BackgroundTasks.BackgroundTaskCompletedTransport;
import com.ollum.ecoCrats.BackgroundTasks.BackgroundTaskInbox;
import com.ollum.ecoCrats.R;

public class CompletedTransportFragment extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    Button completed;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_completed_transport, container, false);

        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.completed_transport_title);

        completed = (Button) view.findViewById(R.id.completed_transport_button_active);
        completed.setOnClickListener(this);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.completed_transport_swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.completed_transport_recyclerView);
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

        if (isOnline()) {
            String method = "updateTransport";
            BackgroundTask backgroundTask = new BackgroundTask(getContext());
            backgroundTask.execute(method);

            BackgroundTaskCompletedTransport backgroundTaskActiveTransport = new BackgroundTaskCompletedTransport(getContext(), recyclerView);
            backgroundTaskActiveTransport.execute(MainActivity.user.username);
        } else {
            Snackbar snackbar = Snackbar.make(MainActivity.coordinatorLayout, R.string.no_internet, Snackbar.LENGTH_LONG);
            TextView tv = (TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
            tv.setTextColor(Color.BLACK);
            snackbar.getView().setBackgroundColor(getResources().getColor(R.color.colorAccent));
            snackbar.show();        }

        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.completed_transport_button_active:
                ActiveTransportFragment activeTransportFragment = new ActiveTransportFragment();
                FragmentTransaction transaction2 = MainActivity.fragmentManager.beginTransaction();
                transaction2.replace(R.id.mainContent, activeTransportFragment, "ActiveTransportFragment");
                transaction2.addToBackStack("ActiveTransportFragment");
                transaction2.commit();
        }
    }

    @Override
    public void onRefresh() {
        if (isOnline()) {
            String method = "updateTransport";
            BackgroundTask backgroundTask = new BackgroundTask(getContext());
            backgroundTask.execute(method);

            BackgroundTaskCompletedTransport backgroundTaskActiveTransport = new BackgroundTaskCompletedTransport(getContext(), recyclerView);
            backgroundTaskActiveTransport.execute(MainActivity.user.username);
        } else {
            Snackbar snackbar = Snackbar.make(MainActivity.coordinatorLayout, R.string.no_internet, Snackbar.LENGTH_LONG);
            TextView tv = (TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
            tv.setTextColor(Color.BLACK);
            snackbar.getView().setBackgroundColor(getResources().getColor(R.color.colorAccent));
            snackbar.show();        }

        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem newTransport = menu.findItem(R.id.newTransport);
        newTransport.setVisible(true);
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
