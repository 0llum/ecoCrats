package com.ollum.ecoCrats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewMessage extends AppCompatActivity implements View.OnClickListener{

    EditText etReceiver, etSubject, etMessage;
    Button send, cancel;
    android.support.v7.app.ActionBar actionBar;
    UserLocalStore userLocalStore;
    User user;
    String sender, receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_message);



        actionBar = getSupportActionBar();
        actionBar.setTitle("New Message");

        userLocalStore = new UserLocalStore(this);
        user = userLocalStore.getLoggedInUser();
        sender = userLocalStore.getLoggedInUser().username;

        etReceiver = (EditText)findViewById(R.id.message_details_sender);
        etSubject = (EditText)findViewById(R.id.message_details_subject);
        etMessage = (EditText)findViewById(R.id.new_message_message);
        send = (Button)findViewById(R.id.new_message_send);
        send.setOnClickListener(this);
        cancel = (Button)findViewById(R.id.new_message_cancel);
        cancel.setOnClickListener(this);

        receiver = getIntent().getStringExtra("receiver");
        if (receiver != null) {
            etReceiver.setText(receiver);
        }

        setUserOnline(user);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.new_message_send:
                String receiver = etReceiver.getText().toString().trim();
                String subject = etSubject.getText().toString().trim();
                String message = etMessage.getText().toString().trim();

                String method = "sendMessage";
                BackgroundTask backgroundTask = new BackgroundTask(this);
                backgroundTask.execute(method, sender, receiver, subject, message);
                break;
            case R.id.new_message_cancel:
                finish();
                overridePendingTransition(0, 0);
                break;
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

    private void setUserOnline(User user) {
        String method = "online";
        BackgroundTaskStatus backgroundTaskStatus = new BackgroundTaskStatus(this);
        backgroundTaskStatus.execute(method, user.username);
    }

    private void setUserOffline(User user) {
        String method = "offline";
        BackgroundTaskStatus backgroundTaskStatus = new BackgroundTaskStatus(this);
        backgroundTaskStatus.execute(method, user.username);
    }

    private void setUserAFK(User user) {
        String method = "afk";
        BackgroundTaskStatus backgroundTaskStatus = new BackgroundTaskStatus(this);
        backgroundTaskStatus.execute(method, user.username);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0);
    }
}
