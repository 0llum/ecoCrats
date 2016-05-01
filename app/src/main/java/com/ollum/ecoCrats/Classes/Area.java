package com.ollum.ecoCrats.Classes;

public class Area {
    int ID, area, regionID;
    String region;

    public Area(int ID, int regionID, String region, int area) {
        this.ID = ID;
        this.regionID = regionID;
        this.region = region;
        this.area = area;
    }

    public int getID() {
        return ID;
    }

    public int getArea() {
        return area;
    }

    public String getRegion() {
        return region;
    }

    public int getRegionID() {
        return regionID;
    }
}
