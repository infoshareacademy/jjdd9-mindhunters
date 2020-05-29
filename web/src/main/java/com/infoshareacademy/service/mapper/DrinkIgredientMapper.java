package com.infoshareacademy.service.mapper;

import com.infoshareacademy.domain.DrinkIngredient;
import com.infoshareacademy.domain.dto.DrinkIngredientView;

import javax.enterprise.context.RequestScoped;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class DrinkIgredientMapper {

    public DrinkIngredientView toView(DrinkIngredient drinkIngredient) {
        DrinkIngredientView view = new DrinkIngredientView(
                drinkIngredient.getIngredient().getName(),
                drinkIngredient.getMeasure().getQuantity()
        );
        return view;
    }

    public List<DrinkIngredientView> toView(List<DrinkIngredient> drinkIngredients) {
        List<DrinkIngredientView> drinkIngredientViews = new ArrayList<>();
        for (DrinkIngredient drinkIngredient : drinkIngredients) {
            drinkIngredientViews.add(toView(drinkIngredient));
        }
        return drinkIngredientViews;
    }

}
