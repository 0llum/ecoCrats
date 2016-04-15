package com.ollum.ecoCrats.Activities;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.ollum.ecoCrats.BackgroundTasks.BackgroundTask;
import com.ollum.ecoCrats.R;
import com.ollum.ecoCrats.Classes.User;

import org.apache.commons.validator.routines.EmailValidator;

import java.io.IOException;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    EditText etUsername, etPassword, etPwConfirm, etEmail;
    Button bSignUp, bCancel;
    android.support.v7.app.ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Sign Up");

        etUsername = (EditText) findViewById(R.id.settings_oldPassword);
        etPassword = (EditText) findViewById(R.id.settings_newPassword);
        etPwConfirm = (EditText) findViewById(R.id.signup_pwconfirm);
        etEmail = (EditText) findViewById(R.id.settings_newEmail);

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
                    dialogBuilder.setMessage("Username must be at least 3 characters long");
                    dialogBuilder.setPositiveButton("OK", null);
                    dialogBuilder.show();
                } else if (username.contains(" ")) {
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(SignUp.this);
                    dialogBuilder.setMessage("Username must not contain spaces");
                    dialogBuilder.setPositiveButton("OK", null);
                    dialogBuilder.show();
                } else if (!(password.length() >= 6)) {
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(SignUp.this);
                    dialogBuilder.setMessage("Password must be at least 6 characters long");
                    dialogBuilder.setPositiveButton("OK", null);
                    dialogBuilder.show();
                } else if (password.contains(" ")) {
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(SignUp.this);
                    dialogBuilder.setMessage("Password must not contain spaces");
                    dialogBuilder.setPositiveButton("OK", null);
                    dialogBuilder.show();
                } else if (!(password.equals(pwConfirm))) {
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(SignUp.this);
                    dialogBuilder.setMessage("The passwords do not match");
                    dialogBuilder.setPositiveButton("OK", null);
                    dialogBuilder.show();
                } else if (!(isValidMail(email))) {
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(SignUp.this);
                    dialogBuilder.setMessage("Please enter a valid email address");
                    dialogBuilder.setPositiveButton("OK", null);
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
        String method = "signUp";
        BackgroundTask backgroundTask = new BackgroundTask(SignUp.this);
        backgroundTask.execute(method, user.username, user.password, user.email);
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
}
