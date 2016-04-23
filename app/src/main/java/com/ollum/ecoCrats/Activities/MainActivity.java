package com.ollum.ecoCrats.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.ollum.ecoCrats.BackgroundTasks.BackgroundTaskLatestMessage;
import com.ollum.ecoCrats.BackgroundTasks.BackgroundTaskStatus;
import com.ollum.ecoCrats.Classes.User;
import com.ollum.ecoCrats.Fragments.BankFragment;
import com.ollum.ecoCrats.Fragments.CountriesFragment;
import com.ollum.ecoCrats.Fragments.FriendlistFragment;
import com.ollum.ecoCrats.Fragments.ItemsFragment;
import com.ollum.ecoCrats.Fragments.MessagesInboxFragment;
import com.ollum.ecoCrats.Fragments.ProfileFragment;
import com.ollum.ecoCrats.Fragments.SettingsFragment;
import com.ollum.ecoCrats.Fragments.StoresFragment;
import com.ollum.ecoCrats.Fragments.TransportFragment;
import com.ollum.ecoCrats.R;
import com.ollum.ecoCrats.SharedPrefs.UserLocalStore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    public static FragmentManager fragmentManager;
    public static User user;
    public static ActionBar actionBar;
    public static CoordinatorLayout coordinatorLayout;
    UserLocalStore userLocalStore;
    private DrawerLayout drawerLayout;
    private ListView listView;
    private String[] navDrawerItems;
    private ActionBarDrawerToggle drawerListener;
    private FloatingActionMenu fabMenu;
    private FloatingActionButton fabMessages;
    private FloatingActionButton fabTransport;
    private FloatingActionButton fabContracts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);

        actionBar = getSupportActionBar();

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
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        fabMenu = (FloatingActionMenu) findViewById(R.id.fabMenu);

        fabMessages = (FloatingActionButton) findViewById(R.id.fabMessages);
        fabTransport = (FloatingActionButton) findViewById(R.id.fabTransport);
        fabContracts = (FloatingActionButton) findViewById(R.id.fabContracts);

        fabMessages.setOnClickListener(this);
        fabTransport.setOnClickListener(this);
        fabContracts.setOnClickListener(this);

        fabMenu.showMenuButton(true);
        fabMenu.setClosedOnTouchOutside(true);
        fabMenu.setOnMenuButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabMenu.toggle(true);
            }
        });

        userLocalStore = new UserLocalStore(this);
        user = userLocalStore.getLoggedInUser();

        if (userLocalStore.getLoggedInUser().getUsername().length() == 0) {
            finish();
            startActivity(new Intent(this, Login.class));
            overridePendingTransition(0, 0);
        } else {
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
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerListener.syncState();
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (fabMenu.isOpened()) {
            fabMenu.close(true);
        }

        switch (v.getId()) {
            case R.id.fabMessages:
                MessagesInboxFragment messagesInboxFragment = new MessagesInboxFragment();
                transaction.replace(R.id.mainContent, messagesInboxFragment, "MessagesInboxFragment");
                transaction.addToBackStack("MessagesInboxFragment");
                transaction.commit();
                break;
            case R.id.fabTransport:
                TransportFragment transportFragment = new TransportFragment();
                transaction.replace(R.id.mainContent, transportFragment, "TransportFragment");
                transaction.addToBackStack("TransportFragment");
                transaction.commit();
                break;
            case R.id.fabContracts:
                break;


        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectItem(position);
        closeDrawer();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (position) {
            case 0:
                ProfileFragment profileFragment = new ProfileFragment();
                transaction.replace(R.id.mainContent, profileFragment, "ProfileFragment");
                transaction.addToBackStack("ProfileFragment");
                transaction.commit();
                break;
            case 1:
                FriendlistFragment friendlistFragment = new FriendlistFragment();
                transaction.replace(R.id.mainContent, friendlistFragment, "FriendlistFragment");
                transaction.addToBackStack("FriendlistFragment");
                transaction.commit();
                break;
            case 2:
                StoresFragment storesFragment = new StoresFragment();
                transaction.replace(R.id.mainContent, storesFragment, "StoresFragment");
                transaction.addToBackStack("StoresFragment");
                transaction.commit();
                break;
            case 5:
                ItemsFragment itemsFragment = new ItemsFragment();
                transaction.replace(R.id.mainContent, itemsFragment, "ItemsFragment");
                transaction.addToBackStack("ItemsFragment");
                transaction.commit();
                break;
            case 7:
                BankFragment bankFragment = new BankFragment();
                transaction.replace(R.id.mainContent, bankFragment, "BankFragment");
                transaction.addToBackStack("BankFragment");
                transaction.commit();
                break;
            case 8:
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
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle(getResources().getString(R.string.logout_confirmation));
                dialog.setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new AsyncTask() {

                            @Override
                            protected Object doInBackground(Object[] params) {
                                String logout_url = "http://0llum.bplaced.net/ecoCrats/Logout.php";
                                String username = user.username;

                                try {
                                    URL url = new URL(logout_url);
                                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                                    httpURLConnection.setRequestMethod("POST");
                                    httpURLConnection.setDoOutput(true);
                                    httpURLConnection.setDoInput(true);
                                    OutputStream outputStream = httpURLConnection.getOutputStream();
                                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                                    String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
                                    bufferedWriter.write(data);
                                    bufferedWriter.flush();
                                    bufferedWriter.close();
                                    outputStream.close();
                                    InputStream inputStream = httpURLConnection.getInputStream();
                                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                                    String response = "";
                                    String line = "";

                                    while ((line = bufferedReader.readLine()) != null) {
                                        response += line;
                                    }

                                    bufferedReader.close();
                                    inputStream.close();
                                    httpURLConnection.disconnect();

                                    return response.trim();

                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                return null;
                            }

                            @Override
                            protected void onPostExecute(Object o) {
                                setUserOffline(user);
                                userLocalStore.clearUserData();
                                userLocalStore.setUserLoggedIn(false);

                                finish();
                                startActivity(new Intent(getApplicationContext(), Login.class));
                                overridePendingTransition(0, 0);
                            }
                        }.execute();
                    }
                });
                dialog.setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.create();
                dialog.show();

                break;
            case R.id.settings:
                FragmentManager.BackStackEntry currentFragment = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1);
                String current = currentFragment.getName();

                if (current.equals("SettingsFragment")) {
                    fragmentManager.popBackStack();
                } else {
                    SettingsFragment settingsFragment = new SettingsFragment();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.mainContent, settingsFragment, "SettingsFragment");
                    transaction.addToBackStack("SettingsFragment");
                    transaction.commit();
                    actionBar.setTitle(R.string.settings_title);
                }
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
        } else if (current.equals("FriendlistFragment") ||
                current.equals("MessagesInboxFragment") ||
                current.equals("MessagesOutboxFragment") ||
                current.equals("CountriesFragment") ||
                current.equals("StoresFragment") ||
                current.equals("BankFragment") ||
                current.equals("ItemsFragment")) {
            ProfileFragment profileFragment = new ProfileFragment();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.mainContent, profileFragment, "ProfileFragment");
            transaction.addToBackStack("ProfileFragment");
            transaction.commit();
            return;
        } else if (current.equals("MarketSalesFragment") ||
                current.equals("MarketPurchasesFragment")) {
            ItemsFragment itemsFragment = new ItemsFragment();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.mainContent, itemsFragment, "ItemsFragment");
            transaction.addToBackStack("ItemsFragment");
            transaction.commit();
            return;
        } else
         {
            super.onBackPressed();
        }
    }


    public void setUserOnline(User user) {
        String method = "online";
        BackgroundTaskStatus backgroundTaskStatus = new BackgroundTaskStatus(this);
        backgroundTaskStatus.execute(method, user.username);
    }

    public void setUserOffline(User user) {
        String method = "offline";
        BackgroundTaskStatus backgroundTaskStatus = new BackgroundTaskStatus(this);
        backgroundTaskStatus.execute(method, user.username);
    }

    public void setUserAFK(User user) {
        String method = "afk";
        BackgroundTaskStatus backgroundTaskStatus = new BackgroundTaskStatus(this);
        backgroundTaskStatus.execute(method, user.username);
    }

}
