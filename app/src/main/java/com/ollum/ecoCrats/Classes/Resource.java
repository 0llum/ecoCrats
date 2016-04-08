package com.ollum.ecoCrats.Classes;

public class Resource {
    String name, company;
    float density;

    public Resource(String name, String company, float density) {
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

    public float getDensity() {
        return density;
    }
}
