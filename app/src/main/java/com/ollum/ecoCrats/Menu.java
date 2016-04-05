package com.ollum.ecoCrats;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Menu extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private DrawerLayout drawerLayout;
    private ListView listView;
    private String[] navDrawerItems;
    private ActionBarDrawerToggle drawerListener;

    TextView welcome;
    Button bFriends, bCountries, bItems, bMessages;
    UserLocalStore userLocalStore;
    User user;
    android.support.v7.app.ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        navDrawerItems = getResources().getStringArray(R.array.navDrawerItems);
        listView = (ListView) findViewById(R.id.drawerList);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, navDrawerItems));
        listView.setOnItemClickListener(this);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerLayout.setDrawerListener(drawerListener);

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
        bMessages = (Button) findViewById(R.id.menu_messages);

        bFriends.setOnClickListener(this);
        bCountries.setOnClickListener(this);
        bItems.setOnClickListener(this);
        bMessages.setOnClickListener(this);

        setUserOnline(user);

        welcome = (TextView) findViewById(R.id.menu_welcome);
        welcome.setText("Welcome, " + user.getUsername());
    }

    @Override
     public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, navDrawerItems[position], Toast.LENGTH_LONG).show();
        selectItem(position);
    }

    private void selectItem(int position) {
        listView.setItemChecked(position, true);
        setTitle(navDrawerItems[position ]);

    }
    public void setTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                setUserOffline(user);
                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);

                finish();
                startActivity(new Intent(this, Login.class));
                overridePendingTransition(0, 0);
                break;
            case R.id.settings:
                startActivity(new Intent(this, Settings.class));
                overridePendingTransition(R.anim.settings_in, R.anim.settings_out);
                break;
        }
        return super.onOptionsItemSelected(item);
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
