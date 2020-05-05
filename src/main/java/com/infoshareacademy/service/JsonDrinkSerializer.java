package com.infoshareacademy.service;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.infoshareacademy.domain.Drink;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class JsonDrinkSerializer extends JsonSerializer<Drink> {

    @Override
    public void serialize(Drink drink, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("idDrink", drink.getDrinkId());
        jsonGenerator.writeStringField("strDrink", drink.getDrinkName());
        jsonGenerator.writeStringField("strCategory", drink.getCategoryName());
        jsonGenerator.writeStringField("strAlcoholic", drink.getAlcoholStatus());
        jsonGenerator.writeStringField("strInstructions", drink.getRecipe());
        jsonGenerator.writeStringField("strDrinkThumb", drink.getImageUrl());

        writeIngredientsToJson(drink, jsonGenerator, "strIngredient");
        writeIngredientsToJson(drink, jsonGenerator, "strMeasure");

        jsonGenerator.writeStringField("dateModified", (drink.getModifiedDate() != null ? drink.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM" +
                "-dd HH:mm:ss")) : null));
        jsonGenerator.writeEndObject();
    }

    private void writeIngredientsToJson(Drink drink, JsonGenerator jsonGenerator, String valueName) throws IOException {
        int counter = 1;
        for (int i = 0; i < drink.getIngredients().size(); i++) {

            if (valueName.equals("strIngredient")) {
                jsonGenerator.writeStringField("strIngredient" + (counter), drink.getIngredients().get(i).getName());
            } else {
                jsonGenerator.writeStringField("strMeasure" + (counter), drink.getIngredients().get(i).getMeasure());
            }
            counter++;
        }

        if (counter < 15) {
            for (int i = counter; i <= 15; i++) {
                if (valueName.equals("strIngredient")) {
                    jsonGenerator.writeStringField("strIngredient" + (i), null);
                } else {
                    jsonGenerator.writeStringField("strMeasure" + (i), null);
                }

            }

        }
    }

}