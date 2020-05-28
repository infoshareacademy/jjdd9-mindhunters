package com.infoshareacademy.repository;

import com.infoshareacademy.domain.Drink;

import javax.ejb.Local;
import java.util.List;

@Local
public interface DrinkRepository {

    Drink findDrinkById(Long id);

    List<Drink> findDrinkByName(String partialDrinkName);

    List<Drink> findDrinkByIngredients(List<String> partialIngredientNames);

}
