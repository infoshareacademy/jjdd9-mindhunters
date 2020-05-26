package com.infoshareacademy.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;

@Entity
@IdClass(DrinkIngredientId.class)
public class DrinkIngredient {

    @Id
    @JoinColumn
    private Measure measureId;

    @Id
    @JoinColumn
    private Ingredient ingredientId;

    @Id
    private Drink drinkId;

}
