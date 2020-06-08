package com.infoshareacademy.mapper;

import com.infoshareacademy.domain.*;
import com.infoshareacademy.jsonSupport.CategoryJson;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class DrinkMapper {

//    @EJB
//    CategoryRepositoryBean categoryRepositoryBean;

    @Inject
    CategoryMapper categoryMapper;

    @Inject
    IngredientMapper ingredientMapper;

    public Drink toEntity(DrinkJson drinkJson,CategoryJson categoryJson) {

        Drink drink = new Drink();

        String categoryName = drinkJson.getCategoryName();
//        Category category = categoryRepositoryBean.getByName(categoryName);

        drink.setDrinkId(drinkJson.getDrinkId());
        drink.setDrinkName(drinkJson.getDrinkName());

        drink.setAlcoholStatus(drinkJson.getAlcoholStatus());
        drink.setRecipe(drinkJson.getRecipe());
        drink.setImage(drinkJson.getImageUrl());
        drink.setDate(drinkJson.getModifiedDate());

        List<DrinkIngredient> drinkIngredients = new ArrayList<>();
        for (IngredientJson ingredientJson : drinkJson.getIngredients()) {
            DrinkIngredient drinkIngredient = ingredientMapper.toEntity(ingredientJson,drink);
            drinkIngredient.setDrinkId(drink);
            drinkIngredients.add(drinkIngredient);
        }
        categoryJson.setCategoryName(drinkJson.getCategoryName());
        Category category = categoryMapper.toEntity(categoryJson);
        drink.setCategory(category);

        return drink;
    }
}
