package com.ollum.ecoCrats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MessageDetails extends AppCompatActivity implements View.OnClickListener {
    UserLocalStore userLocalStore;
    User user;
    android.support.v7.app.ActionBar actionBar;
    String sender, subject, message, time;
    TextView tvSender, tvSubject, tvMessage;
    Button reply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_details);

        tvSender = (TextView) findViewById(R.id.message_details_sender);
        tvSubject = (TextView) findViewById(R.id.message_details_subject);
        tvMessage = (TextView) findViewById(R.id.message_details_message);
        reply = (Button) findViewById(R.id.message_details_reply);
        reply.setOnClickListener(this);

        sender = getIntent().getStringExtra("sender");
        subject = getIntent().getStringExtra("subject");
        message = getIntent().getStringExtra("message");
        time = getIntent().getStringExtra("time");

        tvSender.setText(sender);
        tvSubject.setText(subject);
        tvMessage.setText(message);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Message");

        userLocalStore = new UserLocalStore(this);
        user = userLocalStore.getLoggedInUser();

        setUserOnline(user);
    }

    @Override
     public void onClick(View v) {
        switch (v.getId()) {
            case R.id.message_details_reply:
                Intent intent = new Intent(this, NewMessage.class);
                intent.putExtra("receiver", tvSender.getText());
                this.startActivity(intent);
                overridePendingTransition(0, 0);
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
        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
    }


}
