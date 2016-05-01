package com.ollum.ecoCrats.Classes;

public class Company {
    int ID, companyID, level, regionID, userID;
    String name, region, user;

    public Company(int ID, int companyID, String name, String region, int level) {
        this.ID = ID;
        this.companyID = companyID;
        this.name = name;
        this.region = region;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    public int getCompanyID() {
        return companyID;
    }

    public int getLevel() {
        return level;
    }

    public int getRegionID() {
        return regionID;
    }

    public int getUserID() {
        return userID;
    }

    public String getRegion() {
        return region;
    }

    public String getUser() {
        return user;
    }
}
