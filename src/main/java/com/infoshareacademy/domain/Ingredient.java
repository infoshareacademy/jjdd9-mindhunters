package com.infoshareacademy.domain;

public class Ingredient {

    private String name;
    private String measure;

    public Ingredient(String name, String measure) {
        this.name = name;
        this.measure = measure;
    }

    public Ingredient() {

    }

    public String getName() {
        return name;
    }

    public String getMeasure() {
        return measure;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                ", measure='" + measure + '\'' +
                '}';
    }
}
