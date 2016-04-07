package com.ollum.ecoCrats;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class NewMessageFragment extends Fragment implements View.OnClickListener {
    EditText etReceiver, etSubject, etMessage;
    Button send, cancel;
    String sender, receiver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Bundle bundleMessageDetails = MessageDetailsFragment.bundle;
        Bundle bundleFriendlist = FriendlistAdapter.bundle;

        if (bundleMessageDetails != null) {
            receiver = bundleMessageDetails.getString("receiver");
        } else if (bundleFriendlist != null) {
            receiver = bundleFriendlist.getString("receiver");
        }

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_message, container, false);

        sender = MainActivity.user.username;

        etReceiver = (EditText) view.findViewById(R.id.message_details_sender);
        etSubject = (EditText) view.findViewById(R.id.message_details_subject);
        etMessage = (EditText) view.findViewById(R.id.new_message_message);
        send = (Button) view.findViewById(R.id.new_message_send);
        send.setOnClickListener(this);
        cancel = (Button) view.findViewById(R.id.new_message_cancel);
        cancel.setOnClickListener(this);

        etReceiver.setText(receiver);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.new_message_send:
                String receiver = etReceiver.getText().toString().trim();
                String subject = etSubject.getText().toString().trim();
                String message = etMessage.getText().toString().trim();

                String method = "sendMessage";
                BackgroundTask backgroundTask = new BackgroundTask(getContext());
                backgroundTask.execute(method, sender, receiver, subject, message);
                break;
            case R.id.new_message_cancel:
                MainActivity.fragmentManager.popBackStack();
                break;
        }
    }
}
