package com.ollum.ecoCrats;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.commons.validator.routines.EmailValidator;

public class Settings extends AppCompatActivity implements View.OnClickListener {

    UserLocalStore userLocalStore;
    User user;

    EditText oldPassword, newPassword, newPWConfirmation, newEmail;
    Button changePassword, changeEmail, deleteAccount;
    android.support.v7.app.ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Settings");

        oldPassword = (EditText) findViewById(R.id.settings_oldPassword);
        newPassword = (EditText) findViewById(R.id.settings_newPassword);
        newPWConfirmation = (EditText) findViewById(R.id.settings_newPWConfirmation);
        newEmail = (EditText) findViewById(R.id.settings_newEmail);

        changePassword = (Button) findViewById(R.id.settings_changePassword);
        changeEmail = (Button) findViewById(R.id.settings_changeEmail);
        deleteAccount = (Button) findViewById(R.id.settings_deleteAccount);

        changePassword.setOnClickListener(this);
        changeEmail.setOnClickListener(this);
        deleteAccount.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);
        user = userLocalStore.getLoggedInUser();

        setUserOnline(user);
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.settings_changePassword:
                String oldPasswordToChange = oldPassword.getText().toString();
                String newPasswordToChange = newPassword.getText().toString();
                String newPasswordToConfirm = newPWConfirmation.getText().toString();

                if (!(newPasswordToChange.length() >= 6)) {
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Settings.this);
                    dialogBuilder.setMessage("New password must be at least 6 characters long");
                    dialogBuilder.setPositiveButton("OK", null);
                    dialogBuilder.show();
                } else if (newPasswordToChange.contains(" ")) {
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Settings.this);
                    dialogBuilder.setMessage("New password must not contain spaces");
                    dialogBuilder.setPositiveButton("OK", null);
                    dialogBuilder.show();
                } else if (!(newPasswordToChange.equals(newPasswordToConfirm))) {
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Settings.this);
                    dialogBuilder.setMessage("New passwords do not match");
                    dialogBuilder.setPositiveButton("OK", null);
                    dialogBuilder.show();
                } else if (newPasswordToChange.equals(oldPasswordToChange)) {
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Settings.this);
                    dialogBuilder.setMessage("New password cannot be the same as the old password");
                    dialogBuilder.setPositiveButton("OK", null);
                    dialogBuilder.show();
                } else {
                    changePassword(oldPasswordToChange, newPasswordToChange);
                }
                break;
            case R.id.settings_changeEmail:
                String emailToChange = newEmail.getText().toString();

                if (!(isValidMail(emailToChange))) {
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Settings.this);
                    dialogBuilder.setMessage("Please enter a valid email address");
                    dialogBuilder.setPositiveButton("OK", null);
                    dialogBuilder.show();
                } else {
                    changeEmail(emailToChange);
                }
                break;
            case R.id.settings_deleteAccount:
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Settings.this);
                dialogBuilder.setMessage("Are you sure that you want to delete your account?");
                dialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteAccount();
                    }
                });
                dialogBuilder.setNegativeButton("No", null);
                dialogBuilder.show();
                break;
        }
    }

    private void changePassword(String oldPassword, String newPassword) {
        String method = "changePassword";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method, user.username, oldPassword, newPassword);
    }

    private void changeEmail(String newEmail) {
        String method = "changeEmail";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method, user.username, newEmail);
    }

    private void deleteAccount() {
        String method = "deleteAccount";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method, user.username);
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
