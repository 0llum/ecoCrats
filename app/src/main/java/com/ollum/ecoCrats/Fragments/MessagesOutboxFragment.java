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
import com.ollum.ecoCrats.BackgroundTasks.BackgroundTaskOutbox;
import com.ollum.ecoCrats.R;

public class MessagesOutboxFragment extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    Button inbox;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_messages_outbox, container, false);

        setHasOptionsMenu(true);
        MainActivity.actionBar.setTitle(R.string.outbox_title);

        inbox = (Button) view.findViewById(R.id.messages_outbox_button_inbox);
        inbox.setOnClickListener(this);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.messages_outbox_swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.messages_outbox_recyclerView);

        BackgroundTaskOutbox backgroundTaskOutbox = new BackgroundTaskOutbox(getContext(), recyclerView);
        backgroundTaskOutbox.execute(MainActivity.user.username);

        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.messages_outbox_button_inbox:
                MessagesInboxFragment inboxFragment = new MessagesInboxFragment();
                FragmentTransaction transaction2 = MainActivity.fragmentManager.beginTransaction();
                transaction2.replace(R.id.mainContent, inboxFragment, "MessagesInboxFragment");
                transaction2.addToBackStack("MessagesInboxFragment");
                transaction2.commit();
        }
    }

    @Override
    public void onRefresh() {
        BackgroundTaskOutbox backgroundTaskOutbox = new BackgroundTaskOutbox(getContext(), recyclerView);
        backgroundTaskOutbox.execute(MainActivity.user.username);

        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem newMessage = menu.findItem(R.id.newMessage);
        newMessage.setVisible(true);
    }
}
