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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ollum.ecoCrats.Activities.MainActivity;
import com.ollum.ecoCrats.R;

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

public class ProfileFragment extends Fragment {
    TextView tvWelcome, tvECOs;
    String ECOs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.profile_title);

        tvWelcome = (TextView) view.findViewById(R.id.welcome);
        tvWelcome.setText(MainActivity.user.username);

        tvECOs = (TextView) view.findViewById(R.id.ECOs);

        if (isOnline()) {
            BackgroundTaskECOs backgroundTaskECOs= new BackgroundTaskECOs();
            backgroundTaskECOs.execute();
        } else {
            Snackbar snackbar = Snackbar.make(MainActivity.coordinatorLayout, R.string.no_internet, Snackbar.LENGTH_LONG);
            TextView tv = (TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
            tv.setTextColor(Color.BLACK);
            snackbar.getView().setBackgroundColor(getResources().getColor(R.color.colorAccent));
            snackbar.show();        }

        return view;
    }

    private class BackgroundTaskECOs extends AsyncTask<Void, Void, String>{
        ProgressDialog progressDialog = new ProgressDialog(getContext());

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setCancelable(true);
            progressDialog.setTitle(getActivity().getResources().getString(R.string.progressing));
            progressDialog.setMessage(getActivity().getResources().getString(R.string.please_wait));
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Void[] params) {
            String username = MainActivity.user.username;

            try {
                URL url = new URL("http://0llum.bplaced.net/ecoCrats/DisplayECOs.php");
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
        protected void onPostExecute(String result) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

            if (result != null) {
                ECOs = result;
                tvECOs.setText(NumberFormat.getNumberInstance(Locale.GERMAN).format(Integer.parseInt(ECOs)));
            }

        }
    }
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
