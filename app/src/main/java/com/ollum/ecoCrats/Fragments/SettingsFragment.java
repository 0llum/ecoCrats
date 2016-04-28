package com.ollum.ecoCrats.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ollum.ecoCrats.Activities.MainActivity;
import com.ollum.ecoCrats.BackgroundTasks.BackgroundTask;
import com.ollum.ecoCrats.R;

import org.apache.commons.validator.routines.EmailValidator;

public class SettingsFragment extends Fragment implements View.OnClickListener {
    EditText oldPassword, newPassword, newPWConfirmation, newEmail;
    Button changePassword, changeEmail, deleteAccount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.settings_title);

        oldPassword = (EditText) view.findViewById(R.id.settings_oldPassword);
        newPassword = (EditText) view.findViewById(R.id.settings_newPassword);
        newPWConfirmation = (EditText) view.findViewById(R.id.settings_newPWConfirmation);
        newEmail = (EditText) view.findViewById(R.id.settings_newEmail);

        changePassword = (Button) view.findViewById(R.id.settings_changePassword);
        changeEmail = (Button) view.findViewById(R.id.settings_changeEmail);
        deleteAccount = (Button) view.findViewById(R.id.settings_deleteAccount);

        changePassword.setOnClickListener(this);
        changeEmail.setOnClickListener(this);
        deleteAccount.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.settings_changePassword:
                String oldPasswordToChange = oldPassword.getText().toString();
                String newPasswordToChange = newPassword.getText().toString();
                String newPasswordToConfirm = newPWConfirmation.getText().toString();

                if (!(newPasswordToChange.length() >= 6)) {
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
                    dialogBuilder.setMessage(R.string.new_password_short);
                    dialogBuilder.setPositiveButton(R.string.ok, null);
                    dialogBuilder.show();
                } else if (newPasswordToChange.contains(" ")) {
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
                    dialogBuilder.setMessage(R.string.new_password_spaces);
                    dialogBuilder.setPositiveButton(R.string.ok, null);
                    dialogBuilder.show();
                } else if (!(newPasswordToChange.equals(newPasswordToConfirm))) {
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
                    dialogBuilder.setMessage(R.string.new_password_match);
                    dialogBuilder.setPositiveButton(R.string.ok, null);
                    dialogBuilder.show();
                } else if (newPasswordToChange.equals(oldPasswordToChange)) {
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
                    dialogBuilder.setMessage(R.string.new_password_same);
                    dialogBuilder.setPositiveButton(R.string.ok, null);
                    dialogBuilder.show();
                } else {
                    changePassword(oldPasswordToChange, newPasswordToChange);
                }
                break;
            case R.id.settings_changeEmail:
                String emailToChange = newEmail.getText().toString();

                if (!(isValidMail(emailToChange))) {
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
                    dialogBuilder.setMessage(R.string.email_valid);
                    dialogBuilder.setPositiveButton(R.string.ok, null);
                    dialogBuilder.show();
                } else {
                    changeEmail(emailToChange);
                }
                break;
            case R.id.settings_deleteAccount:
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
                dialogBuilder.setMessage(R.string.delete_account);
                dialogBuilder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteAccount();
                    }
                });
                dialogBuilder.setNegativeButton(R.string.no, null);
                dialogBuilder.show();
                break;
        }
    }

    private void changePassword(String oldPassword, String newPassword) {
        if (isOnline()) {
            String method = "changePassword";
            BackgroundTask backgroundTask = new BackgroundTask(getActivity());
            backgroundTask.execute(method, MainActivity.user.username, oldPassword, newPassword);
        } else {
            Snackbar snackbar = Snackbar.make(MainActivity.coordinatorLayout, R.string.no_internet, Snackbar.LENGTH_LONG);
            TextView tv = (TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
            tv.setTextColor(Color.BLACK);
            snackbar.getView().setBackgroundColor(getResources().getColor(R.color.colorAccent));
            snackbar.show();
        }
    }

    private void changeEmail(String newEmail) {
        if (isOnline()) {
            String method = "changeEmail";
            BackgroundTask backgroundTask = new BackgroundTask(getActivity());
            backgroundTask.execute(method, MainActivity.user.username, newEmail);
        } else {
            Snackbar snackbar = Snackbar.make(MainActivity.coordinatorLayout, R.string.no_internet, Snackbar.LENGTH_LONG);
            TextView tv = (TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
            tv.setTextColor(Color.BLACK);
            snackbar.getView().setBackgroundColor(getResources().getColor(R.color.colorAccent));
            snackbar.show();
        }
    }

    private void deleteAccount() {
        if (isOnline()) {
            String method = "deleteAccount";
            BackgroundTask backgroundTask = new BackgroundTask(getActivity());
            backgroundTask.execute(method, MainActivity.user.username);
        } else {
            Snackbar snackbar = Snackbar.make(MainActivity.coordinatorLayout, R.string.no_internet, Snackbar.LENGTH_LONG);
            TextView tv = (TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
            tv.setTextColor(Color.BLACK);
            snackbar.getView().setBackgroundColor(getResources().getColor(R.color.colorAccent));
            snackbar.show();
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

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
