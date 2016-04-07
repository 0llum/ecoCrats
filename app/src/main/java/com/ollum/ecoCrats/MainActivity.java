package com.ollum.ecoCrats;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public static FragmentManager fragmentManager;
    public static User user;
    UserLocalStore userLocalStore;
    private DrawerLayout drawerLayout;
    private ListView listView;
    private String[] navDrawerItems;
    private ActionBarDrawerToggle drawerListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        navDrawerItems = getResources().getStringArray(R.array.navDrawerItems);
        listView = (ListView) findViewById(R.id.drawerList);
        listView.setAdapter(new ArrayAdapter<>(this, R.layout.nav_drawer_list_item, navDrawerItems));
        listView.setOnItemClickListener(this);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerListener = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
            }

            @Override
            public void onDrawerClosed(View drawerView) {
            }
        };
        drawerLayout.setDrawerListener(drawerListener);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.mipmap.ic_launcher);

        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setContentView(imageView)
                .build();

        ImageView messages = new ImageView(this);
        messages.setImageResource(R.drawable.offline);
        ImageView transport = new ImageView(this);
        transport.setImageResource(R.drawable.afk);
        ImageView friendlist = new ImageView(this);
        friendlist.setImageResource(R.drawable.online);

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        SubActionButton buttonMessages = itemBuilder.setContentView(messages).build();
        SubActionButton buttonTransport = itemBuilder.setContentView(transport).build();
        SubActionButton buttonFriendlist = itemBuilder.setContentView(friendlist).build();

        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(buttonMessages)
                .addSubActionView(buttonTransport)
                .addSubActionView(buttonFriendlist)
                .attachTo(actionButton)
                .build();

        userLocalStore = new UserLocalStore(this);
        user = userLocalStore.getLoggedInUser();

        if (userLocalStore.getLoggedInUser().getUsername().length() == 0) {
            finish();
            startActivity(new Intent(this, Login.class));
            overridePendingTransition(0, 0);
        }

        setUserOnline(user);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerListener.syncState();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectItem(position);
        closeDrawer();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (navDrawerItems[position]) {
            case "Friendlist":
                FriendlistFragment friendlistFragment = new FriendlistFragment();
                transaction.replace(R.id.mainContent, friendlistFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case "Messages":
                MessagesFragment messagesFragment = new MessagesFragment();
                transaction.replace(R.id.mainContent, messagesFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case "Countries":
                CountriesFragment countriesFragment = new CountriesFragment();
                transaction.replace(R.id.mainContent, countriesFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
        }
    }

    private void selectItem(int position) {
        listView.setItemChecked(position, true);
        setTitle(navDrawerItems[position]);

    }

    public void setTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    public void closeDrawer() {
        drawerLayout.closeDrawers();
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerListener.onOptionsItemSelected(item)) {
            return true;
        }
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
                SettingsFragment settingsFragment = new SettingsFragment();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.mainContent, settingsFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                setTitle("Settings");
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
        if (fragmentManager.getBackStackEntryCount() != 0) {
            super.onBackPressed();
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
