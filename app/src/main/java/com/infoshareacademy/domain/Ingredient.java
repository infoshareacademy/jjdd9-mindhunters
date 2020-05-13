package com.infoshareacademy.domain;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ingredient that = (Ingredient) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(measure, that.measure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, measure);
    }
}
