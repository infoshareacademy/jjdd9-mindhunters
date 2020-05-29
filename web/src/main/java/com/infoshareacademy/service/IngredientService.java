package com.infoshareacademy.service;

import com.infoshareacademy.domain.Ingredient;
import com.infoshareacademy.domain.dto.IngredientView;
import com.infoshareacademy.repository.IngredientRepository;
import com.infoshareacademy.service.mapper.IngredientMapper;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Stateless
public class IngredientService {

    @EJB
    IngredientRepository ingredientRepository;

    @Inject
    IngredientMapper ingredientMapper;

    public List<IngredientView> findIngredientsByName(String partialIngredientName) {
        //moze jakie logowanie zrobic w service???
        final List<Ingredient> ingredientsByName = ingredientRepository.findIngredientsByName(partialIngredientName);

        return ingredientMapper.toView(ingredientsByName);
    }

    public List<IngredientView> findIngredientsByName(List<String> partialIngredientNames) {
        Set<Ingredient> ingredientsByName = new HashSet<>();
        for (String name : partialIngredientNames) {
            ingredientsByName.addAll(ingredientRepository.findIngredientsByName(name));
        }
        return ingredientMapper.toView(List.copyOf(ingredientsByName));
    }

}
