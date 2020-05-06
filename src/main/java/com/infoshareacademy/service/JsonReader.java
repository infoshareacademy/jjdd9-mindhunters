package com.infoshareacademy.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.FavouritesDatabase;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;


public class JsonReader {

    public JsonReader() {
    }

    public static List<Drink> jsonDrinkReader(String fileName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        File json = new File(fileName);
        JsonNode jsonNode = mapper.readTree(json);


        List<Drink> drink = (List<Drink>) mapper.readValue(jsonNode.get("drinks").toString(),
                new TypeReference<List<Drink>>() {
                });

        return drink;
    }


    public static Set<String> jsonFavouritesReader(String fileName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File json = new File(fileName);
        final Set<String> favouritesSet = mapper.readValue(json, FavouritesDatabase.class).getFavouritesIds();

        return favouritesSet;
    }


}