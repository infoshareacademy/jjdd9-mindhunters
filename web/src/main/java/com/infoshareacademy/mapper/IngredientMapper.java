package com.infoshareacademy.mapper;

import com.infoshareacademy.domain.DrinkIngredient;
import com.infoshareacademy.domain.Ingredient;
import com.infoshareacademy.domain.IngredientJson;
import com.infoshareacademy.domain.Measure;
import com.infoshareacademy.service.IngredientService;

//@RequestScoped
public class IngredientMapper {

//    @EJB
    private IngredientService ingredientService;

    public DrinkIngredient toEntity(IngredientJson ingredientJson) {
        Measure measure = ingredientService.getByMeasureOrCreate(ingredientJson.getMeasure());
        Ingredient ingredient = ingredientService.getByNameOrCreate(ingredientJson.getName());
        DrinkIngredient drinkIngredient = new DrinkIngredient();
        drinkIngredient.setIngredient(ingredient);
        drinkIngredient.setMeasure(measure);

        return drinkIngredient;
    }
}
