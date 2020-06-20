package com.infoshareacademy.service.mapper;

import com.infoshareacademy.domain.Ingredient;
import com.infoshareacademy.domain.dto.IngredientView;

import javax.enterprise.context.RequestScoped;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class IngredientMapper {

    public IngredientView toView(Ingredient ingredient) {
        IngredientView ingredientView = new IngredientView();
        ingredientView.setId(ingredient.getId());
        ingredientView.setName(ingredient.getName());
        return ingredientView;
    }

    public List<IngredientView> toView(List<Ingredient> ingredients) {
        List<IngredientView> ingredientViews = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            ingredientViews.add(toView(ingredient));
        }
        return ingredientViews;
    }

    public Ingredient toEntity(IngredientView ingredientView) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(ingredientView.getName());
        return ingredient;
    }

    public List<Ingredient> toEntity(List<IngredientView> ingredientViews) {
        List<Ingredient> ingredients = new ArrayList<>();
        for (IngredientView ingredientView : ingredientViews) {
            ingredients.add(toEntity(ingredientView));
        }
        return ingredients;
    }
}