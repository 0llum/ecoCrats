package com.ollum.ecoCrats.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ollum.ecoCrats.Activities.MainActivity;
import com.ollum.ecoCrats.BackgroundTasks.BackgroundTask;
import com.ollum.ecoCrats.BackgroundTasks.BackgroundTaskFriendlist;
import com.ollum.ecoCrats.R;

public class FriendlistFragment extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    EditText searchBar;
    Button add;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friendlist, container, false);

        MainActivity.actionBar.setTitle(R.string.friendlist_title);

        searchBar = (EditText) view.findViewById(R.id.friendlist_searchbar);

        add = (Button) view.findViewById(R.id.friendlist_add);
        add.setOnClickListener(this);

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
}
