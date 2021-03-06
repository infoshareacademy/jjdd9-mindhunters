package com.infoshareacademy.repository;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.Ingredient;
import com.infoshareacademy.domain.dto.FullDrinkView;

import javax.ejb.Local;
import java.util.List;

@Local
public interface DrinkRepository {


    Drink findDrinkById(Long drinkId);

    List<Drink> findDrinksByName(String partialDrinkName, int startPosition, int endPosition);

    int countPagesByName(String partialDrinkName);

    List<Drink> liveSearchDrinksByName(String partialDrinkName);

    List<Drink> findByIngredients(List<Ingredient> ingredients, int startPosition, int endPosition);

    int countPagesByIngredients(List<Ingredient> ingredients);

    List<Drink> findAllDrinks(int startPosition, int endPosition);

    List<Drink> findAllDrinks();

    int countPagesFindAll();

    List<Drink> findByCategories(List<Long> category, int startPosition, int endPosition);

    int countPagesByCategories(List<Long> category);

    List<Drink> findByAlcoholStatus(List<String> alcoholStatus, int startPosition, int endPosition);

    int countPagesByAlcoholStatus(List<String> alcoholStatus);

    List<Drink> findByCategoriesAndAlcoholStatus(List<Long> category, List<String> alcoholStatus,
                                                 int startPosition, int endPosition);

    int countPagesByCategoriesAndAlcoholStatus(List<Long> category, List<String> alcoholStatus);

    void save(Drink drink);

    void delete(Long id);

    void update(Long id, Drink drink);

    List<Drink> findDrinksToApprove();

    void deleteIngredientsFromDrink(Long drinkId);


    }
