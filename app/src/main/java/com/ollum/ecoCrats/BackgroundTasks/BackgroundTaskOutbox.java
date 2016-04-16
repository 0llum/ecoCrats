package com.ollum.ecoCrats.BackgroundTasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.ollum.ecoCrats.Adapters.MessagesAdapter;
import com.ollum.ecoCrats.Classes.Message;
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

public class BackgroundTaskOutbox extends AsyncTask<String, Message, Void> {

    Context ctx;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Message> arrayList = new ArrayList<>();
    ProgressDialog progressDialog;
    String json_String = "http://0llum.bplaced.net/ecoCrats/DisplayOutbox.php";

    public BackgroundTaskOutbox(Context ctx, RecyclerView recyclerView) {
        this.ctx = ctx;
        this.recyclerView = recyclerView;
        progressDialog = new ProgressDialog(ctx);
    }

    @Override
    protected void onPreExecute() {
        layoutManager = new LinearLayoutManager(ctx);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new MessagesAdapter(arrayList, ctx);
        recyclerView.setAdapter(adapter);

        progressDialog.setCancelable(true);
        progressDialog.setTitle(ctx.getResources().getString(R.string.progressing));
        progressDialog.setMessage(ctx.getResources().getString(R.string.please_wait));
        progressDialog.show();
    }

    @Override
    protected Void doInBackground(String... params) {
        String username = params[0];

        try {
            URL url = new URL(json_String);
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
                Message message = new Message(JO.getInt("ID"), JO.getString("username"), JO.getString("Subject"), JO.getString("Message"), JO.getString("Time"), JO.getInt("Seen"));
                publishProgress(message);
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
    protected void onProgressUpdate(Message... values) {
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
