package com.ollum.ecoCrats.Classes;

public class Region {
    String name, capital, country;
    int area, population;
    float latitude, longitude;

    public Region(String name, String capital, String country, int area, int population, float latitude, float longitude) {
        this.name = name;
        this.capital = capital;
        this.country = country;
        this.area = area;
        this.population = population;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Region(String name, String capital, int area, int population) {
        this.name = name;
        this.capital = capital;
        this.area = area;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public int getArea() {
        return area;
    }

    public int getPopulation() {
        return population;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }
}
