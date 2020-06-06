package com.infoshareacademy.repository;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.Ingredient;

import javax.ejb.Local;
import java.util.List;

@Local
public interface DrinkRepository {

    Drink findDrinkById(Long id);

    List<Drink> findDrinksByName(String partialDrinkName);

    List<Drink> findDrinkByIngredients(List<Ingredient> ingredients);

    List<Drink> findByCategories(List<Long> category, int pageNumber);

    List<Drink> findByAlcoholStatus(List<String> alcoholStatus, int pageNumber);

    List<Drink> findByCategoriesAndAlcoholStatus(List<Long> category, List<String> alcoholStatus, int pageNumber);

    List<Drink> findAllDrinks(int pageNumber);

    int maxPageNumberFindAll();

    int maxPageNumberByCategories(List<Long> category);

    int maxPageNumberByAlcoholStatus(List<String> alcoholStatus);

    int maxPageNumberByCategoriesAndAlcoholStatus (List<Long> category, List<String> alcoholStatus);


}
