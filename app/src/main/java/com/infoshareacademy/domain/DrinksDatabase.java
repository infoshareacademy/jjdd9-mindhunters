package com.infoshareacademy.domain;

import java.util.*;

public final class DrinksDatabase {

    private static DrinksDatabase INSTANCE;
    private List<DrinkJson> drinks;

    private DrinksDatabase() {
        drinks = new ArrayList<>();
    }

    public static DrinksDatabase getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new DrinksDatabase();
        }
        return INSTANCE;
    }

    public void addAllDrinks(List<DrinkJson> drinkRecipes) {
        drinks.addAll(drinkRecipes);
    }

    public void addDrink(DrinkJson drinkRecipe) {
        drinks.add(drinkRecipe);
    }

    public List<DrinkJson> getDrinks() {
        return this.drinks;
    }



}