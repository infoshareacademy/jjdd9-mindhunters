package com.infoshareacademy.mapper;

import com.infoshareacademy.domain.*;

import javax.enterprise.context.RequestScoped;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class DrinkMapper {

    IngredientMapper ingredientMapper;

    public Drink toEntity(DrinkJson drinkJson){

        Drink drinkMapped = new Drink();
        Category category = new Category();

        drinkMapped.setDrinkId(drinkJson.getDrinkId());
        drinkMapped.setDrinkName(drinkJson.getDrinkName());
//        drinkMapped.setCategory(drinkJson.cat);             cos nie tak
        drinkMapped.setAlcoholStatus(drinkJson.getAlcoholStatus());
        drinkMapped.setRecipe(drinkJson.getRecipe());
        drinkMapped.setImage(drinkJson.getImageUrl());







        List<Ingredient> ingredientsList = new ArrayList<>();
        DrinkIngredient drinkIngredient = new DrinkIngredient();
        for (Ingredient ingredient : drinkJson.getIngredients()) {
             drinkIngredient = ingredientMapper.toEntity(ingredient);
            ingredientsList.add(ingredient);
        }
//        drinkMapped.setDrinkIngredient();

        return drinkMapped;
    }
}
