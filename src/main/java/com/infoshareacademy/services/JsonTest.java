package com.infoshareacademy.services;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.DrinksDatabase;
import com.infoshareacademy.domain.Ingredient;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JsonTest {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");

        DrinksDatabase  drinksDatabase= DrinksDatabase.getINSTANCE();
        drinksDatabase.addDrinks(JsonReader.objectMapper("LIST_aLETTER.json"));
        drinksDatabase.addDrinks(JsonReader.objectMapper("LIST_bLETTER.json"));
        drinksDatabase.addDrinks(JsonReader.objectMapper("LIST_bLETTER.json"));
        drinksDatabase.addDrinks(JsonReader.objectMapper("LIST_bLETTER.json"));
        drinksDatabase.addDrinks(JsonReader.objectMapper("LIST_eLETTER.json"));








        drinksDatabase.getDrinks().forEach(System.out::println);
        //LocalDateTime date = drinksDatabase.getDrinks().getModifiedDate();

      /*  System.out.println(myDrinks.get(0).getIngredients().get(0).getName());
        System.out.println(myDrinks.get(1).getIngredients().get(1).getName());
        System.out.println(myDrinks.get(1).getIngredients().get(2).getName());
        System.out.println(myDrinks.get(1).getIngredients().size());*/
       // for (Ingredient ingredient : myDrinks.get(1).getIngredients()) {
        //    System.out.println("Name " + ingredient.getName() + " measure " + ingredient.getMeasure());

    }

}