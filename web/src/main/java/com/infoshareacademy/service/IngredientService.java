package com.infoshareacademy.service;

import com.infoshareacademy.domain.Ingredient;
import com.infoshareacademy.domain.dto.DrinkIngredientView;
import com.infoshareacademy.domain.dto.IngredientView;
import com.infoshareacademy.repository.IngredientRepository;
import com.infoshareacademy.service.mapper.IngredientMapper;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class IngredientService {

    @EJB
    private IngredientRepository ingredientRepository;

    @Inject
    private IngredientMapper ingredientMapper;

    public List<IngredientView> findIngredientsByName(String partialIngredientName) {
        final List<Ingredient> ingredientsByName = ingredientRepository.findIngredientsByName(partialIngredientName);

        return ingredientMapper.toView(ingredientsByName);
    }

}
