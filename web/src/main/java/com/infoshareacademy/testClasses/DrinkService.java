package com.infoshareacademy.testClasses;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DrinkService {

    public static void loadDrinkList() {
        DrinksDatabase database = DrinksDatabase.getINSTANCE();
        if (database.getDrinks().isEmpty()) {
            List<Drink> drinkList = new ArrayList<>();
            String fileName = "AllDrinks.json";
            try {
                drinkList.addAll(JsonReader.jsonDrinkReader(fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            database.addAllDrinks(drinkList);
        }
    }
}




