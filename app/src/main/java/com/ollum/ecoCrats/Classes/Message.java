package com.ollum.ecoCrats.Classes;

public class Message {
    String sender, receiver, subject, message, time;
    int ID, seen;

    public Message(int ID, String sender, String receiver, String subject, String message, String time) {
        this.ID = ID;
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.message = message;
        this.time = time;
    }

    public Message(int ID, String sender, String subject, String message, String time, int seen) {
        this.ID = ID;
        this.sender = sender;
        this.subject = subject;
        this.message = message;
        this.time = time;
        this.seen = seen;
    }

    public Message(int ID, String sender, String subject, String time) {
        this.ID = ID;
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

    public int getID() {
        return ID;
    }

    public int getSeen() {
        return seen;
    }
}
