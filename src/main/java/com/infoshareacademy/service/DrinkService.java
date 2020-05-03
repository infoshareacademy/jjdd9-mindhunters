package com.infoshareacademy.service;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.DrinksDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class DrinkService {

    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    public static void loadDrinkList() {

        DrinksDatabase database = DrinksDatabase.getINSTANCE();

        if (database.getDrinks().isEmpty()) {
            List<Drink> drinks = new ArrayList<>();
            for (int i = 0; i <= 4; i++) {
                char letter = (char) (97 + i);
                String fileName = "LIST_" + letter + "LETTER.json";

                try {
                    drinks.addAll(JsonReader.objectMapper(fileName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            database.addAllDrinks(drinks);
        }
    }

    public static void printAllDrinks(DrinksDatabase database) {

        for (Drink drink : database.getDrinks()) {
            STDOUT.info("\n{}\n *ID: {}, *Category: {}, {};", drink.getDrinkName().toUpperCase(),
                    drink.getDrinkId(), drink.getCategoryName(), drink.getAlcoholStatus());
            STDOUT.info("\n");
        }
    }

    public static List<Integer> getAllDrinkIdNumbers(DrinksDatabase database) {
        List<Integer> idNumbers = new ArrayList<>();
        database.getDrinks().forEach(drink -> idNumbers.add(Integer.parseInt(drink.getDrinkId())));
        return idNumbers;
    }


    public static void printAllCategories(DrinksDatabase database) {
        List<String> categories = getAllCategories(database);
        int counter = 0;
        for (String category : categories) {
            counter++;
            STDOUT.info("[{}], {}\n", counter, category);
        }
    }

    public static List<String> getAllCategories(DrinksDatabase database) {
        TreeSet<String> categories = new TreeSet<>();
        database.getDrinks().forEach(drink -> categories.add(drink.getCategoryName()));
        return List.copyOf(categories);
    }

    public static void printAllAlcoholStatuses(DrinksDatabase database) {
        List<String> alcoholStatuses = getAlcoholStatuses(database);
        int counter = 0;
        for (String alcoholStatus : alcoholStatuses) {
            counter++;
            STDOUT.info("[{}], {}\n", counter, alcoholStatus);
        }
    }

    public static List<String> getAlcoholStatuses(DrinksDatabase database) {
        TreeSet<String> alcoholStatuses = new TreeSet<>();
        database.getDrinks().forEach(drink -> alcoholStatuses.add(drink.getAlcoholStatus()));
        return List.copyOf(alcoholStatuses);
    }

    public static void printDrinkIngrAndMeasures(Drink drink){
        drink.getIngredients().forEach(i -> STDOUT.info("Ingredient: {}, measure: {}\n", i.getName(), i.getMeasure()) );
        STDOUT.info("\n");
    }
}



