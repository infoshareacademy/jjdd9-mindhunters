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

    List<Drink> findByCategories(List<Long> category, int startPosition, int endPosition);

    List<Drink> findByAlcoholStatus(List<String> alcoholStatus, int startPosition, int endPosition);

    List<Drink> findByCategoriesAndAlcoholStatus(List<Long> category, List<String> alcoholStatus, int startPosition, int endPosition);

    List<Drink> findAllDrinks(int startPosition, int endPosition);

    int countPagesFindAll();

    int countPagesByCategories(List<Long> category);

    int countPagesByAlcoholStatus(List<String> alcoholStatus);

    int countPagesByCategoriesAndAlcoholStatus(List<Long> category, List<String> alcoholStatus);


}
