package com.ollum.ecoCrats.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ollum.ecoCrats.Activities.MainActivity;
import com.ollum.ecoCrats.Adapters.MessagesAdapter;
import com.ollum.ecoCrats.BackgroundTasks.BackgroundTaskStatus;
import com.ollum.ecoCrats.R;

public class MessageDetailsFragment extends Fragment {
    public static Bundle bundle;
    String sender, subject, message, time;
    int ID;
    public static TextView tvSender, tvSubject, tvMessage;
    String current;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = MessagesAdapter.bundle;
        if (bundle != null) {
            ID = bundle.getInt("ID");
            sender = bundle.getString("sender");
            subject = bundle.getString("subject");
            message = bundle.getString("message");
            time = bundle.getString("time");

            FragmentManager.BackStackEntry currentFragment = MainActivity.fragmentManager.getBackStackEntryAt(MainActivity.fragmentManager.getBackStackEntryCount() - 1);
            current = currentFragment.getName();

            if (current.equals("MessagesInboxFragment")) {
                String method = "seen";
                BackgroundTaskStatus backgroundTaskStatus = new BackgroundTaskStatus(getContext());
                backgroundTaskStatus.execute(method, "" + ID);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message_details, container, false);

        setHasOptionsMenu(true);
        MainActivity.actionBar.setTitle(R.string.message_details_title);

        tvSender = (TextView) view.findViewById(R.id.message_details_sender);
        tvSubject = (TextView) view.findViewById(R.id.message_details_subject);
        tvMessage = (TextView) view.findViewById(R.id.message_details_message);

        tvSender.setText(sender);
        tvSubject.setText(subject);
        tvMessage.setText(message);

        return view;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem reply = menu.findItem(R.id.reply);
        reply.setVisible(true);
    }
}
