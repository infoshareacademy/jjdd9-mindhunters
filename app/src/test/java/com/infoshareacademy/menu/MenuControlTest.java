package com.infoshareacademy.menu;

import com.infoshareacademy.domain.DrinkJson;
import com.infoshareacademy.domain.DrinksDatabase;
import com.infoshareacademy.domain.FavouritesDatabase;
import com.infoshareacademy.domain.IngredientJson;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

class MenuControlTest {

    @AfterEach
    public void clearDatabase() {
        DrinksDatabase drinksDatabase = DrinksDatabase.getINSTANCE();
        drinksDatabase.getDrinks().clear();
    }

    @Test
    public void getAllFavouritesTest() {
        //given
        DrinkJson drink = new DrinkJson();
        final FavouritesDatabase instFavourites = FavouritesDatabase.getInstFavourites();
        instFavourites.addFavourite("17222");

        //when
        Set<String> favouritesDrinks = FavouritesDatabase.getInstFavourites().getFavouritesIds();

        //then
        Assertions.assertThat(favouritesDrinks).containsExactly("17222");
    }


    @Test
    public void saveIntoDatabaseTest() {
        //given
        DrinksDatabase database = DrinksDatabase.getINSTANCE();
        DrinkJson drink = new DrinkJson();
        drink.setDrinkId("17222");
        drink.setDrinkName("TestTest");
        drink.setModifiedDate(LocalDateTime.now());
        drink.setImageUrl("TEST");
        drink.setIngredients(List.of(new IngredientJson(), new IngredientJson()));
        DrinksDatabase.getINSTANCE().addDrink(drink);

        //when
        final List<DrinkJson> drinks = DrinksDatabase.getINSTANCE().getDrinks();

        //then
        Assertions.assertThat(drinks).containsOnly(drink);
    }

    @Test
    public void deleteFromDatabaseTest() {
        //given
        DrinksDatabase database = DrinksDatabase.getINSTANCE();
        DrinkJson drink = new DrinkJson();
        drink.setDrinkId("17222");
        drink.setDrinkName("TestTest");
        drink.setModifiedDate(LocalDateTime.now());
        drink.setImageUrl("TEST");
        drink.setIngredients(List.of(new IngredientJson(), new IngredientJson()));
        DrinksDatabase.getINSTANCE().addDrink(drink);
        DrinksDatabase.getINSTANCE().getDrinks().remove(drink);


        //when
        final List<DrinkJson> drinks = DrinksDatabase.getINSTANCE().getDrinks();

        //then
        Assertions.assertThat(drinks).isEmpty();
    }
}