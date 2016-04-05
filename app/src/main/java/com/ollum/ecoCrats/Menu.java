package com.ollum.ecoCrats;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Menu extends AppCompatActivity implements View.OnClickListener {

    TextView welcome;
    Button bFriends, bCountries, bItems, bLogout, bSettings, bMessages;
    UserLocalStore userLocalStore;
    User user;
    android.support.v7.app.ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Menu");

        userLocalStore = new UserLocalStore(this);
        user = userLocalStore.getLoggedInUser();

        if(userLocalStore.getLoggedInUser().getUsername().length() == 0) {
            finish();
            startActivity(new Intent(this, Login.class));
            overridePendingTransition(0, 0);
        }

        bFriends = (Button) findViewById(R.id.menu_friends);
        bCountries = (Button) findViewById(R.id.menu_countries);
        bItems = (Button) findViewById(R.id.menu_items);
        bLogout = (Button) findViewById(R.id.menu_logout);
        bSettings = (Button) findViewById(R.id.menu_settings);
        bMessages = (Button) findViewById(R.id.menu_messages);

        bFriends.setOnClickListener(this);
        bCountries.setOnClickListener(this);
        bItems.setOnClickListener(this);
        bLogout.setOnClickListener(this);
        bSettings.setOnClickListener(this);
        bMessages.setOnClickListener(this);

        setUserOnline(user);

        welcome = (TextView) findViewById(R.id.menu_welcome);
        welcome.setText("Welcome, " + user.getUsername());
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

    @Override
    public void onBackPressed() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu_friends:
                startActivity(new Intent(this, Friendlist.class));
                overridePendingTransition(0, 0);
                break;
            case R.id.menu_countries:
                startActivity(new Intent(this, Countries.class));
                overridePendingTransition(0, 0);
                break;
            case R.id.menu_items:
                break;
            case R.id.menu_logout:
                setUserOffline(user);
                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);

                finish();
                startActivity(new Intent(this, Login.class));
                overridePendingTransition(0, 0);
                break;
            case R.id.menu_settings:
                startActivity(new Intent(this, Settings.class));
                overridePendingTransition(0, 0);
                break;
            case R.id.menu_messages:
                startActivity(new Intent(this, Messages.class));
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
}
