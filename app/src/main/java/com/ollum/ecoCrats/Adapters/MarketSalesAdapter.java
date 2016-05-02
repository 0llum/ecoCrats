package com.ollum.ecoCrats.Adapters;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.ollum.ecoCrats.Activities.MainActivity;
import com.ollum.ecoCrats.BackgroundTasks.BackgroundTask;
import com.ollum.ecoCrats.Classes.Item;
import com.ollum.ecoCrats.Classes.Store;
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

public class MarketSalesAdapter extends RecyclerView.Adapter<MarketSalesAdapter.RecyclerViewHolder> {
    public static Bundle bundle;
    ArrayList<Item> arrayList = new ArrayList<>();
    Context ctx;
    public static int progress = 0;
    public static int cost = 0;
    public static int storeID = 0;
    public static int salesID = 0;

    public MarketSalesAdapter(ArrayList<Item> arrayList, Context ctx) {
        this.arrayList = arrayList;
        this.ctx = ctx;
    }

    @Override
    public MarketSalesAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_market_row, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view, ctx, arrayList);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Item item = arrayList.get(position);
        holder.region.setText(item.getRegion());
        holder.username.setText(item.getUsername());
        holder.price.setText(item.getPrice() + "");
        holder.quantity.setText(item.getQuantity() + "");
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView region, username, price, quantity;
        ArrayList<Item> items = new ArrayList<Item>();
        Context ctx;
        Item item;

        public RecyclerViewHolder(View view, Context ctx, ArrayList<Item> items) {
            super(view);
            this.items = items;
            this.ctx = ctx;
            view.setOnClickListener(this);

            region = (TextView) view.findViewById(R.id.display_market_row_region);
            username = (TextView) view.findViewById(R.id.display_market_row_username);
            price = (TextView) view.findViewById(R.id.display_market_row_price);
            quantity = (TextView) view.findViewById(R.id.display_market_row_quantity);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            item = this.items.get(position);

            salesID = item.getID();

            final AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
            final ArrayList<Store> stores = new ArrayList<>();

            LinearLayout layout = new LinearLayout(ctx);
            layout.setOrientation(LinearLayout.VERTICAL);

            SeekBar seekBar = new SeekBar(ctx);
            seekBar.setMax(item.getQuantity());
            seekBar.setKeyProgressIncrement(1);
            layout.addView(seekBar);

            final Spinner spinner = new Spinner(ctx);
            final SpinnerStoresAdapter adapter = new SpinnerStoresAdapter(ctx, R.id.txt, stores);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Store store = adapter.getItem(position);
                    storeID = store.getID();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            layout.addView(spinner);
            class BackgroundTaskStores extends AsyncTask<String, Store, Void> {
                ProgressDialog progressDialog = new ProgressDialog(ctx);

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
                    stores.add(values[0]);
                    adapter.notifyDataSetChanged();
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                }
            }
            BackgroundTaskStores backgroundTaskStores = new BackgroundTaskStores();
            backgroundTaskStores.execute(MainActivity.user.getUsername());

            builder.setTitle(R.string.quantity_select)
                    .setMessage(ctx.getResources().getString(R.string.quantity) + ": " + progress + "\n" + ctx.getResources().getString(R.string.price) + ": " + cost)
                    .setView(layout)
                    .setPositiveButton(R.string.buy, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String method = "buyItem";
                            BackgroundTask backgroundTask = new BackgroundTask(ctx);
                            backgroundTask.execute(method, "" + salesID, "" + storeID, "" + progress, "" + cost);
                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

            final AlertDialog dialog = builder.create();

            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progressV, boolean fromUser) {
                    progress = progressV;
                    cost = progress * item.getPrice();
                    dialog.setMessage(ctx.getResources().getString(R.string.quantity) + ": " + progress + "\n" + ctx.getResources().getString(R.string.price) + ": " + cost);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
            dialog.show();
        }
    }
}
