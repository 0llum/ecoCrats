package com.ollum.ecoCrats.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;
import com.ollum.ecoCrats.BackgroundTasks.BackgroundTaskLatestMessage;
import com.ollum.ecoCrats.BackgroundTasks.BackgroundTaskStatus;
import com.ollum.ecoCrats.Fragments.CountriesFragment;
import com.ollum.ecoCrats.Fragments.FriendlistFragment;
import com.ollum.ecoCrats.Fragments.MessagesInboxFragment;
import com.ollum.ecoCrats.Fragments.ProfileFragment;
import com.ollum.ecoCrats.Fragments.SettingsFragment;
import com.ollum.ecoCrats.Fragments.StoresFragment;
import com.ollum.ecoCrats.R;
import com.ollum.ecoCrats.Classes.User;
import com.ollum.ecoCrats.SharedPrefs.UserLocalStore;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    public static FragmentManager fragmentManager;
    public static User user;
    UserLocalStore userLocalStore;
    private DrawerLayout drawerLayout;
    private ListView listView;
    private String[] navDrawerItems;
    private ActionBarDrawerToggle drawerListener;
    FloatingActionButton actionButton;
    FloatingActionMenu actionMenu;
    public static ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();

        userLocalStore = new UserLocalStore(this);
        user = userLocalStore.getLoggedInUser();

        if (userLocalStore.getLoggedInUser().getUsername().length() == 0) {
            finish();
            startActivity(new Intent(this, Login.class));
            overridePendingTransition(0, 0);
        }

        setUserOnline(user);

        String fragment = getIntent().getStringExtra("fragment");
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (fragment != null) {
            if (fragment.equals("MessagesInboxFragment")) {
                MessagesInboxFragment messagesInboxFragment = new MessagesInboxFragment();
                transaction.replace(R.id.mainContent, messagesInboxFragment, "MessagesInboxFragment");
                transaction.addToBackStack("MessagesInboxFragment");
                transaction.commit();
            }
        } else {
            ProfileFragment profileFragment = new ProfileFragment();
            transaction.replace(R.id.mainContent, profileFragment, "ProfileFragment");
            transaction.addToBackStack("ProfileFragment");
            transaction.commit();
        }

        navDrawerItems = getResources().getStringArray(R.array.navDrawerItems);
        listView = (ListView) findViewById(R.id.drawerList);
        listView.setAdapter(new ArrayAdapter<>(this, R.layout.nav_drawer_list_item, navDrawerItems));
        listView.setOnItemClickListener(this);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerListener = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                actionMenu.close(true);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
            }
        };
        drawerLayout.setDrawerListener(drawerListener);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        ImageView iconFAB = new ImageView(this);
        iconFAB.setImageResource(R.mipmap.fab);

        actionButton = new FloatingActionButton.Builder(this)
                .setContentView(iconFAB)
                .setBackgroundDrawable(R.drawable.selector_button_blue)
                .build();

        ImageView messages = new ImageView(this);
        messages.setImageResource(R.mipmap.messages);
        ImageView transport = new ImageView(this);
        transport.setImageResource(R.mipmap.transport);
        ImageView contracts = new ImageView(this);
        contracts.setImageResource(R.mipmap.contracts);

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        SubActionButton buttonMessages = itemBuilder.setContentView(messages).build();
        SubActionButton buttonTransport = itemBuilder.setContentView(transport).build();
        SubActionButton buttonContracts = itemBuilder.setContentView(contracts).build();

        buttonMessages.setTag("TAG_MESSAGES");
        buttonContracts.setTag("TAG_CONTRACTS");
        buttonTransport.setTag("TAG_TRANSPORT");

        buttonMessages.setOnClickListener(this);
        buttonTransport.setOnClickListener(this);
        buttonContracts.setOnClickListener(this);

        actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(buttonContracts)
                .addSubActionView(buttonTransport)
                .addSubActionView(buttonMessages)
                .attachTo(actionButton)
                .build();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerListener.syncState();
    }

    @Override
    public void onClick(View v) {
        actionMenu.close(true);

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (v.getTag().equals("TAG_MESSAGES")) {
            MessagesInboxFragment messagesInboxFragment = new MessagesInboxFragment();
            transaction.replace(R.id.mainContent, messagesInboxFragment, "MessagesInboxFragment");
            transaction.addToBackStack("MessagesInboxFragment");
            transaction.commit();
        } else if (v.getTag().equals("TAG_TRANSPORT")) {

        } else if (v.getTag().equals("TAG_CONTRACTS")) {

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectItem(position);
        closeDrawer();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (navDrawerItems[position]) {
            case "Profile":
                ProfileFragment profileFragment = new ProfileFragment();
                transaction.replace(R.id.mainContent, profileFragment, "ProfileFragment");
                transaction.addToBackStack("ProfileFragment");
                transaction.commit();
                break;
            case "Friendlist":
                FriendlistFragment friendlistFragment = new FriendlistFragment();
                transaction.replace(R.id.mainContent, friendlistFragment, "FriendlistFragment");
                transaction.addToBackStack("FriendlistFragment");
                transaction.commit();
                break;
            case "Stores":
                StoresFragment storesFragment = new StoresFragment();
                transaction.replace(R.id.mainContent, storesFragment, "StoresFragment");
                transaction.addToBackStack("StoresFragment");
                transaction.commit();
                break;
            case "Countries":
                CountriesFragment countriesFragment = new CountriesFragment();
                transaction.replace(R.id.mainContent, countriesFragment, "CountriesFragment");
                transaction.addToBackStack("CountriesFragment");
                transaction.commit();
                break;
        }
    }

    private void selectItem(int position) {
        listView.setItemChecked(position, true);
    }

    public static void setTitle(String title) {
        actionBar.setTitle(title);
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
                transaction.replace(R.id.mainContent, settingsFragment, "SettingsFragment");
                transaction.addToBackStack("SettingsFragment");
                transaction.commit();
                setTitle("Settings");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (BackgroundTaskLatestMessage.mNotificationManager != null) {
            BackgroundTaskLatestMessage.mNotificationManager.cancelAll();
        }
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
        FragmentManager.BackStackEntry currentFragment = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1);
        String current = currentFragment.getName();

        if (current.equals("ProfileFragment")) {
            return;
        } else if (current.equals("FriendlistFragment") || current.equals("MessagesInboxFragment") || current.equals("MessagesOutboxFragment") || current.equals("CountriesFragment") || current.equals("StoresFragment")) {
            ProfileFragment profileFragment = new ProfileFragment();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.mainContent, profileFragment, "ProfileFragment");
            transaction.addToBackStack("ProfileFragment");
            transaction.commit();
            return;
        } else {
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
