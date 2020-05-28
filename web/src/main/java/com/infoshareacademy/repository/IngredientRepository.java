package com.infoshareacademy.repository;

import com.infoshareacademy.domain.Ingredient;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IngredientRepository {

    List<Ingredient> findIngredientsByName(String partialIngredientName);
}
