package com.ollum.ecoCrats.Classes;

public class Transport {
    int ID, quantity, minutes;
    String item, startRegion, destinationRegion, sender, receiver, time;

    public Transport(int ID, String startRegion, String destinationRegion, String sender, String receiver, String item, int quantity, int minutes, String time) {
        this.ID = ID;
        this.startRegion = startRegion;
        this.destinationRegion = destinationRegion;
        this.sender = sender;
        this.receiver = receiver;
        this.item = item;
        this.quantity = quantity;
        this.minutes = minutes;
        this.time = time;
    }

    public int getID() {
        return ID;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getMinutes() {
        return minutes;
    }

    public String getItem() {
        return item;
    }

    public String getStartRegion() {
        return startRegion;
    }

    public String getDestinationRegion() {
        return destinationRegion;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getTime() {
        return time;
    }
}
