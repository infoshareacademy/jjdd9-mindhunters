package com.infoshareacademy.service;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.DrinksDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DrinkServiceSz {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    List<Drink> database = DrinksDatabase.getINSTANCE().getDrinks();

    public DrinkServiceSz() {
    }

    public void searchDrinkByName() {
        List<Drink> outputSearch = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        STDOUT.info("Input drink name: ");

        String inputSearch = scanner.next().toLowerCase();
        if (inputSearch.length() > 2) {
            for (Drink drink : database) {
                String name = drink.getDrinkName().toLowerCase();
                if (name.startsWith(inputSearch)) {
                    outputSearch.add(drink);
                }
            }
            printFoundDrink(outputSearch);
        } else {
            STDOUT.info("Input min. 3 characters\n");
            searchDrinkByName();
        }

    }




    public void printFoundDrink(List<Drink> drinkList) {
        for (Drink drink : drinkList) {
            STDOUT.info("\n{}\n *ID: {}, *Category: {}, {};", drink.getDrinkName().toUpperCase(),
                    drink.getDrinkId(), drink.getCategoryName(), drink.getAlcoholStatus());
            STDOUT.info("\n");

        }
    }
}


