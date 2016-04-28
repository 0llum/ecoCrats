package com.ollum.ecoCrats.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.ollum.ecoCrats.BackgroundTasks.BackgroundTask;
import com.ollum.ecoCrats.BackgroundTasks.BackgroundTaskStatus;
import com.ollum.ecoCrats.Classes.User;
import com.ollum.ecoCrats.R;
import com.ollum.ecoCrats.SharedPrefs.UserLocalStore;
import com.ollum.ecoCrats.Utils.Constants;

import java.io.IOException;

public class Login extends AppCompatActivity implements View.OnClickListener {
    String regid;
    GoogleCloudMessaging gcm;

    EditText etUsername, etPassword;
    Button bLogin, bSignUp;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        etUsername = (EditText) findViewById(R.id.login_username);
        etPassword = (EditText) findViewById(R.id.login_password);

        bLogin = (Button) findViewById(R.id.login_login);
        bSignUp = (Button) findViewById(R.id.login_signup);

        bLogin.setOnClickListener(this);
        bSignUp.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_login:
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString();
                User user = new User(username, password);

                if (isOnline()) {
                    logUserIn(user);
                    userLocalStore.storeUserData(user);
                } else {
                    Toast.makeText(this, R.string.no_internet, Toast.LENGTH_LONG).show();
                }

                break;

            case R.id.login_signup:
                startActivity(new Intent(this, SignUp.class));
                overridePendingTransition(0, 0);
                break;
        }
    }

    private void logUserIn(final User user) {
        new AsyncTask() {
            @Override
            protected String doInBackground(Object... params) {
                String msg = "";
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
                    }
                    regid = gcm.register(Constants.PROJECT_NUMBER);
                    msg = "Device registered, registration ID=" + regid;

                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();
                }
                return msg;
            }

            @Override
            protected void onPostExecute(Object msg) {
                Log.i("gcm", msg.toString());
                String method = "login";
                BackgroundTask backgroundTask = new BackgroundTask(Login.this);
                backgroundTask.execute(method, user.username, user.password, regid);
            }
        }.execute(null, null, null);
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
