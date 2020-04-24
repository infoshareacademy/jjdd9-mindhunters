package com.infoshareacademy.services;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.Ingredient;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class JsonDrinkDeserializer extends JsonDeserializer<Drink> {


    @Override
    public Drink deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        Drink drink = new Drink();

        JsonNode readValueAsTree = jsonParser.readValueAsTree();

        drink.setDrinkId(readValueAsTree.get("idDrink").asText());
        drink.setDrinkName(readValueAsTree.get("strDrink").asText());
        drink.setCategoryName(readValueAsTree.get("strCategory").asText());
        drink.setAlcoholStatus(readValueAsTree.get("strAlcoholic").asText());
        drink.setRecipe(readValueAsTree.get("strInstructions").asText());
        drink.setImageUrl(readValueAsTree.get("strDrinkThumb").asText());

        String dateAsString = readValueAsTree.get("dateModified").asText();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime formatDateTime = LocalDateTime.parse(dateAsString, dateFormatter);
        drink.setModifiedDate(formatDateTime);

        Ingredient ingredient = new Ingredient();

        List<Ingredient> ingredientsList= new ArrayList<>();

        for (int i = 1; i<=15;i++){
            String ingridientMeasureField = "strMeasure" + i;
            String ingridientNameField = "strIngredient" + i;

            if ( readValueAsTree.get(ingridientNameField).asText()!= "null"){
                ingredient.setName(readValueAsTree.get(ingridientNameField).asText());
                ingredient.setMeasure(readValueAsTree.get(ingridientMeasureField).asText());
                ingredientsList.add(ingredient);
                ingredient = new Ingredient();
            } else break;
            drink.setIngredients(ingredientsList);
        }


        return drink;
    }
}