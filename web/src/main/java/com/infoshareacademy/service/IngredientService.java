package com.infoshareacademy.service;

import com.infoshareacademy.domain.Ingredient;
import com.infoshareacademy.domain.dto.DrinkIngredientView;
import com.infoshareacademy.domain.dto.IngredientView;
import com.infoshareacademy.repository.IngredientRepository;
import com.infoshareacademy.service.mapper.IngredientMapper;
import com.infoshareacademy.servlet.LoggerServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Stateless
public class IngredientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IngredientService.class.getName());

    @EJB
    IngredientRepository ingredientRepository;

    @Inject
    IngredientMapper ingredientMapper;

    public List<IngredientView> findIngredientsByName(String partialIngredientName) {
        LOGGER.debug("Searching for ingredients by partial name input");

        final List<Ingredient> ingredientsByName = ingredientRepository.findIngredientsByName(partialIngredientName);

        return ingredientMapper.toView(ingredientsByName);
    }

    public List<IngredientView> findIngredientsByName(List<String> partialIngredientNames) {
        LOGGER.debug("Searching for ingredients by partial name list input");
        Set<Ingredient> ingredientsByName = new HashSet<>();
        for (String name : partialIngredientNames) {
            ingredientsByName.addAll(ingredientRepository.findIngredientsByName(name));
        }
        return ingredientMapper.toView(List.copyOf(ingredientsByName));
    }

}
