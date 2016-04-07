package com.ollum.ecoCrats;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MessagesFragment extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    Button newMessage;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_messages, container, false);

        newMessage = (Button) view.findViewById(R.id.messages_new_message);
        newMessage.setOnClickListener(this);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.messages_swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.messages_recyclerView);

        BackgroundTaskMessages backgroundTaskMessages = new BackgroundTaskMessages(getContext(), recyclerView);
        backgroundTaskMessages.execute(MainActivity.user.username);

        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.messages_new_message:
                NewMessageFragment newMessageFragment = new NewMessageFragment();
                FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                transaction.replace(R.id.mainContent, newMessageFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
        }
    }

    @Override
    public void onRefresh() {
        BackgroundTaskMessages backgroundTaskMessages = new BackgroundTaskMessages(getContext(), recyclerView);
        backgroundTaskMessages.execute(MainActivity.user.username);

        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}
