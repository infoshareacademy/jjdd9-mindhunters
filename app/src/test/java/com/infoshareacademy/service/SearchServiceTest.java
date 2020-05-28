package com.infoshareacademy.service;

import com.infoshareacademy.domain.DrinkJson;
import com.infoshareacademy.domain.Ingredient;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.List;

public class SearchServiceTest  {

    @Test
    public void testGetDrinks() {
        //given
        Ingredient ingredient = new Ingredient();
        String ingredientName = "testname";
        ingredient.setName(ingredientName);

        DrinkJson drink = new DrinkJson();
        drink.setIngredients(List.of(ingredient));
        List<DrinkJson> drinks = List.of(drink);
        SearchService service = new SearchService();

        //when
        List<DrinkJson> result = service.getDrinkList(drinks, List.of(ingredientName));
        //then
        Assertions.assertThat(result).containsExactly(drink);

    }
}