package com.infoshareacademy.service;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.DrinksDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DrinkRemover {

    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private DrinksDatabase database;

    public DrinkRemover() {
        this.database = DrinksDatabase.getINSTANCE();
    }

    private boolean removeDrinkFromDatabase(String id) {
        for (Drink drink : database.getDrinks()) {
            if (drink.getDrinkId().trim().equalsIgnoreCase(id)) {
                database.getDrinks().remove(drink);
                return true;
            }
        }
        return false;
    }
}
