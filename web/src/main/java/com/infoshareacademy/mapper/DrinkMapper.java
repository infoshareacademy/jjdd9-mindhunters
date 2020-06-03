package com.infoshareacademy.mapper;

import com.infoshareacademy.domain.*;
import com.infoshareacademy.repository.CategoryRepositoryBean;

import javax.enterprise.context.RequestScoped;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class DrinkMapper {

//    @EJB
    CategoryRepositoryBean categoryRepositoryBean;

//    @Inject
    CategoryMapper categoryMapper;

//    @Inject
    IngredientMapper ingredientMapper;

    public Drink toEntity(DrinkJson drinkJson){

        Drink drink = new Drink();

        String categoryName = drinkJson.getCategoryName();
        Category category = categoryRepositoryBean.getByName(categoryName);

        drink.setDrinkId(drinkJson.getDrinkId());
        drink.setDrinkName(drinkJson.getDrinkName());
        drink.setCategory(category);
        drink.setAlcoholStatus(drinkJson.getAlcoholStatus());
        drink.setRecipe(drinkJson.getRecipe());
        drink.setImage(drinkJson.getImageUrl());

        List<DrinkIngredient> ingredientsList = new ArrayList<>();
        for (IngredientJson ingredientJson : drinkJson.getIngredients()) {
            DrinkIngredient ingredient = ingredientMapper.toEntity(ingredientJson);
            ingredientsList.add(ingredient);
        }
//        drinkMapped.setDrinkIngredient();

        return drink;
    }
}
