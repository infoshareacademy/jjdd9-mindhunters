package com.infoshareacademy.service;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.DrinksDatabase;
import com.infoshareacademy.domain.Ingredient;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;


import java.util.List;

import static java.util.List.*;

public class SearchServiceTest  {


    @AfterEach
    public void clearDatabase() {
        DrinksDatabase drinksDatabase = DrinksDatabase.getINSTANCE();
        drinksDatabase.getDrinks().clear();
    }

    @Test
    public void GetDrinksTest() {
        //given
        Ingredient ingredient = new Ingredient();
        String ingredientName = "testname";
        ingredient.setName(ingredientName);

        Drink drink = new Drink();
        drink.setIngredients(List.of(ingredient));
        List<Drink> drinks = List.of(drink);
        SearchService service = new SearchService();

        //when
        List<Drink> result = service.getDrinkList(drinks, List.of(ingredientName));
        //then
        Assertions.assertThat(result).containsExactly(drink);

    }

    @Test
    public void searchDrinkByNameTest() {
        //given
        Ingredient ingredient = new Ingredient();
        String ingredientName = "TestName";
        ingredient.setName(ingredientName);

        Drink drink = null;

        drink.setIngredients(List.of(ingredient));
        List<Drink> drinks = List.of(drink);
        SearchService service = new SearchService();

        //when
        List<Drink> result = service.getDrinkList(drinks, List.of(ingredientName));
        //then
        Assertions.assertThat(result).containsExactly(drink);

    }


    @Test
    void normalizeIngredientsListCaseSensitive() {
        //given
        String ingredientName1 = "INGREDIENT1";
        String ingredientName2 = "Ingredient2";

        SearchService service = new SearchService();

        //when
        List<String> result = service.normalizeIngredientsList(List.of(ingredientName1, ingredientName2));
        //then
        Assertions.assertThat(result).containsExactly("ingredient1", "ingredient2");


    }

    @Test
    void normalizeIngredientsListRecurringElements() {
        //given
        String ingredientName1 = "Ingredient1";
        String ingredientName2 = "Ingredient2";

        SearchService service = new SearchService();

        //when
        List<String> result = service.normalizeIngredientsList(List.of(ingredientName1, ingredientName1, ingredientName2, ingredientName2));
        //then
        Assertions.assertThat(result).containsExactly(ingredientName1.toLowerCase(),ingredientName2.toLowerCase());


    }

    @Test
    void normalizeIngredientsListWhitespace() {
        //given
        String ingredientName1 = "  Ingre dient1";
        String ingredientName2 = "Ingredien t2";

        SearchService service = new SearchService();

        //when
        List<String> result = service.normalizeIngredientsList(List.of(ingredientName1, ingredientName1, ingredientName2, ingredientName2));
        //then
        List<String> immutableList = java.util.List.of("one", "two", "three");

        Assertions.assertThat(result).containsExactly(List.of("ingredient1"));

    }

    @Test
    void normalizeIngredientsListBlank() {
        //given
        String ingredientName1 = "ingredient1";
        String ingredientName2 = "  ";

        SearchService service = new SearchService();

        //when
        List<String> result = service.normalizeIngredientsList(List.of(ingredientName1, ingredientName2));
        //then
        Assertions.assertThat(result).containsExactly(String.valueOf(List.of("ingredient1")));

    }

    @Test
    void searchDrinkByIngredient() {
    }

    @Test
    void getDrinkList() {
    }

    @Test
    void getAllIngredient() {
    }

    @Test
    void getAllCategories() {
    }

    @Test
    void searchByCategory() {
    }
}