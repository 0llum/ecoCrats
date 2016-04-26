package com.ollum.ecoCrats.BackgroundTasks;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.ollum.ecoCrats.Activities.MainActivity;
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

public class BackgroundTaskLatestMessage extends AsyncTask<String, Message, Message> {
    public static NotificationManager mNotificationManager;
    Context ctx;
    ArrayList<Message> arrayList = new ArrayList<>();
    String json_String = "http://0llum.bplaced.net/ecoCrats/DisplayLatestMessage.php";

    public BackgroundTaskLatestMessage(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected Message doInBackground(String... params) {
        String username = params[0];
        Message message = new Message(0, "", "", "", "", 0);

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
                message = new Message(JO.getInt("ID"), JO.getString("username"), JO.getString("Subject"), JO.getString("Message"), JO.getString("Time"), JO.getInt("Seen"));
                publishProgress(message);
            }

            Log.d("JSON-STRING", json_string);
            return message;

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
    }

    @Override
    protected void onPostExecute(Message message) {
        final int notifyID = 9001;
        Intent resultIntent = new Intent(ctx, MainActivity.class);
        resultIntent.putExtra("fragment", "MessagesInboxFragment");
        resultIntent.putExtra("ID", message.getID());
        resultIntent.putExtra("Sender", message.getSender());
        resultIntent.putExtra("Subject", message.getSubject());
        resultIntent.putExtra("Message", message.getMessage());
        resultIntent.putExtra("Time", message.getTime());
        PendingIntent resultPendingIntent = PendingIntent.getActivity(ctx, 0, resultIntent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder mNotifyBuilder;

        mNotificationManager = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);

        mNotifyBuilder = new NotificationCompat.Builder(ctx)
                .setContentTitle(message.getSender())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLights(Color.BLUE, 100, 900);

        // Set pending intent
        mNotifyBuilder.setContentIntent(resultPendingIntent);

        // Set Vibrate, Sound and Light
        int defaults = 0;
        defaults = defaults | Notification.DEFAULT_VIBRATE;
        defaults = defaults | Notification.DEFAULT_SOUND;

        mNotifyBuilder.setDefaults(defaults);
        // Set the content for Notification
        mNotifyBuilder.setContentText(message.getSubject());
        // Set autocancel
        mNotifyBuilder.setAutoCancel(true);
        // Post a notification
        mNotificationManager.notify(notifyID, mNotifyBuilder.build());
        super.onPostExecute(null);
    }
}
