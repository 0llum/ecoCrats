package com.ollum.ecoCrats.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ollum.ecoCrats.Activities.MainActivity;
import com.ollum.ecoCrats.Adapters.MessagesAdapter;
import com.ollum.ecoCrats.BackgroundTasks.BackgroundTaskStatus;
import com.ollum.ecoCrats.R;

public class MessageDetailsFragment extends Fragment implements View.OnClickListener {
    String sender, subject, message, time;
    int ID;
    TextView tvSender, tvSubject, tvMessage;
    Button reply;
    public static Bundle bundle;
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

        MainActivity.setTitle("Message Details");

        tvSender = (TextView) view.findViewById(R.id.message_details_sender);
        tvSubject = (TextView) view.findViewById(R.id.message_details_subject);
        tvMessage = (TextView) view.findViewById(R.id.message_details_message);
        reply = (Button) view.findViewById(R.id.message_details_reply);
        reply.setOnClickListener(this);

        tvSender.setText(sender);
        tvSubject.setText(subject);
        tvMessage.setText(message);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.message_details_reply:
                bundle = new Bundle();
                bundle.putString("receiver", tvSender.getText().toString());
                bundle.putString("subject", tvSubject.getText().toString());

                NewMessageFragment newMessageFragment = new NewMessageFragment();
                newMessageFragment.setArguments(bundle);
                FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                transaction.replace(R.id.mainContent, newMessageFragment, "NewMessageFragment");
                transaction.addToBackStack("NewMessageFragment");
                transaction.commit();
        }
    }
}
