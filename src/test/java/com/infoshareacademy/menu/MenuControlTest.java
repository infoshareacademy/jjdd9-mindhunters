package com.infoshareacademy.menu;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.DrinksDatabase;
import com.infoshareacademy.domain.FavouritesDatabase;
import com.infoshareacademy.domain.Ingredient;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

class MenuControlTest {

    @Test
    public void getAllFavouritesTest() {
        //given
        Drink drink = new Drink();
        final FavouritesDatabase instFavourites = FavouritesDatabase.getInstFavourites();
        instFavourites.addFavourite("17222");

        //when
        Set<String> favouritesDrinks = FavouritesDatabase.getInstFavourites().getFavouritesIds();

        //then
        Assertions.assertThat(favouritesDrinks).containsExactly("17222");
    }


    @Test
    public void saveTest() {
        //given
        DrinksDatabase database = DrinksDatabase.getINSTANCE();
        Drink drink = new Drink();
        drink.setDrinkId("17222");
        drink.setDrinkName("TestTest");
        drink.setModifiedDate(LocalDateTime.now());
        drink.setImageUrl("TEST");
        drink.setIngredients(List.of(new Ingredient(), new Ingredient()));
        DrinksDatabase.getINSTANCE().addDrink(drink);

        //when
        final List<Drink> drinks = DrinksDatabase.getINSTANCE().getDrinks();

        //then
        Assertions.assertThat(drinks).containsOnly(drink);
    }
}