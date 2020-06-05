package com.infoshareacademy.mapper;

import com.infoshareacademy.domain.*;
import com.infoshareacademy.repository.CategoryRepositoryBean;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class DrinkMapper {

    @EJB
    CategoryRepositoryBean categoryRepositoryBean;

    @Inject
    CategoryMapper categoryMapper;

    @Inject
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

        List<DrinkIngredient> drinkIngredients = new ArrayList<>();
        for (IngredientJson ingredientJson : drinkJson.getIngredients()) {
            DrinkIngredient ingredient = ingredientMapper.toEntity(ingredientJson);
            ingredient.setDrinkId(drink);
            drinkIngredients.add(ingredient);
        }

        return drink;
    }
}


//    DrinkJson drink = new DrinkJson();
//
//    JsonNode readValueAsTree = jsonParser.readValueAsTree();
//
//        drink.setDrinkId(readValueAsTree.get("idDrink").asText());
//                drink.setDrinkName(readValueAsTree.get("strDrink").asText());
//                drink.setCategoryName(readValueAsTree.get("strCategory").asText());
//                drink.setAlcoholStatus(readValueAsTree.get("strAlcoholic").asText());
//                drink.setRecipe(readValueAsTree.get("strInstructions").asText());
//                drink.setImageUrl(readValueAsTree.get("strDrinkThumb").asText());
//
//                String dateAsString = readValueAsTree.get("dateModified").asText();
//                if (!dateAsString.equals("null")) {
//                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//                LocalDateTime formatDateTime = LocalDateTime.parse(dateAsString, dateFormatter);
//                drink.setModifiedDate(formatDateTime);
//                } else {
//                LocalDateTime formatDateTime = LocalDateTime.now();
//                drink.setModifiedDate(formatDateTime);
//                }
//
//                IngredientJson ingredient = new IngredientJson();
//
//                List<IngredientJson> ingredientsList = new ArrayList<>();
//
//        for (int i = 1; i <= 15; i++) {
//        String ingredientMeasureField = "strMeasure" + i;
//        String ingredientNameField = "strIngredient" + i;
//
//        if (!readValueAsTree.get(ingredientNameField).asText().equals("null") && !readValueAsTree.get(ingredientNameField).asText().isEmpty()) {
//        if (!readValueAsTree.get(ingredientMeasureField).asText().equals("null")) {
//        ingredient.setName(readValueAsTree.get(ingredientNameField).asText());
//        ingredient.setMeasure(readValueAsTree.get(ingredientMeasureField).asText());
//        ingredientsList.add(ingredient);
//        ingredient = new IngredientJson();
//        } else {
//        ingredient.setName(readValueAsTree.get(ingredientNameField).asText());
//        ingredient.setMeasure("no measures");
//        ingredientsList.add(ingredient);
//        ingredient = new IngredientJson();
//        }
//
//        } else break;
//        drink.setIngredients(ingredientsList);
//        }
//
//
//        return drink;
