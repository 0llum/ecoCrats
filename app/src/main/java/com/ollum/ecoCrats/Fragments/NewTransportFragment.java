package com.ollum.ecoCrats.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.ollum.ecoCrats.Activities.MainActivity;
import com.ollum.ecoCrats.Adapters.SpinnerFriendsAdapter;
import com.ollum.ecoCrats.Adapters.SpinnerItemsAdapter;
import com.ollum.ecoCrats.Adapters.SpinnerStoresAdapter;
import com.ollum.ecoCrats.Adapters.StoreDetailsAdapter;
import com.ollum.ecoCrats.Classes.Item;
import com.ollum.ecoCrats.Classes.Store;
import com.ollum.ecoCrats.Classes.User;
import com.ollum.ecoCrats.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import java.util.ArrayList;

public class NewTransportFragment extends Fragment {

    public static String startID, itemID, friendID, destinationID, quantity, startRegionLatitude, startRegionLongitude, destinationRegionLatitude, destinationRegionLongitude;
    public static TextView tvQuantity;
    ArrayList<Store> startStores = new ArrayList<>();
    ArrayList<Item> items = new ArrayList<>();
    ArrayList<User> friends = new ArrayList<>();
    ArrayList<Store> destinationStores = new ArrayList<>();
    SpinnerStoresAdapter spinnerStartAdapter, spinnerDestinationAdapter;
    SpinnerFriendsAdapter spinnerFriendsAdapter;
    SpinnerItemsAdapter spinnerItemsAdapter;
    Spinner spinnerStart, spinnerItem, spinnerFriend, spinnerDestination;
    int progress;
    SeekBar seekBar;
    int storeID, item_ID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Bundle bundle = StoreDetailsAdapter.bundle;

        if (bundle != null) {
            storeID = bundle.getInt("storeID");
            item_ID = bundle.getInt("itemID");
        }

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transport, container, false);

        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.transport_title);

        tvQuantity = (TextView) view.findViewById(R.id.transport_quantity);
        tvQuantity.setText("" + 0);

        seekBar = (SeekBar) view.findViewById(R.id.transport_seekBar);
        seekBar.setKeyProgressIncrement(1);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressV, boolean fromUser) {
                progress = progressV;
                tvQuantity.setText("" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        spinnerStart = (Spinner) view.findViewById(R.id.transport_spinner_start);
        spinnerItem = (Spinner) view.findViewById(R.id.transport_spinner_item);
        spinnerFriend = (Spinner) view.findViewById(R.id.transport_spinner_friend);
        spinnerDestination = (Spinner) view.findViewById(R.id.transport_spinner_destination);

        spinnerStartAdapter = new SpinnerStoresAdapter(getContext(), R.id.txt, startStores);
        spinnerItemsAdapter = new SpinnerItemsAdapter(getContext(), R.id.txt, items);
        spinnerFriendsAdapter = new SpinnerFriendsAdapter(getContext(), R.id.txt, friends);
        spinnerDestinationAdapter = new SpinnerStoresAdapter(getContext(), R.id.txt, destinationStores);

        spinnerStart.setAdapter(spinnerStartAdapter);
        spinnerItem.setAdapter(spinnerItemsAdapter);
        spinnerFriend.setAdapter(spinnerFriendsAdapter);
        spinnerDestination.setAdapter(spinnerDestinationAdapter);

        spinnerStart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Store store = spinnerStartAdapter.getItem(position);
                startID = "" + store.getID();
                startRegionLatitude = "" + store.getRegionLatitude();
                startRegionLongitude = "" + store.getRegionLongitude();

                if (isOnline()) {
                    BackgroundTaskItem backgroundTaskItem = new BackgroundTaskItem();
                    backgroundTaskItem.execute(startID);
                } else {
                    Snackbar snackbar = Snackbar.make(MainActivity.coordinatorLayout, R.string.no_internet, Snackbar.LENGTH_LONG);
                    TextView tv = (TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.BLACK);
                    snackbar.getView().setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    snackbar.show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerItem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Item item = spinnerItemsAdapter.getItem(position);
                itemID = "" + item.getID();

                seekBar.setMax(item.getQuantity());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerFriend.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                User user = spinnerFriendsAdapter.getItem(position);
                friendID = user.getUsername();

                if (isOnline()) {
                    BackgroundTaskDestination backgroundTaskDestination = new BackgroundTaskDestination();
                    backgroundTaskDestination.execute(friendID);
                } else {
                    Snackbar snackbar = Snackbar.make(MainActivity.coordinatorLayout, R.string.no_internet, Snackbar.LENGTH_LONG);
                    TextView tv = (TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.BLACK);
                    snackbar.getView().setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    snackbar.show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerDestination.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Store store = spinnerDestinationAdapter.getItem(position);
                destinationID = "" + store.getID();
                destinationRegionLatitude = "" + store.getRegionLatitude();
                destinationRegionLongitude = "" + store.getRegionLongitude();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (isOnline()) {
            BackgroundTaskStart backgroundTaskStart = new BackgroundTaskStart();
            backgroundTaskStart.execute(MainActivity.user.getUsername());

            BackgroundTaskFriend backgroundTaskFriend = new BackgroundTaskFriend();
            backgroundTaskFriend.execute(MainActivity.user.getUsername());
        } else {
            Snackbar snackbar = Snackbar.make(MainActivity.coordinatorLayout, R.string.no_internet, Snackbar.LENGTH_LONG);
            TextView tv = (TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
            tv.setTextColor(Color.BLACK);
            snackbar.getView().setBackgroundColor(getResources().getColor(R.color.colorAccent));
            snackbar.show();
        }

        return view;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem sendTransport = menu.findItem(R.id.sendTransport);
        sendTransport.setVisible(true);
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private class BackgroundTaskStart extends AsyncTask<String, Store, Void> {
        ProgressDialog progressDialog = new ProgressDialog(getContext());

        @Override
        protected void onPreExecute() {
            progressDialog.setCancelable(true);
            progressDialog.setTitle("Progressing");
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(String... params) {
            String username = params[0];

            try {
                URL url = new URL("http://0llum.bplaced.net/ecoCrats/TransportStores.php");
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
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }

                httpURLConnection.disconnect();
                String json_string = stringBuilder.toString().trim();
                JSONObject jsonObject = new JSONObject(json_string);
                JSONArray jsonArray = jsonObject.getJSONArray("server_response");
                int count = 0;

                while (count < jsonArray.length()) {
                    JSONObject JO = jsonArray.getJSONObject(count);
                    count++;
                    Store store = new Store(JO.getInt("ID"), JO.getDouble("Latitude"), JO.getDouble("Longitude"), JO.getString("Name"), JO.getInt("Capacity"));
                    publishProgress(store);
                }

                Log.d("JSON-STRING", json_string);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Store... values) {
            startStores.add(values[0]);
            spinnerStartAdapter.notifyDataSetChanged();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

            for (int i = 0; i < startStores.size(); i++) {
                if (startStores.get(i).getID() == storeID) {
                    spinnerStart.setSelection(spinnerStartAdapter.getPosition(startStores.get(i)));
                }
            }
        }
    }

    private class BackgroundTaskItem extends AsyncTask<String, Item, Void> {
        ProgressDialog progressDialog = new ProgressDialog(getContext());

        @Override
        protected void onPreExecute() {
            items.clear();
            progressDialog.setCancelable(true);
            progressDialog.setTitle("Progressing");
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(String... params) {
            String Store_ID = params[0];

            try {
                URL url = new URL("http://0llum.bplaced.net/ecoCrats/DisplayStoreDetails.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("Store_ID", "UTF-8") + "=" + URLEncoder.encode(Store_ID, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }

                httpURLConnection.disconnect();
                String json_string = stringBuilder.toString().trim();
                JSONObject jsonObject = new JSONObject(json_string);
                JSONArray jsonArray = jsonObject.getJSONArray("server_response");
                int count = 0;

                while (count < jsonArray.length()) {
                    JSONObject JO = jsonArray.getJSONObject(count);
                    count++;
                    Item item = new Item(JO.getInt("ID"), JO.getString("Name"), JO.getString("Company"), JO.getInt("Quantity"), JO.getDouble("Density"));
                    publishProgress(item);
                }

                Log.d("JSON-STRING", json_string);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Item... values) {
            items.add(values[0]);
            spinnerItemsAdapter.notifyDataSetChanged();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getID() == item_ID) {
                    spinnerItem.setSelection(spinnerItemsAdapter.getPosition(items.get(i)));
                }
            }
        }
    }

    private class BackgroundTaskFriend extends AsyncTask<String, User, Void> {
        ProgressDialog progressDialog = new ProgressDialog(getContext());

        @Override
        protected void onPreExecute() {
            progressDialog.setCancelable(true);
            progressDialog.setTitle("Progressing");
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(String... params) {
            String username = params[0];

            try {
                URL url = new URL("http://0llum.bplaced.net/ecoCrats/DisplayFriends.php");
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
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }

                httpURLConnection.disconnect();
                String json_string = stringBuilder.toString().trim();
                JSONObject jsonObject = new JSONObject(json_string);
                JSONArray jsonArray = jsonObject.getJSONArray("server_response");
                int count = 0;

                while (count < jsonArray.length()) {
                    JSONObject JO = jsonArray.getJSONObject(count);
                    count++;
                    User user = new User(JO.getString("username"), JO.getInt("status"), JO.getString("lastOnline"));
                    publishProgress(user);
                }

                Log.d("JSON-STRING", json_string);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(User... values) {
            friends.add(values[0]);
            spinnerFriendsAdapter.notifyDataSetChanged();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }

    private class BackgroundTaskDestination extends AsyncTask<String, Store, Void> {
        ProgressDialog progressDialog = new ProgressDialog(getContext());

        @Override
        protected void onPreExecute() {
            destinationStores.clear();
            progressDialog.setCancelable(true);
            progressDialog.setTitle("Progressing");
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(String... params) {
            String username = params[0];

            try {
                URL url = new URL("http://0llum.bplaced.net/ecoCrats/TransportStores.php");
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
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }

                httpURLConnection.disconnect();
                String json_string = stringBuilder.toString().trim();
                JSONObject jsonObject = new JSONObject(json_string);
                JSONArray jsonArray = jsonObject.getJSONArray("server_response");
                int count = 0;

                while (count < jsonArray.length()) {
                    JSONObject JO = jsonArray.getJSONObject(count);
                    count++;
                    Store store = new Store(JO.getInt("ID"), JO.getDouble("Latitude"), JO.getDouble("Longitude"), JO.getString("Name"), JO.getInt("Capacity"));
                    publishProgress(store);
                }

                Log.d("JSON-STRING", json_string);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Store... values) {
            destinationStores.add(values[0]);
            spinnerDestinationAdapter.notifyDataSetChanged();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }
}
