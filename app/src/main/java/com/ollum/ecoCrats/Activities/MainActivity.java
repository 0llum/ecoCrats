package com.ollum.ecoCrats.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.ollum.ecoCrats.Adapters.NavDrawerAdapter;
import com.ollum.ecoCrats.BackgroundTasks.BackgroundTask;
import com.ollum.ecoCrats.BackgroundTasks.BackgroundTaskLatestMessage;
import com.ollum.ecoCrats.BackgroundTasks.BackgroundTaskStatus;
import com.ollum.ecoCrats.Classes.User;
import com.ollum.ecoCrats.Fragments.ActiveTransportFragment;
import com.ollum.ecoCrats.Fragments.AddItemsFragment;
import com.ollum.ecoCrats.Fragments.BankFragment;
import com.ollum.ecoCrats.Fragments.CountriesFragment;
import com.ollum.ecoCrats.Fragments.FriendlistFragment;
import com.ollum.ecoCrats.Fragments.ItemsFragment;
import com.ollum.ecoCrats.Fragments.MessageDetailsFragment;
import com.ollum.ecoCrats.Fragments.MessagesInboxFragment;
import com.ollum.ecoCrats.Fragments.NewMessageFragment;
import com.ollum.ecoCrats.Fragments.NewTransportFragment;
import com.ollum.ecoCrats.Fragments.ProfileFragment;
import com.ollum.ecoCrats.Fragments.RegionDetailsFragment;
import com.ollum.ecoCrats.Fragments.SettingsFragment;
import com.ollum.ecoCrats.Fragments.StoreDetailsFragment;
import com.ollum.ecoCrats.Fragments.StoresFragment;
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
import java.text.NumberFormat;
import java.util.Locale;

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
    public static FloatingActionMenu fabMenu;
    private FloatingActionButton fabMessages;
    private FloatingActionButton fabTransport;
    private FloatingActionButton fabContracts;
    public static Bundle bundle;
    public static Bundle GCMbundle;
    int progress = 0;
    int cost = 0;
    private NavDrawerAdapter navDrawerAdapter;
    private TextView drawerUsername;
    private ImageButton settingsButton, logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);

        actionBar = getSupportActionBar();

        drawerUsername = (TextView) findViewById(R.id.drawer_username);

        navDrawerItems = getResources().getStringArray(R.array.navDrawerItems);
        listView = (ListView) findViewById(R.id.drawerList);
        navDrawerAdapter = new NavDrawerAdapter(this);
        listView.setAdapter(navDrawerAdapter);
        listView.setOnItemClickListener(this);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerListener = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                if (fabMenu.isOpened()) {
                    fabMenu.close(true);
                }
            }

            @Override
            public void onDrawerClosed(View drawerView) {
            }
        };
        drawerLayout.setDrawerListener(drawerListener);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        settingsButton = (ImageButton) findViewById(R.id.settings);
        settingsButton.setOnClickListener(this);

        logoutButton = (ImageButton) findViewById(R.id.logout);
        logoutButton.setOnClickListener(this);

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
                    closeDrawer();
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

            drawerUsername.setText(user.getUsername());

            String fragment = getIntent().getStringExtra("fragment");
            int ID = getIntent().getIntExtra("ID", 0);
            String Sender = getIntent().getStringExtra("Sender");
            String Subject = getIntent().getStringExtra("Subject");
            String Message = getIntent().getStringExtra("Message");
            String Time = getIntent().getStringExtra("Time");

            fragmentManager = getSupportFragmentManager();
            fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
                @Override
                public void onBackStackChanged() {
                    fabMenu.showMenuButton(true);
                }
            });
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            if (fragment != null) {
                if (fragment.equals("MessagesInboxFragment")) {
                    /*MessagesInboxFragment messagesInboxFragment = new MessagesInboxFragment();
                    transaction.replace(R.id.mainContent, messagesInboxFragment, "MessagesInboxFragment");
                    transaction.addToBackStack("MessagesInboxFragment");*/

                    GCMbundle = new Bundle();
                    GCMbundle.putInt("ID", ID);
                    GCMbundle.putString("sender", Sender);
                    GCMbundle.putString("subject", Subject);
                    GCMbundle.putString("message", Message);
                    GCMbundle.putString("time", Time);

                    MessageDetailsFragment messageDetailsFragment = new MessageDetailsFragment();
                    messageDetailsFragment.setArguments(GCMbundle);
                    transaction.replace(R.id.mainContent, messageDetailsFragment, "MessageDetailsFragment");
                    transaction.addToBackStack("MessageDetailsFragment");
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
        closeDrawer();

        if (fabMenu.isOpened()) {
            fabMenu.close(true);
        }

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        switch (v.getId()) {
            case R.id.settings:
                SettingsFragment settingsFragment = new SettingsFragment();
                transaction.replace(R.id.mainContent, settingsFragment, "SettingsFragment");
                transaction.addToBackStack("SettingsFragment");
                transaction.commit();
                break;
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
            case R.id.fabMessages:
                MessagesInboxFragment messagesInboxFragment = new MessagesInboxFragment();
                transaction.replace(R.id.mainContent, messagesInboxFragment, "MessagesInboxFragment");
                transaction.addToBackStack("MessagesInboxFragment");
                transaction.commit();
                break;
            case R.id.fabTransport:
                ActiveTransportFragment activeTransportFragment = new ActiveTransportFragment();
                transaction.replace(R.id.mainContent, activeTransportFragment, "ActiveTransportFragment");
                transaction.addToBackStack("ActiveTransportFragment");
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
            case 3:
                break;
            case 4:
                break;
            case 5:
                ItemsFragment itemsFragment = new ItemsFragment();
                transaction.replace(R.id.mainContent, itemsFragment, "ItemsFragment");
                transaction.addToBackStack("ItemsFragment");
                transaction.commit();
                break;
            case 6:
                CountriesFragment countriesFragment = new CountriesFragment();
                transaction.replace(R.id.mainContent, countriesFragment, "CountriesFragment");
                transaction.addToBackStack("CountriesFragment");
                transaction.commit();
                break;
            case 7:
                BankFragment bankFragment = new BankFragment();
                transaction.replace(R.id.mainContent, bankFragment, "BankFragment");
                transaction.addToBackStack("BankFragment");
                transaction.commit();
                break;
            case 8:
                SettingsFragment settingsFragment = new SettingsFragment();
                transaction.replace(R.id.mainContent, settingsFragment, "SettingsFragment");
                transaction.addToBackStack("SettingsFragment");
                transaction.commit();
                break;
            case 9:
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

        FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();

        switch (item.getItemId()) {
            case R.id.newMessage:
                NewMessageFragment newMessageFragment = new NewMessageFragment();
                transaction.replace(R.id.mainContent, newMessageFragment, "NewMessageFragment");
                transaction.addToBackStack("NewMessageFragment");
                transaction.commit();
                break;
            case R.id.send:
                String receiver = NewMessageFragment.etReceiver.getText().toString().trim();
                String subject = NewMessageFragment.etSubject.getText().toString().trim();
                String message = NewMessageFragment.etMessage.getText().toString().trim();

                if (receiver.equals("")) {
                    Snackbar snackbar = Snackbar.make(MainActivity.coordinatorLayout, R.string.receiver_choose, Snackbar.LENGTH_LONG);
                    snackbar.show();
                } else if (subject.equals("")) {
                    Snackbar snackbar = Snackbar.make(MainActivity.coordinatorLayout, R.string.subject_enter, Snackbar.LENGTH_LONG);
                    snackbar.show();
                } else if (message.equals("")) {
                    Snackbar snackbar = Snackbar.make(MainActivity.coordinatorLayout, R.string.message_enter, Snackbar.LENGTH_LONG);
                    snackbar.show();
                } else {
                    String method = "sendMessage";
                    BackgroundTask backgroundTask = new BackgroundTask(this);
                    backgroundTask.execute(method, NewMessageFragment.sender, receiver, subject, message);
                }

                break;
            case R.id.reply:
                bundle = new Bundle();
                bundle.putString("receiver", MessageDetailsFragment.tvSender.getText().toString());
                bundle.putString("subject", MessageDetailsFragment.tvSubject.getText().toString());

                NewMessageFragment newMessageFragment2 = new NewMessageFragment();
                newMessageFragment2.setArguments(bundle);
                transaction.replace(R.id.mainContent, newMessageFragment2, "NewMessageFragment");
                transaction.addToBackStack("NewMessageFragment");
                transaction.commit();
                break;
            case R.id.buy:
                final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                SeekBar seekBar = new SeekBar(this);
                seekBar.setMax(25);
                seekBar.setKeyProgressIncrement(1);

                dialog.setTitle(R.string.select_area);
                dialog.setMessage("" + progress + ", " + getResources().getString(R.string.forQuantity) + " " + (int) (progress * (1 / Math.sqrt(RegionDetailsFragment.area + 6) * 2500000 - 690)) + " ECOs");
                dialog.setView(seekBar);

                dialog.setPositiveButton(R.string.buy, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String method = "buyArea";
                        BackgroundTask backgroundTask = new BackgroundTask(MainActivity.this);
                        backgroundTask.execute(method, MainActivity.user.getUsername(), "" + RegionDetailsFragment.ID, "" + progress, "" + cost);
                        dialog.dismiss();
                    }
                });
                dialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                final AlertDialog alertDialog = dialog.create();

                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progressV, boolean fromUser) {
                        progress = progressV;
                        cost = (int) (progress * (1 / Math.sqrt(RegionDetailsFragment.area + 6) * 2500000 - 690));
                        alertDialog.setMessage("" + progress + ", " + getResources().getString(R.string.forQuantity) + " " + NumberFormat.getNumberInstance(Locale.GERMAN).format(cost) + " ECOs");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });

                alertDialog.show();
                break;
            case R.id.build:
                AlertDialog.Builder buildDialog = new AlertDialog.Builder(this)
                        .setTitle(R.string.build_store_confirmation)
                        .setMessage("20.000 ECOs")
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                BackgroundTask backgroundTask = new BackgroundTask(MainActivity.this);
                                backgroundTask.execute("buildStore", MainActivity.user.getUsername(), "" + RegionDetailsFragment.ID);
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                buildDialog.create();
                buildDialog.show();
                break;
            case R.id.addItem:
                bundle = new Bundle();
                bundle.putInt("ID", StoreDetailsFragment.ID);

                AddItemsFragment addItemsFragment = new AddItemsFragment();
                addItemsFragment.setArguments(bundle);
                transaction.replace(R.id.mainContent, addItemsFragment, "AddItemsFragment");
                transaction.addToBackStack("AddItemsFragment");
                transaction.commit();
                break;
            case R.id.addFriend:
                final EditText etFriend = new EditText(this);
                etFriend.setInputType(InputType.TYPE_CLASS_TEXT);

                AlertDialog.Builder addFriendDialog = new AlertDialog.Builder(this)
                        .setTitle(R.string.username_enter)
                        .setView(etFriend)
                        .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String friend = etFriend.getText().toString().trim();
                                String method = "addFriend";
                                BackgroundTask backgroundTask = new BackgroundTask(MainActivity.this);
                                backgroundTask.execute(method, MainActivity.user.username, friend);
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                addFriendDialog.create();
                addFriendDialog.show();
                break;
            case R.id.newTransport:
                NewTransportFragment newTransportFragment = new NewTransportFragment();
                transaction.replace(R.id.mainContent, newTransportFragment, "NewTransportFragment");
                transaction.addToBackStack("NewTransportFragment");
                transaction.commit();
                break;
            case R.id.sendTransport:
                if (NewTransportFragment.tvQuantity.getText().equals("0")) {
                    Snackbar snackbar = Snackbar.make(MainActivity.coordinatorLayout, R.string.quantity_0, Snackbar.LENGTH_LONG);
                    snackbar.show();
                } else {
                    double startRegionLatitude = Math.toRadians(Double.parseDouble(NewTransportFragment.startRegionLatitude));
                    double startRegionLongitude = Math.toRadians(Double.parseDouble(NewTransportFragment.startRegionLongitude));
                    double destinationRegionLatitude = Math.toRadians(Double.parseDouble(NewTransportFragment.destinationRegionLatitude));
                    double destinationRegionLongitude = Math.toRadians(Double.parseDouble(NewTransportFragment.destinationRegionLongitude));

                    int r = 6371;
                    int distance = (int)(Math.acos((Math.sin(startRegionLatitude) * Math.sin(destinationRegionLatitude)) + (Math.cos(startRegionLatitude) * Math.cos(destinationRegionLatitude) * Math.cos(destinationRegionLongitude - startRegionLongitude))) * r);
                    final String duration = "" + distance * 60 / 80;

                    AlertDialog.Builder transportDialog = new AlertDialog.Builder(this)
                            .setTitle(R.string.transport_confirmation)
                            .setMessage(getResources().getString(R.string.distance) + ": " + distance + " km\n" + getResources().getString(R.string.duration) + ": " + duration + " min")
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    String quantity = NewTransportFragment.tvQuantity.getText().toString();
                                    BackgroundTask backgroundTask = new BackgroundTask(MainActivity.this);
                                    backgroundTask.execute("transport", NewTransportFragment.startID, NewTransportFragment.destinationID, NewTransportFragment.itemID, quantity, duration);
                                }
                            })
                            .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    transportDialog.create();
                    transportDialog.show();
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

        String method = "updateTransport";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method);
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
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            closeDrawer();
        } else if (fabMenu.isOpened()) {
            fabMenu.close(true);
        } else {
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
                    current.equals("ActiveTransportFragment") ||
                    current.equals("CompletedTransportFragment") ||
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
            } else if (current.equals("MessageDetailsFragment")) {
                MessagesInboxFragment messagesInboxFragment = new MessagesInboxFragment();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.mainContent, messagesInboxFragment, "MessagesInboxFragment");
                transaction.addToBackStack("MessagesInboxFragment");
                transaction.commit();
            } else {
                super.onBackPressed();
            }
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
