package com.ollum.ecoCrats.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ollum.ecoCrats.BackgroundTasks.BackgroundTask;
import com.ollum.ecoCrats.Activities.MainActivity;
import com.ollum.ecoCrats.R;

import org.apache.commons.validator.routines.EmailValidator;

public class SettingsFragment extends Fragment implements View.OnClickListener {
    EditText oldPassword, newPassword, newPWConfirmation, newEmail;
    Button changePassword, changeEmail, deleteAccount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        MainActivity.setTitle("Settings");

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
                    dialogBuilder.setMessage("New password must be at least 6 characters long");
                    dialogBuilder.setPositiveButton("OK", null);
                    dialogBuilder.show();
                } else if (newPasswordToChange.contains(" ")) {
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
                    dialogBuilder.setMessage("New password must not contain spaces");
                    dialogBuilder.setPositiveButton("OK", null);
                    dialogBuilder.show();
                } else if (!(newPasswordToChange.equals(newPasswordToConfirm))) {
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
                    dialogBuilder.setMessage("New passwords do not match");
                    dialogBuilder.setPositiveButton("OK", null);
                    dialogBuilder.show();
                } else if (newPasswordToChange.equals(oldPasswordToChange)) {
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
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
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
                    dialogBuilder.setMessage("Please enter a valid email address");
                    dialogBuilder.setPositiveButton("OK", null);
                    dialogBuilder.show();
                } else {
                    changeEmail(emailToChange);
                }
                break;
            case R.id.settings_deleteAccount:
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
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
        BackgroundTask backgroundTask = new BackgroundTask(getActivity());
        backgroundTask.execute(method, MainActivity.user.username, oldPassword, newPassword);
    }

    private void changeEmail(String newEmail) {
        String method = "changeEmail";
        BackgroundTask backgroundTask = new BackgroundTask(getActivity());
        backgroundTask.execute(method, MainActivity.user.username, newEmail);
    }

    private void deleteAccount() {
        String method = "deleteAccount";
        BackgroundTask backgroundTask = new BackgroundTask(getActivity());
        backgroundTask.execute(method, MainActivity.user.username);
    }

    private boolean isValidMail(String email) {
        if (email == null || "".contains(email)) {
            return false;
        }

        email = email.trim();

        EmailValidator ev = EmailValidator.getInstance();
        return ev.isValid(email);
    }
}
