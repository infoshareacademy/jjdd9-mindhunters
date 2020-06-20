package com.infoshareacademy.service.mapper;

import com.infoshareacademy.domain.DrinkIngredient;
import com.infoshareacademy.domain.Ingredient;
import com.infoshareacademy.domain.Measure;
import com.infoshareacademy.domain.dto.DrinkIngredientView;
import com.infoshareacademy.service.IngredientService;
import com.infoshareacademy.service.MeasureService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class DrinkIgredientMapper {

    @Inject
    private IngredientService ingredientService;

    @Inject
    private MeasureService measureService;


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

    public DrinkIngredient toEntity(DrinkIngredientView drinkIngredientView) {
        DrinkIngredient drinkIngredient = new DrinkIngredient();

        Ingredient ingredient = ingredientService.getOrCreate(drinkIngredientView.getName());
        Measure measure = measureService.getOrCreate(drinkIngredientView.getMeasure());

        drinkIngredient.setIngredient(ingredient);

        drinkIngredient.setMeasure(measure);

        return drinkIngredient;
    }
}
