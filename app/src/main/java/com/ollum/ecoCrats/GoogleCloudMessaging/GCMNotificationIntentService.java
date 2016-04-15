package com.ollum.ecoCrats.GoogleCloudMessaging;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.ollum.ecoCrats.Activities.MainActivity;
import com.ollum.ecoCrats.BackgroundTasks.BackgroundTaskLatestMessage;
import com.ollum.ecoCrats.Classes.User;
import com.ollum.ecoCrats.R;
import com.ollum.ecoCrats.SharedPrefs.UserLocalStore;

public class GCMNotificationIntentService extends IntentService {
    Bundle extras;

    // Sets an ID for the notification, so it can be updated
    NotificationCompat.Builder builder;

    public GCMNotificationIntentService() {
        super("GcmIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);

        String messageType = gcm.getMessageType(intent);

        if (!extras.isEmpty()) {
            if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
                sendNotification("Send error: " + extras.toString());
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)) {
                sendNotification("Deleted messages on server: " + extras.toString());
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
                sendNotification("" + extras.get(ApplicationConstants.MSG_KEY));
            }
        }
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }

    private void sendNotification(String msg) {
        UserLocalStore userLocalStore = new UserLocalStore(getApplicationContext());
        User user = userLocalStore.getLoggedInUser();

        BackgroundTaskLatestMessage backgroundTaskLatestMessage = new BackgroundTaskLatestMessage(getApplicationContext());
        backgroundTaskLatestMessage.execute(user.getUsername());
    }
}
