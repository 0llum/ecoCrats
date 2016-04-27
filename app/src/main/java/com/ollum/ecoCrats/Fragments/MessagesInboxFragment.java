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
import com.ollum.ecoCrats.BackgroundTasks.BackgroundTaskInbox;
import com.ollum.ecoCrats.R;

public class MessagesInboxFragment extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    Button outbox;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_messages_inbox, container, false);

        setHasOptionsMenu(true);
        MainActivity.actionBar.setTitle(R.string.inbox_title);

        outbox = (Button) view.findViewById(R.id.messages_inbox_button_outbox);
        outbox.setOnClickListener(this);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.messages_inbox_swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.messages_inbox_recyclerView);
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

        BackgroundTaskInbox backgroundTaskInbox = new BackgroundTaskInbox(getContext(), recyclerView);
        backgroundTaskInbox.execute(MainActivity.user.username);

        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.messages_inbox_button_outbox:
                MessagesOutboxFragment outboxFragment = new MessagesOutboxFragment();
                FragmentTransaction transaction2 = MainActivity.fragmentManager.beginTransaction();
                transaction2.replace(R.id.mainContent, outboxFragment, "MessagesOutboxFragment");
                transaction2.addToBackStack("MessagesOutboxFragment");
                transaction2.commit();
        }
    }

    @Override
    public void onRefresh() {
        BackgroundTaskInbox backgroundTaskInbox = new BackgroundTaskInbox(getContext(), recyclerView);
        backgroundTaskInbox.execute(MainActivity.user.username);

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
