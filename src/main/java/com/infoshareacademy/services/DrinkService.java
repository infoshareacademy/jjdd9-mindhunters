package com.infoshareacademy.services;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.DrinksDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
            } database.addDrinks(drinks);
        }
    }

    public static void printAllDrinks(DrinksDatabase database) {

        for (Drink drink : database.getDrinks()) {
            STDOUT.info("\n{}", drink.getDrinkName().toUpperCase());
            STDOUT.info("\n\t*ID: {}", drink.getDrinkId());
            STDOUT.info("\n\t*Category: {}", drink.getCategoryName());
            STDOUT.info("\n\t*Alcohol status: {}", drink.getAlcoholStatus());
            STDOUT.info("\n");
        }
    }
}



