package com.ollum.ecoCrats.Classes;

public class Item {
    String name, company, username, time, region;
    int ID, itemID, quantity, price;
    double density;

    public Item(int ID, int itemID, String name, String company, int quantity, double density) {
        this.ID = ID;
        this.itemID = itemID;
        this.name = name;
        this.company = company;
        this.quantity = quantity;
        this.density = density;
    }

    public Item(int ID, String name, String company, int quantity, double density) {
        this.ID = ID;
        this.name = name;
        this.company = company;
        this.quantity = quantity;
        this.density = density;
    }

    public Item(int ID, String name, String company, double density) {
        this.ID = ID;
        this.name = name;
        this.company = company;
        this.density = density;
    }

    public Item(int ID, String name, String region, String username, int quantity, int price, String time) {
        this.ID = ID;
        this.name = name;
        this.region = region;
        this.username = username;
        this.quantity = quantity;
        this.price = price;
        this.time = time;
    }

    public Item(int ID, String name, String username, int quantity, int price, String time) {
        this.ID = ID;
        this.name = name;
        this.username = username;
        this.quantity = quantity;
        this.price = price;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public double getDensity() {
        return density;
    }

    public int getID() {
        return ID;
    }

    public int getItemID() {
        return itemID;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getUsername() {
        return username;
    }

    public String getTime() {
        return time;
    }

    public String getRegion() {
        return region;
    }

    public int getPrice() {
        return price;
    }
}
