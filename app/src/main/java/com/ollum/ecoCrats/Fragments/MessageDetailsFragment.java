package com.ollum.ecoCrats.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ollum.ecoCrats.Activities.MainActivity;
import com.ollum.ecoCrats.Adapters.MessagesAdapter;
import com.ollum.ecoCrats.BackgroundTasks.BackgroundTaskStatus;
import com.ollum.ecoCrats.R;

public class MessageDetailsFragment extends Fragment {
    public static Bundle bundle;
    public static TextView tvSender, tvSubject, tvMessage;
    String sender, subject, message, time;
    int ID;
    String current;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = MessagesAdapter.bundle;
        Bundle GCMbundle = MainActivity.GCMbundle;

        if (GCMbundle != null) {
            ID = GCMbundle.getInt("ID");
            sender = GCMbundle.getString("sender");
            subject = GCMbundle.getString("subject");
            message = GCMbundle.getString("message");
            time = GCMbundle.getString("time");

            MainActivity.GCMbundle = null;

            if (isOnline()) {
                String method = "seen";
                BackgroundTaskStatus backgroundTaskStatus = new BackgroundTaskStatus(getContext());
                backgroundTaskStatus.execute(method, "" + ID);
            } else {
                Snackbar snackbar = Snackbar.make(MainActivity.coordinatorLayout, R.string.no_internet, Snackbar.LENGTH_LONG);
                TextView tv = (TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
                tv.setTextColor(Color.BLACK);
                snackbar.getView().setBackgroundColor(getResources().getColor(R.color.colorAccent));
                snackbar.show();
            }

        } else if (bundle != null) {
            ID = bundle.getInt("ID");
            sender = bundle.getString("sender");
            subject = bundle.getString("subject");
            message = bundle.getString("message");
            time = bundle.getString("time");

            FragmentManager.BackStackEntry currentFragment = MainActivity.fragmentManager.getBackStackEntryAt(MainActivity.fragmentManager.getBackStackEntryCount() - 1);
            current = currentFragment.getName();

            if (current.equals("MessagesInboxFragment")) {
                if (isOnline()) {
                    String method = "seen";
                    BackgroundTaskStatus backgroundTaskStatus = new BackgroundTaskStatus(getContext());
                    backgroundTaskStatus.execute(method, "" + ID);
                } else {
                    Snackbar snackbar = Snackbar.make(MainActivity.coordinatorLayout, R.string.no_internet, Snackbar.LENGTH_LONG);
                    TextView tv = (TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.BLACK);
                    snackbar.getView().setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    snackbar.show();
                }

            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message_details, container, false);

        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.message_details_title);

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

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
