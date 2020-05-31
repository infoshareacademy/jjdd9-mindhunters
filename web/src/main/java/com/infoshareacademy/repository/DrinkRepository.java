package com.infoshareacademy.repository;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.Ingredient;
import com.infoshareacademy.domain.dto.FullDrinkView;

import javax.ejb.Local;
import java.util.List;

@Local
public interface DrinkRepository {

    Drink findDrinkById(Long id);

    List<Drink> findDrinksByName(String partialDrinkName);

    List<Drink> findDrinkByIngredients(List<Ingredient> ingredients);

    List<Drink> findAllDrinks();

    List<Drink> findAllDrinksByCategories(List<String> category);

    List<Drink> paginationDrinkList(int pageNumber);

    int maxPageNumber();

    int maxPageSearchResultNumber(Integer numberOfDrinks);

}
