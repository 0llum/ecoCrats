package com.ollum.ecoCrats;

public class Message {
    String sender, receiver, subject, message, time;

    public Message(String sender, String receiver, String subject, String message, String time) {
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.message = message;
        this.time = time;
    }

    public Message(String sender, String subject, String message, String time) {
        this.sender = sender;
        this.subject = subject;
        this.message = message;
        this.time = time;
    }

    public Message(String sender, String subject, String time) {
        this.sender = sender;
        this.subject = subject;
        this.time = time;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }
}
