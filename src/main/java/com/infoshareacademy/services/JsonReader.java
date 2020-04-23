package com.infoshareacademy.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.infoshareacademy.domain.Drink;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class JsonReader  {

    private String fileName;

    public JsonReader(String fileName) {
        this.fileName = fileName;
    }

    public static Drink[] ObjectMapper(String fileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE).reader().withRootName("drinks");
/*
        objectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
*/
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


        //ignore fields from .json that are not in target class


        Drink[] drink = objectMapper.readValue(new File(fileName), Drink[].class);
        return drink;
    }

    public static List<Drink>  ObjectMapperArray(String fileName) throws IOException {


        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE).reader().withRootName("drinks");

    //    objectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //ignore fields from .json that are not in target class

        List<Drink> drinkList = objectMapper.readValue(new File(fileName), new TypeReference<List<Drink>>() {});
        // Drinks [] drinksArray;
        //   drinksArray = objectMapper.readValue(jsonFile, Drinks[].class);

        return(drinkList);



    }

/*    public static List<Drink>  CustomObjectArray(String jsonFileName) throws IOException {


        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        //ignore fields from .json that are not in target class

        File jsonFile = new File(jsonFileName);

        List<Drink> drinkList = objectMapper.readValue(jsonFile, new TypeReference<List<Drink>>() {});
        // Drinks [] drinksArray;
        //   drinksArray = objectMapper.readValue(jsonFile, Drinks[].class);
        SimpleMo
        return(drinkList);



    }*/
}
