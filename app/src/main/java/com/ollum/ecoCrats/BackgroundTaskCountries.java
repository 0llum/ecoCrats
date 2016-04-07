package com.ollum.ecoCrats;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class BackgroundTaskCountries extends AsyncTask<String, Country, Void> {

    Context ctx;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Country> arrayList = new ArrayList<>();
    ProgressDialog progressDialog;
    String json_String = "http://0llum.bplaced.net/ecoCrats/DisplayCountries.php";

    public BackgroundTaskCountries(Context ctx, RecyclerView recyclerView) {
        this.ctx = ctx;
        this.recyclerView = recyclerView;
        progressDialog = new ProgressDialog(ctx);
    }

    @Override
    protected void onPreExecute() {
        layoutManager = new LinearLayoutManager(ctx);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new CountriesAdapter(arrayList, ctx);
        recyclerView.setAdapter(adapter);

        /*progressDialog.setCancelable(false);
        progressDialog.setTitle("Progressing");
        progressDialog.setMessage("Please wait...");
        progressDialog.show();*/
    }

    @Override
    protected Void doInBackground(String... params) {

        try {
            URL url = new URL(json_String);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
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
                Country country = new Country(JO.getString("Name"), JO.getString("Capital"), JO.getInt("Area"), JO.getInt("Population"));
                publishProgress(country);
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
    protected void onProgressUpdate(Country... values) {
        arrayList.add(values[0]);
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
