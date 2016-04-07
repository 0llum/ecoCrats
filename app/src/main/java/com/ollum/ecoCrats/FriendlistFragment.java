package com.ollum.ecoCrats;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FriendlistFragment extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    EditText searchBar;
    Button add, remove;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friendlist, container, false);

        searchBar = (EditText) view.findViewById(R.id.friendlist_searchbar);
        add = (Button) view.findViewById(R.id.friendlist_add);
        add.setOnClickListener(this);
        remove = (Button) view.findViewById(R.id.friendlist_remove);
        remove.setOnClickListener(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.friendlist_recyclerView);

        BackgroundTaskFriendlist backgroundTaskFriendlist = new BackgroundTaskFriendlist(getContext(), recyclerView);
        backgroundTaskFriendlist.execute(MainActivity.user.username);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.friendlist_swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);

        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.friendlist_add:
                String friendToAdd = searchBar.getText().toString().trim();
                addFriend(friendToAdd);
                break;
            case R.id.friendlist_remove:
                String friendToRemove = searchBar.getText().toString().trim();
                removeFriend(friendToRemove);
                break;
        }
    }

    @Override
    public void onRefresh() {
        BackgroundTaskFriendlist backgroundTaskFriendlist = new BackgroundTaskFriendlist(getContext(), recyclerView);
        backgroundTaskFriendlist.execute(MainActivity.user.username);

        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    private void addFriend(final String username_2) {
        String method = "addFriend";
        BackgroundTask backgroundTask = new BackgroundTask(getContext());
        backgroundTask.execute(method, MainActivity.user.username, username_2);
    }

    private void removeFriend(final String username_2) {
        String method = "removeFriend";
        BackgroundTask backgroundTask = new BackgroundTask(getContext());
        backgroundTask.execute(method, MainActivity.user.username, username_2);
    }
}
