package com.infoshareacademy.repository;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.Ingredient;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class DrinkRepositoryBean implements DrinkRepository {

    @PersistenceContext
    EntityManager entityManager;

    public Drink findDrinkByName(Long drinkName) {

        return entityManager.find(Drink.class, drinkName);
    }

    public List<Drink> findDrinkByIngredients(List<Ingredient> ingredients) {
        Query drinkQuery = entityManager.createNamedQuery("Drink.findDrinkByIngredients");
        drinkQuery.setParameter("ingredients", ingredients);
        return drinkQuery.getResultList();
    }


/*    @Override
    public void saveAllDrinks(List<Drink> drinkRecipes) {

    }

    @Override
    public void saveDrink(Drink drinkRecipe) {

    }

    @Override
    public List<Drink> findAllDrinks() {
        return null;
    }

    @Override
    public Drink findDrink() {
        return null;
    }*/
}
