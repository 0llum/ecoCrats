package com.ollum.ecoCrats;

public class City {
    String name, country;
    float latitude, longitude;

    public City(String name, String country, float latitude, float longitude) {
        this.name = name;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }
}
