package com.infoshareacademy.service;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.DrinksDatabase;

public class DrinkRemover implements DatabaseOperator {

    private final DrinksDatabase database = DrinksDatabase.getINSTANCE();

    @Override
    public boolean operate(String id) {
        for (Drink drink : database.getDrinks()) {
            if (drink.getDrinkId().trim().equalsIgnoreCase(id)) {
                database.getDrinks().remove(drink);
                return true;
            }
        }
        return false;
    }
}
