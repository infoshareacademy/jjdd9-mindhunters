package com.infoshareacademy.repository;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.DrinksDatabase;
import com.infoshareacademy.service.DrinkService;

import java.util.List;

public class DrinkFileRepository implements DrinkRepository{

    private final DrinksDatabase drinksDatabase;

    public DrinkFileRepository() {
        DrinkService.loadDrinkList();
    }

    @Override
    public void saveAllDrinks(List<Drink> drinkRecipes) {

    }

    @Override
    public void saveDrink(Drink drinkRecipe) {

    }

    @Override
    public List<Drink> findAllDrinks() {
        return null;
    }

    @Override
    public Drink findDrink() {
        return null;
    }
}
