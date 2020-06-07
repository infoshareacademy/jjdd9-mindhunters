package com.infoshareacademy.repository;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.Ingredient;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

@Local
public interface DrinkRepository {

    Drink findDrinkById(Long id);

    List<Drink> paginatedFindDrinksByName(String partialDrinkName, int pageNumber);

    List<Drink> paginatedFindDrinkByIngredients(List<Ingredient> ingredients, int pageNumer);

    List<Drink> findAllDrinks();

    List<Drink> paginatedDrinksByCategories(List<Long> category, int pageNumber);

    List<Drink> paginatedDrinksList(int pageNumber);

    int maxPageNumberDrinkList();

    int maxPageNumberDrinksByCategories(List<Long> category);

    int maxPageNumberDrinksByIngredients(List<Ingredient> ingredients);

    int maxPageNumberDrinksByName(String name);

    List<Drink> liveSearchDrinksByName(String partialDrinkName);


}
