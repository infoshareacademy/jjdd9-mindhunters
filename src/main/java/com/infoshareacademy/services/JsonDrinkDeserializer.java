package com.infoshareacademy.services;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.infoshareacademy.domain.Drink;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


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

        return drink;
    }
}