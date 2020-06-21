package com.infoshareacademy.mapper;

import com.infoshareacademy.domain.*;
import com.infoshareacademy.service.IngredientService;
import com.infoshareacademy.service.MeasureService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class UploadIngredientMapper {

    @EJB
    private IngredientService ingredientService;

    @EJB
    private MeasureService measureService;

    public DrinkIngredient toEntity(IngredientJson ingredientJson, Drink drink) {
        Measure measure = measureService.getOrCreate(ingredientJson.getMeasure());
        Ingredient ingredient = ingredientService.getOrCreate(ingredientJson.getName());
        DrinkIngredient drinkIngredient = new DrinkIngredient();
        drinkIngredient.setIngredient(ingredient);
        drinkIngredient.setMeasure(measure);
        return drinkIngredient;
    }
}
