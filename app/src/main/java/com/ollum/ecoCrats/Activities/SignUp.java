package com.ollum.ecoCrats.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ollum.ecoCrats.BackgroundTasks.BackgroundTask;
import com.ollum.ecoCrats.Classes.User;
import com.ollum.ecoCrats.R;

import org.apache.commons.validator.routines.EmailValidator;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    EditText etUsername, etPassword, etPwConfirm, etEmail;
    Button bSignUp, bCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        etUsername = (EditText) findViewById(R.id.signup_username);
        etPassword = (EditText) findViewById(R.id.signup_password);
        etPwConfirm = (EditText) findViewById(R.id.signup_pwconfirm);
        etEmail = (EditText) findViewById(R.id.signup_email);

        bSignUp = (Button) findViewById(R.id.signup_signup);
        bCancel = (Button) findViewById(R.id.signup_cancel);

        bSignUp.setOnClickListener(this);
        bCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signup_signup:
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String pwConfirm = etPwConfirm.getText().toString();
                String email = etEmail.getText().toString();

                if (!(username.length() >= 3)) {
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(SignUp.this);
                    dialogBuilder.setMessage(R.string.username_short);
                    dialogBuilder.setPositiveButton(R.string.ok, null);
                    dialogBuilder.show();
                } else if (username.contains(" ")) {
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(SignUp.this);
                    dialogBuilder.setMessage(R.string.username_spaces);
                    dialogBuilder.setPositiveButton(R.string.ok, null);
                    dialogBuilder.show();
                } else if (!(password.length() >= 6)) {
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(SignUp.this);
                    dialogBuilder.setMessage(R.string.password_short);
                    dialogBuilder.setPositiveButton(R.string.ok, null);
                    dialogBuilder.show();
                } else if (password.contains(" ")) {
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(SignUp.this);
                    dialogBuilder.setMessage(R.string.password_spaces);
                    dialogBuilder.setPositiveButton(R.string.ok, null);
                    dialogBuilder.show();
                } else if (!(password.equals(pwConfirm))) {
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(SignUp.this);
                    dialogBuilder.setMessage(R.string.password_match);
                    dialogBuilder.setPositiveButton(R.string.ok, null);
                    dialogBuilder.show();
                } else if (!(isValidMail(email))) {
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(SignUp.this);
                    dialogBuilder.setMessage(R.string.email_valid);
                    dialogBuilder.setPositiveButton(R.string.ok, null);
                    dialogBuilder.show();
                } else {
                    User user = new User(username, password, email);
                    signUpUser(user);
                }
                break;

            case R.id.signup_cancel:
                finish();
                break;
        }
    }

    private void signUpUser(final User user) {
        if (isOnline()) {
            String method = "signUp";
            BackgroundTask backgroundTask = new BackgroundTask(SignUp.this);
            backgroundTask.execute(method, user.username, user.password, user.email);
        } else {
            Toast.makeText(this, R.string.no_internet, Toast.LENGTH_LONG).show();
        }
    }

    private boolean isValidMail(String email) {
        if (email == null || "".contains(email)) {
            return false;
        }

        email = email.trim();

        EmailValidator ev = EmailValidator.getInstance();
        return ev.isValid(email);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0);
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
