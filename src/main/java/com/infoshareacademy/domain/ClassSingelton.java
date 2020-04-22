package com.infoshareacademy.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class ClassSingelton {

    private static ClassSingelton INSTANCE;
    private String info = "Initial info class";
    private Set<Drink> drinks;

    private ClassSingelton() {
        drinks = new HashSet();

    }

    public static ClassSingelton getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new ClassSingelton();
        }
        return INSTANCE;
    }

    public void addDrinks(List<Drink> drinkRecipes) {
        drinks.addAll(drinkRecipes);
    }
}
