package com.ollum.ecoCrats.Classes;

public class Store {
    int ID, regionID, capacity;
    double regionLatitude, regionLongitude;
    String region;

    public Store(int ID, String region, int capacity) {
        this.ID = ID;
        this.region = region;
        this.capacity = capacity;
    }

    public Store(String region) {
        this.region = region;
    }

    public Store(int ID) {
        this.ID = ID;
    }

    public Store(int ID, double regionLatitude, double regionLongitude, String region, int capacity) {
        this.ID = ID;
        this.regionLatitude = regionLatitude;
        this.regionLongitude = regionLongitude;
        this.region = region;
        this.capacity = capacity;
    }

    public int getID() {
        return ID;
    }

    public int getRegionID() {
        return regionID;
    }

    public double getRegionLatitude() {
        return regionLatitude;
    }

    public double getRegionLongitude() {
        return regionLongitude;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getRegion() {
        return region;
    }
}
