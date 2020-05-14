package com.infoshareacademy.repository;

import com.infoshareacademy.domain.Drink;

import java.util.List;

public interface DrinkRepository {

    void saveAllDrinks(List<Drink> drinkRecipes);

    void saveDrink(Drink drinkRecipe);

    List<Drink> findAllDrinks();

    Drink findDrink();

}
