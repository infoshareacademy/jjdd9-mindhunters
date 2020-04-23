package com.infoshareacademy.services;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.infoshareacademy.domain.Drink;

import java.io.IOException;


public class JsonDrinkDeserializer extends JsonDeserializer<Drink> {


    @Override
    public Drink deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        //cała logika zmiany String na Obiekt, JsonParser uzywany żeby wszyt powyciągać - jeden obiekt Jackson
        Drink drink = new Drink();
        JsonNode readValueAsTree = jsonParser.readValueAsTree();
        drink.setDrinkId(readValueAsTree.get("idDrink").asText());


        return null;
    }
}