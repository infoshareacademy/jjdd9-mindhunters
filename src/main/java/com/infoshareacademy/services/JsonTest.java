package com.infoshareacademy.services;

import com.infoshareacademy.domain.Drink;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class JsonTest {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");

        List<Drink> myDrinks = JsonReader.objectMapper("TEST.json");
        myDrinks.forEach(System.out::println);

        LocalDateTime date = myDrinks.get(0).getModifiedDate();


    }

}