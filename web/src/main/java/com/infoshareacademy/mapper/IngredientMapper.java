package com.infoshareacademy.mapper;

import com.infoshareacademy.domain.DrinkIngredient;
import com.infoshareacademy.domain.Ingredient;
import com.infoshareacademy.domain.IngredientJson;
import com.infoshareacademy.domain.Measure;
import com.infoshareacademy.service.DrinkIngredientService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class IngredientMapper {

    @EJB
    private DrinkIngredientService drinkIngredientService;

    public DrinkIngredient toEntity(IngredientJson ingredientJson) {
        Measure measure = drinkIngredientService.getByMeasureOrCreate(ingredientJson.getMeasure());
        Ingredient ingredient = drinkIngredientService.getByNameOrCreate(ingredientJson.getName());
        DrinkIngredient drinkIngredient = new DrinkIngredient();
        drinkIngredient.setIngredient(ingredient);
        drinkIngredient.setMeasure(measure);

        return drinkIngredient;
    }
}
