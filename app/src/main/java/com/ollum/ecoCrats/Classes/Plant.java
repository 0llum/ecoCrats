package com.ollum.ecoCrats.Classes;

public class Plant {
    int ID;
    String name, company;

    public Plant(int ID, String name, String company) {
        this.ID = ID;
        this.name = name;
        this.company = company;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }
}
