package com.infoshareacademy.service;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.Ingredient;
import com.infoshareacademy.domain.dto.FullDrinkView;
import com.infoshareacademy.domain.dto.IngredientView;
import com.infoshareacademy.repository.DrinkRepository;
import com.infoshareacademy.service.mapper.FullDrinkMapper;
import com.infoshareacademy.service.mapper.IngredientMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class DrinkService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DrinkService.class.getName());
    private static final Integer PAGE_SIZE = 5;


    @EJB
    private DrinkRepository drinkRepository;

    @Inject
    private FullDrinkMapper fullDrinkMapper;

    @Inject
    private IngredientMapper ingredientMapper;


    public FullDrinkView getDrinkById(Long drinkId) {
        LOGGER.debug("Searching drink id");
        Drink foundDrink = drinkRepository.findDrinkById(drinkId);
        if (foundDrink == null) {
            return null;
        }
        return fullDrinkMapper.toView(foundDrink);
    }

    public List<FullDrinkView> findDrinksByName(String partialDrinkName, int pageNumber) {
        LOGGER.debug("Searching drinks by name with pagination");

        int startPosition = (pageNumber - 1) * PAGE_SIZE;
        int endPosition = PAGE_SIZE;

        List<Drink> foundDrinks = drinkRepository.findDrinksByName(partialDrinkName, startPosition, endPosition);
        return fullDrinkMapper.toView(foundDrinks);
    }

    public int countPagesByName(String name) {
        int maxPageNumber = drinkRepository.countPagesByName(name);
        return maxPageNumber;
    }


    public List<FullDrinkView> findByIngredients(List<IngredientView> ingredientViews, int pageNumber) {
        LOGGER.debug("Searching drinks by ingredients with pagination");

        int startPosition = (pageNumber - 1) * PAGE_SIZE;
        int endPosition = PAGE_SIZE;

        final List<Ingredient> ingredients = ingredientMapper.toEntity(ingredientViews);
        final List<Drink> foundDrinksByIngredients = drinkRepository.findByIngredients(ingredients,
                startPosition, endPosition);
        return fullDrinkMapper.toView(foundDrinksByIngredients);
    }

    public int countPagesByIngredients(List<IngredientView> ingredientViews) {
        final List<Ingredient> ingredients = ingredientMapper.toEntity(ingredientViews);
        int maxPageNumber = drinkRepository.countPagesByIngredients(ingredients);
        return maxPageNumber;
    }


    public List<FullDrinkView> findAllDrinks(int pageNumber) {
        int startPosition = (pageNumber - 1) * PAGE_SIZE;
        int endPosition = PAGE_SIZE;

        List<Drink> drinks = drinkRepository.findAllDrinks(startPosition, endPosition);
        return fullDrinkMapper.toView(drinks);
    }

    public int countPagesFindAll() {
        int maxPageNumber = drinkRepository.countPagesFindAll();
        return maxPageNumber;

    }

    public int getMaxPageNumber(String querySize) {
        return (int) Math.ceil((Double.valueOf(querySize) / PAGE_SIZE));
    }


}
