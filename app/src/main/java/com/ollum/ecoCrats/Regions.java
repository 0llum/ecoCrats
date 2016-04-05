package com.ollum.ecoCrats;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class Regions extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    UserLocalStore userLocalStore;
    User user;
    SwipeRefreshLayout swipeRefreshLayout;
    String putExtra;
    android.support.v7.app.ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regions);

        putExtra = getIntent().getStringExtra("country");

        actionBar = getSupportActionBar();
        actionBar.setTitle(putExtra);

        userLocalStore = new UserLocalStore(this);
        user = userLocalStore.getLoggedInUser();

        BackgroundTaskRegions backgroundTaskRecyclerView = new BackgroundTaskRegions(Regions.this);
        backgroundTaskRecyclerView.execute(putExtra);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.regions_swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);

        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }

        setUserOnline(user);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setUserOnline(user);
    }

    @Override
    protected void onPause() {
        super.onPause();
        setUserOffline(user);
    }

    private void setUserOnline(User user) {
        String method = "online";
        BackgroundTaskStatus backgroundTaskStatus = new BackgroundTaskStatus(this);
        backgroundTaskStatus.execute(method, user.username);
    }

    private void setUserOffline(User user) {
        String method = "offline";
        BackgroundTaskStatus backgroundTaskStatus = new BackgroundTaskStatus(this);
        backgroundTaskStatus.execute(method, user.username);
    }

    private void setUserAFK(User user) {
        String method = "afk";
        BackgroundTaskStatus backgroundTaskStatus = new BackgroundTaskStatus(this);
        backgroundTaskStatus.execute(method, user.username);
    }

    @Override
    public void onRefresh() {
        finish();
        Intent intent = new Intent(this, Regions.class);
        intent.putExtra("country", putExtra);
        this.startActivity(intent);
        overridePendingTransition(0, 0);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
    }
}
