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
import java.util.Optional;

@Stateless
public class DrinkService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DrinkService.class.getName());

    @EJB
    private DrinkRepository drinkRepository;

    @Inject
    private FullDrinkMapper fullDrinkMapper;

    @Inject
    private IngredientMapper ingredientMapper;



    public FullDrinkView findDrinkById(Long drinkId) {
        LOGGER.debug("Searching drink id");
        Drink foundDrink = drinkRepository.findDrinkById(drinkId);
        if (foundDrink == null){
            return null;
        }
        return fullDrinkMapper.toView(foundDrink);
    }

    public List<FullDrinkView> findDrinksByName(String partialDrinkName, int pageNumber) {
        LOGGER.debug("Searching drinks by name with pagination");
        List<Drink> foundDrinks = drinkRepository.paginatedFindDrinksByName(partialDrinkName, pageNumber);
        return fullDrinkMapper.toView(foundDrinks);
    }

    public List<FullDrinkView> findDrinkByIngredients(List<IngredientView> ingredientViews, int pageNumber) {
        LOGGER.debug("Searching drinks by ingredients with pagination");
        final List<Ingredient> ingredients = ingredientMapper.toEntity(ingredientViews);
        final List<Drink> foundDrinksByIngredients = drinkRepository.paginatedFindDrinkByIngredients(ingredients, pageNumber);
        return fullDrinkMapper.toView(foundDrinksByIngredients);
    }

    public List<FullDrinkView> findAllDrinks() {
        List<Drink> drinks = drinkRepository.findAllDrinks();
        return fullDrinkMapper.toView(drinks);
    }

    public List<FullDrinkView> findAllDrinksByCategories(List<Long> category,int pageNumber) {
        LOGGER.debug("Searching drinks by categories with pagination");
        List<Drink> drinks = drinkRepository.paginatedDrinksByCategories(category, pageNumber);
        return fullDrinkMapper.toView(drinks);
    }

    public List<FullDrinkView> paginationDrinkList(int pageNumber) {
        LOGGER.debug("Get all drinks paginated");
        List<Drink> drinks = drinkRepository.paginatedDrinksList(pageNumber);
        return fullDrinkMapper.toView(drinks);
    }

    public int maxPageNumberDrinkList() {
        int maxPageNumber = drinkRepository.maxPageNumberDrinkList();
        return maxPageNumber;

    }

    public int maxPageNumberDrinksByCategories(List<Long> category) {
        int maxPageNumber = drinkRepository.maxPageNumberDrinksByCategories(category);
        return maxPageNumber;
    }

    public int maxPageNumberDrinksByName(String name) {
        int maxPageNumber = drinkRepository.maxPageNumberDrinksByName(name);
        return maxPageNumber;
    }

    public int maxPageNumberDrinksByIngredients(List<IngredientView> ingredientViews) {
        final List<Ingredient> ingredients = ingredientMapper.toEntity(ingredientViews);
        int maxPageNumber = drinkRepository.maxPageNumberDrinksByIngredients(ingredients);
        return maxPageNumber;
    }
}
