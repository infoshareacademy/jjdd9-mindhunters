package com.infoshareacademy.service;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.Ingredient;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
;

import java.util.List;

public class SearchServiceTest  {

    @Test
    public void testGetDrinks() {
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
}