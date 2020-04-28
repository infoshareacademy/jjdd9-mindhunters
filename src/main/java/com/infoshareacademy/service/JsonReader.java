package com.infoshareacademy.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.domain.Drink;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class JsonReader {

    public JsonReader() {
    }

    public static List<Drink> objectMapper(String fileName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        File json = new File(fileName);
        JsonNode jsonNode = mapper.readTree(json);


        List<Drink> drink = (List<Drink>) mapper.readValue(jsonNode.get("drinks").toString(),
                new TypeReference<List<Drink>>() {
                });

        return drink;
    }
}