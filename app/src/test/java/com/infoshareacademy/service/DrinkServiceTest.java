package com.infoshareacademy.service;

import com.infoshareacademy.domain.DrinkJson;
import com.infoshareacademy.domain.DrinksDatabase;
import com.infoshareacademy.domain.Ingredient;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

class DrinkServiceTest {

    @AfterEach
    public void clearDatabase() {
        DrinksDatabase drinksDatabase = DrinksDatabase.getINSTANCE();
        drinksDatabase.getDrinks().clear();
    }

    @Test
    public void drinkServiceDeleteFromDatabaseTest() {
        //given
        DrinkService service = new DrinkService();
        DrinkJson drink = new DrinkJson();
        drink.setDrinkId("17222");
        drink.setDrinkName("TestTest");
        drink.setModifiedDate(LocalDateTime.now());
        drink.setImageUrl("TEST");
        drink.setIngredients(List.of(new Ingredient(), new Ingredient()));
        DrinksDatabase.getINSTANCE().addDrink(drink);
        service.removeDrink("17222");


        //when
        final List<DrinkJson> drinks = DrinksDatabase.getINSTANCE().getDrinks();

        //then
        Assertions.assertThat(drinks).isEmpty();
    }

    @Test
    public void drinkServiceAddToDatabaseTest() {
        //given
        DrinkService service = new DrinkService();
        DrinkJson drink = new DrinkJson();
        drink.setDrinkId("17222");
        drink.setDrinkName("TestTest");
        drink.setModifiedDate(LocalDateTime.now());
        drink.setImageUrl("TEST");
        drink.setIngredients(List.of(new Ingredient(), new Ingredient()));
        DrinksDatabase.getINSTANCE().addDrink(drink);

        //when
        final List<DrinkJson> drinks = DrinksDatabase.getINSTANCE().getDrinks();

        //then
        Assertions.assertThat(drinks).containsOnly(drink);

    }
}