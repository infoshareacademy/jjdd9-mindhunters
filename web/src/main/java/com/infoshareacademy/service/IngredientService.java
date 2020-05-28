package com.infoshareacademy.service;

import com.infoshareacademy.domain.Ingredient;
import com.infoshareacademy.repository.IngredientRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class IngredientService {

    @EJB
    IngredientRepository ingredientRepository;

    public List<Ingredient> findIngredientsByName(String partialIngredientName) {
        //moze jakie logowanie zrobic w service???
        return ingredientRepository.findIngredientsByName(partialIngredientName);
    }

}
