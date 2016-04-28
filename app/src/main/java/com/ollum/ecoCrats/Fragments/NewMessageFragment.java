package com.ollum.ecoCrats.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.ollum.ecoCrats.Activities.MainActivity;
import com.ollum.ecoCrats.Adapters.FriendlistAdapter;
import com.ollum.ecoCrats.R;

public class NewMessageFragment extends Fragment {
    public static EditText etReceiver, etSubject, etMessage;
    public static String sender, receiver, subject;
    String current;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        receiver = "";
        subject = "";

        FragmentManager.BackStackEntry currentFragment = MainActivity.fragmentManager.getBackStackEntryAt(MainActivity.fragmentManager.getBackStackEntryCount() - 1);
        current = currentFragment.getName();

        if (current.equals("FriendlistFragment") && FriendlistAdapter.bundle != null) {
            receiver = FriendlistAdapter.bundle.getString("receiver");
        } else if (current.equals("MessageDetailsFragment") && MainActivity.bundle != null) {
            receiver = MainActivity.bundle.getString("receiver");
            subject = "Re: " + MainActivity.bundle.getString("subject");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_message, container, false);

        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.new_message_title);

        sender = MainActivity.user.username;

        etReceiver = (EditText) view.findViewById(R.id.message_details_sender);
        etSubject = (EditText) view.findViewById(R.id.message_details_subject);
        etMessage = (EditText) view.findViewById(R.id.new_message_message);

        etReceiver.setText(receiver);
        etSubject.setText(subject);

        if (receiver.equals("")) {
            etReceiver.requestFocus();
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        } else if (subject.equals("")) {
            etSubject.requestFocus();
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        } else {
            etMessage.requestFocus();
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        }

        return view;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem sendMessage = menu.findItem(R.id.send);
        sendMessage.setVisible(true);
    }
}
