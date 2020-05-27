package com.infoshareacademy.repository;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.Ingredient;

import javax.ejb.Local;
import java.util.List;

@Local
public interface DrinkRepository {

    Drink findDrinkByName(String drinkName);

    List<Drink> findDrinkByIngredients(List<Ingredient> ingredients);

    void saveAllDrinks(List<Drink> drinkRecipes);

    void saveDrink(Drink drinkRecipe);

    List<Drink> findAllDrinks();

    Drink findDrink();

}
