package com.ollum.ecoCrats;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Messages extends AppCompatActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener{
    Button newMessage;
    SwipeRefreshLayout swipeRefreshLayout;
    android.support.v7.app.ActionBar actionBar;
    UserLocalStore userLocalStore;
    User user;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messages);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Messages");

        newMessage = (Button) findViewById(R.id.messages_new_message);
        newMessage.setOnClickListener(this);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.messages_swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);

        userLocalStore = new UserLocalStore(this);
        user = userLocalStore.getLoggedInUser();
        username = userLocalStore.getLoggedInUser().username;

        BackgroundTaskMessages backgroundTaskMessages = new BackgroundTaskMessages(Messages.this);
        backgroundTaskMessages.execute(username);

        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.messages_new_message:
                startActivity(new Intent(this, NewMessage.class));
                overridePendingTransition(0, 0);
                break;
        }
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
        startActivity(new Intent(this, Messages.class));
        overridePendingTransition(0, 0);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0);
    }
}
