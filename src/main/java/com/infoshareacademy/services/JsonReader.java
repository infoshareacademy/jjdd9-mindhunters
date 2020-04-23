package com.infoshareacademy.services;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.domain.Drink;

import java.io.File;
import java.io.IOException;


public class JsonReader {

    private String fileName;

    public JsonReader(String fileName) {

        this.fileName = fileName;
    }

    public static Drink ObjectMapper(String fileName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
       // mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        File json = new File("TEST.json");
        JsonNode jsonNode = mapper.readTree(json);


        Drink drink = mapper.readValue(jsonNode.get("drinks").toString(), Drink.class);

        return drink;
    }
}
