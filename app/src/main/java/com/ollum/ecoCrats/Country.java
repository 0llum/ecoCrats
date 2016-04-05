package com.ollum.ecoCrats;

public class Country {

    String name, capital;
    int area, population;
    float latitude, longitude;

    public Country(String name, String capital, int area, int population, float latitude, float longitude) {
        this.name = name;
        this.capital = capital;
        this.area = area;
        this.population = population;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Country(String name, String capital, int area, int population) {
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
