package com.ollum.ecoCrats.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ollum.ecoCrats.Activities.MainActivity;
import com.ollum.ecoCrats.BackgroundTasks.BackgroundTask;
import com.ollum.ecoCrats.BackgroundTasks.BackgroundTaskActiveTransport;
import com.ollum.ecoCrats.BackgroundTasks.BackgroundTaskInbox;
import com.ollum.ecoCrats.R;

public class ActiveTransportFragment extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    Button completed;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_active_transport, container, false);

        setHasOptionsMenu(true);
        MainActivity.actionBar.setTitle(R.string.active_transport_title);

        completed = (Button) view.findViewById(R.id.active_transport_button_completed);
        completed.setOnClickListener(this);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.active_transport_swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.active_transport_recyclerView);

        String method = "updateTransport";
        BackgroundTask backgroundTask = new BackgroundTask(getContext());
        backgroundTask.execute(method);

        BackgroundTaskActiveTransport backgroundTaskActiveTransport = new BackgroundTaskActiveTransport(getContext(), recyclerView);
        backgroundTaskActiveTransport.execute(MainActivity.user.username);

        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.active_transport_button_completed:
                CompletedTransportFragment completedTransportFragment = new CompletedTransportFragment();
                FragmentTransaction transaction2 = MainActivity.fragmentManager.beginTransaction();
                transaction2.replace(R.id.mainContent, completedTransportFragment, "CompletedTransportFragment");
                transaction2.addToBackStack("CompletedTransportFragment");
                transaction2.commit();
        }
    }

    @Override
    public void onRefresh() {
        String method = "updateTransport";
        BackgroundTask backgroundTask = new BackgroundTask(getContext());
        backgroundTask.execute(method);

        BackgroundTaskActiveTransport backgroundTaskActiveTransport = new BackgroundTaskActiveTransport(getContext(), recyclerView);
        backgroundTaskActiveTransport.execute(MainActivity.user.username);

        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem newTransport = menu.findItem(R.id.newTransport);
        newTransport.setVisible(true);
    }
}
