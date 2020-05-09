package com.infoshareacademy.service;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.DrinksDatabase;
import com.infoshareacademy.domain.FavouritesDatabase;
import com.infoshareacademy.domain.Ingredient;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

class FavouritesServiceTest {

    @Test
    public void removeFromFavouritesTest() {
        //given
        FavouritesDatabase instFavourites = FavouritesDatabase.getInstFavourites();
        instFavourites.addFavourite("17222");

        //when
        new FavouritesService().removeFromFavourites("17222");

        //then
        Assertions.assertThat(instFavourites.getFavouritesIds()).doesNotContain("17222");
    }

    @Test
    public void addToFavouritesTest() {
        //given
        FavouritesDatabase instFavourites = FavouritesDatabase.getInstFavourites();

        //when
        new FavouritesService().addToFavourites("17222");

        //then
        Assertions.assertThat(instFavourites.getFavouritesIds()).containsOnly("17222");
    }

    @Test
    public void getFavourDrinkListTest() {
        //given
        Drink drink = new Drink();
        drink.setDrinkId("17222");
        drink.setDrinkName("TestTest");
        drink.setModifiedDate(LocalDateTime.now());
        drink.setImageUrl("TEST");
        drink.setIngredients(List.of(new Ingredient(), new Ingredient()));
        DrinksDatabase.getINSTANCE().addDrink(drink);
        FavouritesDatabase instFavourites = FavouritesDatabase.getInstFavourites();
        instFavourites.addFavourite("17222");

        //when
        new FavouritesService().getAllFavourites(instFavourites);

        //then
        Assertions.assertThat(instFavourites.getFavouritesIds()).containsOnly("17222");


    }

}