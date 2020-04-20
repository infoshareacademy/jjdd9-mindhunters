package com.infoshareacademy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class JSONReader {

    private String fileName;

    public JSONReader(String fileName) {
        this.fileName = fileName;
    }

    public static Drinks ObjectMapper(String fileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        //ignore fields from .json that are not in target class

        File file = new File(fileName);

        Drinks drink = objectMapper.readValue(file, Drinks.class);
        return drink;
    }

    public static List<Drinks>  ObjectMapperArray(String jsonFileName) throws IOException {


            ObjectMapper objectMapper = new ObjectMapper();


            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            //ignore fields from .json that are not in target class

            File jsonFile = new File(jsonFileName);

           List<Drinks> drinkList = objectMapper.readValue(jsonFile, new TypeReference<List<Drinks>>() {});
           // Drinks [] drinksArray;
          //   drinksArray = objectMapper.readValue(jsonFile, Drinks[].class);

            return(drinkList);



    }
}
