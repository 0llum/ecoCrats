package com.ollum.ecoCrats.Classes;

public class Store {
    int ID, capacity;
    String region;

    public Store(int ID, String region, int capacity) {
        this.ID = ID;
        this.region = region;
        this.capacity = capacity;
    }

    public Store(String region) {
        this.region = region;
    }

    public int getID() {
        return ID;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getRegion() {
        return region;
    }
}
