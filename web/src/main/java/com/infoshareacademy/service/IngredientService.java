package com.infoshareacademy.service;

import com.infoshareacademy.domain.Ingredient;
import com.infoshareacademy.repository.IngredientRepositoryBean;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class IngredientService {

    @Inject
    IngredientRepositoryBean ingredientRepositoryBean;

    public Ingredient getOrCreate(String name) {
        Ingredient ingredient = ingredientRepositoryBean.getByName(name);
        if (ingredient == null) {
            ingredient = new Ingredient();
            ingredient.setName(name);
            ingredientRepositoryBean.save(ingredient);
        }
        return ingredient;
    }
}
