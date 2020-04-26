package com.infoshareacademy.domain;

import com.infoshareacademy.services.JsonReader;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class DrinksDatabase {

    private static DrinksDatabase INSTANCE;
    private String info = "Initial info class";
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

    public void addDrinks(List<Drink> drinkRecipes) {
        drinks.addAll(drinkRecipes);
    }

    public List<Drink> getDrinks() throws IOException {
        List<Drink> drinks = new ArrayList<>();
        for (int i=0; i<=4; i++){
            char letter = (char) (97 + i) ;
            String fileName = "LIST_" + letter + "LETTER.json";

            drinks.addAll(JsonReader.objectMapper(fileName));

            addDrinks(drinks);
        }
        return this.drinks;
    }
}