package com.infoshareacademy.mapper;

import com.infoshareacademy.domain.*;
import com.infoshareacademy.service.DrinkIngredientService;
import com.infoshareacademy.service.IngredientService;
import com.infoshareacademy.service.MeasureService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class IngredientMapper {

    @EJB
    private IngredientService ingredientService;

    @EJB
    private MeasureService measureService;

    //nowy
    @EJB
    private DrinkIngredientService drinkIngredientService;
//================================
    public DrinkIngredient toEntity(IngredientJson ingredientJson, Drink drink) {
        Measure measure = measureService.getOrCreate(ingredientJson.getMeasure());
        Ingredient ingredient = ingredientService.getOrCreate(ingredientJson.getName());
        DrinkIngredient drinkIngredient = new DrinkIngredient();
        drinkIngredient.setIngredient(ingredient);
        drinkIngredient.setMeasure(measure);
        drinkIngredient.setDrinkId(drink);
//        drinkIngredient = drinkIngredientService.getByDrinkIdOrCreate(drink,ingredient,measure);
        return drinkIngredient;
    }
}
