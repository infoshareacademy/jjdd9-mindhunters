package com.infoshareacademy.domain;

import java.io.Serializable;
import java.util.Objects;

public class DrinkIngredientId implements Serializable {

    private Long measureId;

    private Long ingredientId;

    public DrinkIngredientId(Long measureId, Long ingredientId) {
        this.measureId = measureId;
        this.ingredientId = ingredientId;
    }

    public Long getMeasureId() {
        return measureId;
    }

    public Long getIngredientId() {
        return ingredientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof DrinkIngredientId))
            return false;
        DrinkIngredientId that = (DrinkIngredientId) o;
        return measureId.equals(that.measureId) &&
                ingredientId.equals(that.ingredientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(measureId, ingredientId);
    }
}

