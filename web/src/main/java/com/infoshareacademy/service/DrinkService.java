package com.infoshareacademy.service;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.Ingredient;
import com.infoshareacademy.domain.dto.FullDrinkView;
import com.infoshareacademy.domain.dto.IngredientView;
import com.infoshareacademy.repository.DrinkRepository;
import com.infoshareacademy.service.mapper.FullDrinkMapper;
import com.infoshareacademy.service.mapper.IngredientMapper;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class DrinkService {


    @EJB
    private DrinkRepository drinkRepository;

    @Inject
    private FullDrinkMapper fullDrinkMapper;

    @Inject
    private IngredientMapper ingredientMapper;



    public FullDrinkView findDrinkById(Long drinkId) {
        Drink foundDrink = drinkRepository.findDrinkById(drinkId);
        return fullDrinkMapper.toView(foundDrink);
    }

    public List<FullDrinkView> findDrinksByName(String partialDrinkName) {
        List<Drink> foundDrinks = drinkRepository.findDrinksByName(partialDrinkName);
        return fullDrinkMapper.toView(foundDrinks);
    }

    public List<FullDrinkView> findDrinkByIngredients(List<IngredientView> ingredientViews) {
        final List<Ingredient> ingredients = ingredientMapper.toEntity(ingredientViews);
        final List<Drink> foundDrinksByIngredients = drinkRepository.findDrinkByIngredients(ingredients);
        return fullDrinkMapper.toView(foundDrinksByIngredients);
    }

    public Long findTotalDrinksAmount() {
        return drinkRepository.findTotalDrinksAmount();
    }

    public List<FullDrinkView> findAllDrinks() {
        List<Drink> drinks = drinkRepository.findAllDrinks();
        return fullDrinkMapper.toView(drinks);
    }

    public List<FullDrinkView> findAllDrinksByCategories(List<Long> category,int pageNumber) {
        List<Drink> drinks = drinkRepository.paginatedDrinksByCategories(category, pageNumber);
        return fullDrinkMapper.toView(drinks);
    }

    public List<FullDrinkView> paginationDrinkList(int pageNumber) {
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
}
