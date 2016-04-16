package com.ollum.ecoCrats.Fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ollum.ecoCrats.Activities.MainActivity;
import com.ollum.ecoCrats.BackgroundTasks.BackgroundTask;
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

public class BankFragment extends Fragment implements View.OnClickListener {
    TextView tvDepts;
    EditText etCredit;
    Button lend;
    String Depts, Credit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bank, container, false);

        MainActivity.actionBar.setTitle(R.string.bank_title);

        tvDepts = (TextView) view.findViewById(R.id.bank_depts);
        tvDepts.setText("0");
        etCredit = (EditText) view.findViewById(R.id.bank_credit);

        lend = (Button) view.findViewById(R.id.bank_button_lend);
        lend.setOnClickListener(this);

        BackgroundTaskDepts backgroundTaskDepts = new BackgroundTaskDepts();
        backgroundTaskDepts.execute();

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bank_button_lend:
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle(R.string.credit_confirmation);
                dialog.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Credit = etCredit.getText().toString();

                        BackgroundTaskCredit backgroundTaskCredit = new BackgroundTaskCredit();
                        backgroundTaskCredit.execute();
                    }
                });
                dialog.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
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

    private class BackgroundTaskDepts extends AsyncTask<Void, Void, String> {
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
                URL url = new URL("http://0llum.bplaced.net/ecoCrats/DisplayDepts.php");
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

            Depts = result;
            tvDepts.setText(NumberFormat.getNumberInstance(Locale.GERMAN).format(Integer.parseInt(Depts)));
        }
    }

    private class BackgroundTaskCredit extends AsyncTask<Void, Void, String> {
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
                URL url = new URL("http://0llum.bplaced.net/ecoCrats/TakeUpLoan.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                        URLEncoder.encode("Credit", "UTF-8") + "=" + URLEncoder.encode(Credit, "UTF-8");
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

            Toast.makeText(getContext(), result, Toast.LENGTH_LONG).show();

            BackgroundTaskDepts backgroundTaskDepts = new BackgroundTaskDepts();
            backgroundTaskDepts.execute();
        }
    }
}
