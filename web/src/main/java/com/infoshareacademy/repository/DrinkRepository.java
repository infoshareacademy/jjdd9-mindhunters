package com.infoshareacademy.repository;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.Ingredient;

import javax.ejb.Local;
import javax.persistence.Query;
import java.util.List;

@Local
public interface DrinkRepository {

    Drink findDrinkById(Long id);

    List<Drink> findDrinksByName(String partialDrinkName);

    List<Drink> findDrinkByIngredients(List<Ingredient> ingredients);

    List<Drink> findAllDrinks();

    List<Drink> findAllDrinksByCategories(List<String> category,int pageNumber);

    List<Drink> paginationDrinkList(int pageNumber);

    int maxPageNumberDrinkList();

    int maxPageNumberDrinksByCategories(List<String> category);

}
