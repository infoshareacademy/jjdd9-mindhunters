package com.infoshareacademy.repository;

import com.infoshareacademy.domain.Drink;

import javax.ejb.Local;
import java.util.List;

@Local
public interface DrinkRepository {

    Drink findDrinkById(Long id);

    Drink findDrinkByName(String drinkName);

    List<Drink> findDrinkByIngredients(List<String> ingredientNames);

}
