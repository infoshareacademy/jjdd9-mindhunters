package com.infoshareacademy.testClasses;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public static List<String> getAllCategories(List<Drink> drinkList) {

        return drinkList.stream()
                .map(drink -> drink.getCategoryName())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
}




