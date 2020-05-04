package com.infoshareacademy.domain;

import java.util.ArrayList;
import java.util.List;

public final class DrinksDatabase {

    private static DrinksDatabase INSTANCE;
    private List<Drink> drinks;

    private DrinksDatabase() {
        drinks = new ArrayList<>();
    }

    public static DrinksDatabase getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new DrinksDatabase();
        }
        return INSTANCE;
    }

    public void addAllDrinks(List<Drink> drinkRecipes) {
        drinks.addAll(drinkRecipes);
    }

    public void addDrink(Drink drinkRecipe) {
        drinks.add(drinkRecipe);
    }

    public List<Drink> getDrinks() {
        return this.drinks;
    }
}