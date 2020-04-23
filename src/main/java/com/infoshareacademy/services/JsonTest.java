package com.infoshareacademy.services;

import com.infoshareacademy.services.JsonReader;
import com.infoshareacademy.domain.Drink;

import java.io.IOException;
import java.util.List;

public class JsonTest {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");

/*        Drink[] myDrink = JsonReader.ObjectMapper("TEST.json");

        System.out.println(myDrink[0].toString());*/

        List<Drink> myDrinksArray = JsonReader.ObjectMapperArray("TEST.json");

        System.out.println(myDrinksArray.get(0).toString());


    }

}