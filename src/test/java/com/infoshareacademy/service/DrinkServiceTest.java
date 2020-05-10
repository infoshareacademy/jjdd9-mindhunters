package com.infoshareacademy.service;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.DrinksDatabase;
import com.infoshareacademy.domain.Ingredient;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

class DrinkServiceTest {

    @Test
    public void drinkServiceDeleteFromDatabaseTest() {
        //given
        DrinksDatabase database = DrinksDatabase.getINSTANCE();
        DrinkService service = new DrinkService();
        Drink drink = new Drink();
        drink.setDrinkId("17222");
        drink.setDrinkName("TestTest");
        drink.setModifiedDate(LocalDateTime.now());
        drink.setImageUrl("TEST");
        drink.setIngredients(List.of(new Ingredient(), new Ingredient()));
        DrinksDatabase.getINSTANCE().addDrink(drink);
        service.removeDrink("17222");

        //when
        final List<Drink> drinks = DrinksDatabase.getINSTANCE().getDrinks();

        //then
        Assertions.assertThat(drinks).isEmpty();
    }

    @Test
    public void getAlcoholStatusesTest() {
        DrinksDatabase database = DrinksDatabase.getINSTANCE();
        DrinkService service = new DrinkService();
        Drink drink = new Drink();
        drink.setDrinkId("17222");
        drink.setDrinkName("TestTest");
        drink.setModifiedDate(LocalDateTime.now());
        drink.setImageUrl("TEST");
        drink.setIngredients(List.of(new Ingredient(), new Ingredient()));
        drink.setAlcoholStatus("Alcoholic");
        DrinksDatabase.getINSTANCE().addDrink(drink);

        //when
        final List<String> alcoholStatuses = service.getAlcoholStatuses(database);

        //then
        Assertions.assertThat(alcoholStatuses).containsOnly("Alcoholic");
    }
}