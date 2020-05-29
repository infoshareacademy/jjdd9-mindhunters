package com.infoshareacademy.service.mapper;

import com.infoshareacademy.domain.Category;
import com.infoshareacademy.domain.DrinkIngredient;
import com.infoshareacademy.domain.dto.CategoryView;
import com.infoshareacademy.domain.dto.DrinkIngredientView;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class DrinkIgredientMapper {

    public DrinkIngredientView toView(DrinkIngredient drinkIngredient) {
      DrinkIngredientView view = new DrinkIngredientView(
              drinkIngredient.getIngredient().getName(),
              drinkIngredient.getMeasure().getQuantity()
      );
      return view;
    }
}
