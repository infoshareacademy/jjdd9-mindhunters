package com.infoshareacademy.service;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.DrinkIngredient;
import com.infoshareacademy.domain.Ingredient;
import com.infoshareacademy.domain.Measure;
import com.infoshareacademy.repository.DrinkIngredientRepositoryBean;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class DrinkIngredientService {

    @Inject
    DrinkIngredientRepositoryBean drinkIngredientRepositoryBean;

    public DrinkIngredient getByDrinkIdOrCreate(Drink drink, Ingredient ingredient, Measure measure) { ;
       DrinkIngredient drinkIngredient = drinkIngredientRepositoryBean.getDrinkIngredientByDrinkId(drink);
        if (drinkIngredient==null) {
            drinkIngredient = new DrinkIngredient();
            drinkIngredient.setDrinkId(drink);
            drinkIngredient.setIngredient(ingredient);
            drinkIngredient.setMeasure(measure);
//            drinkIngredientRepositoryBean.save(drinkIngredient);
        }
        return drinkIngredient;
    }
}
