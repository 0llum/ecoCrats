package com.ollum.ecoCrats.Classes;

public class Item {
    String name, company;
    int ID, quantity;
    double density;

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

    public int getQuantity() {
        return quantity;
    }
}
