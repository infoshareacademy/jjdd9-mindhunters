package com.infoshareacademy;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.io.IOException;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");

        Drinks myDrink = JSONReader.ObjectMapper("TEST.json");

        System.out.println("drink ID: " + myDrink.getIdDrink() + "drink name: " + myDrink.getStrDrink() + "category: " + myDrink.getStrCategory());

         List<Drinks> myDrinksArray = JSONReader.ObjectMapperArray("TEST_ARRAY.json");

        System.out.println("drink ID: " + myDrinksArray.get(0).getIdDrink() + "drink name: "  + myDrinksArray.get(0).getStrDrink());
        System.out.println("drink ID: " + myDrinksArray.get(1).getIdDrink() + "drink name: "  + myDrinksArray.get(1).getStrDrink());
        System.out.println("drink ID: " + myDrinksArray.get(11).getIdDrink() + "drink name: "  + myDrinksArray.get(11).getStrDrink());


    }
}
