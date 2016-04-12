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

import com.ollum.ecoCrats.BackgroundTasks.BackgroundTaskInbox;
import com.ollum.ecoCrats.Activities.MainActivity;
import com.ollum.ecoCrats.R;

public class MessagesInboxFragment extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    Button newMessage, outbox;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_messages_inbox, container, false);

        MainActivity.setTitle("Inbox");

        newMessage = (Button) view.findViewById(R.id.messages_inbox_new_message);
        newMessage.setOnClickListener(this);
        outbox = (Button) view.findViewById(R.id.messages_inbox_button_outbox);
        outbox.setOnClickListener(this);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.messages_inbox_swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.messages_inbox_recyclerView);

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
            case R.id.messages_inbox_new_message:
                NewMessageFragment newMessageFragment = new NewMessageFragment();
                FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                transaction.replace(R.id.mainContent, newMessageFragment, "NewMessageFragment");
                transaction.addToBackStack("NewMessageFragment");
                transaction.commit();
                break;
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
}
