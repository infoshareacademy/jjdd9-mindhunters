package com.infoshareacademy.repository;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.Ingredient;
import org.hibernate.jpa.QueryHints;

import javax.ejb.Local;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Local
public interface DrinkRepository {

    Drink findDrinkById(Long drinkId);


   List<Drink> findDrinksByName(String partialDrinkName, int startPosition, int endPosition);

    int countPagesByName(String partialDrinkName);

    List<Drink> liveSearchDrinksByName(String partialDrinkName);

    List<Drink> findByIngredients(List<Ingredient> ingredients, int startPosition, int endPosition);

    int countPagesByIngredients(List<Ingredient> ingredients);

    List<Drink> findAllDrinks(int startPosition, int endPosition);

    int countPagesFindAll();

}
