package com.infoshareacademy.service;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.Ingredient;
import com.infoshareacademy.domain.dto.FullDrinkView;
import com.infoshareacademy.domain.dto.IngredientView;
import com.infoshareacademy.repository.DrinkRepository;
import com.infoshareacademy.service.mapper.FullDrinkMapper;
import com.infoshareacademy.service.mapper.IngredientMapper;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

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



/*    public List<Drink> findDrinkById(String partialDrinkName) {

        if (partialDrinkName.length() < 2) {
            //pusta lista + brak resultatów albo error/wyjatek/za krótka nazwa

        }

        return drinkRepository.findAllDrinks().stream();
    }*/


    public List<FullDrinkView> findByCategories(List<Long> category, int pageNumber) {
        List<Drink> drinks = drinkRepository.findByCategories(category, pageNumber);
        return fullDrinkMapper.toView(drinks);
    }

    public List<FullDrinkView> findAll(int pageNumber) {
        List<Drink> drinks = drinkRepository.findAllDrinks(pageNumber);
        return fullDrinkMapper.toView(drinks);
    }

    public int maxPageNumberFindAll() {
        int maxPageNumber = drinkRepository.maxPageNumberFindAll();
        return maxPageNumber;

    }


    public int maxPageNumberByCategories(List<Long> category) {
        int maxPageNumber = drinkRepository.maxPageNumberByCategories(category);
        return maxPageNumber;

    }

    public List<FullDrinkView> findByAlcoholStatus (List<String> alcoholStatus, int pageNumber) {
        List<Drink> drinks = drinkRepository.findByAlcoholStatus(alcoholStatus, pageNumber);
        return fullDrinkMapper.toView(drinks);
    }

    public int maxPageNumberByAlcoholStatus(List<String> alcoholStatus) {
        int maxPageNumber = drinkRepository.maxPageNumberByAlcoholStatus(alcoholStatus);
        return maxPageNumber;

    }

    public List<FullDrinkView> findByCategoriesAndAlcoholStatus (List<Long> category, List<String> alcoholStatus, int pageNumber) {
        List<Drink> drinks = drinkRepository.findByCategoriesAndAlcoholStatus(category, alcoholStatus, pageNumber);
        return fullDrinkMapper.toView(drinks);
    }

    public int maxPageNumberByCategoriesAndAlcoholStatus (List<Long> category, List<String> alcoholStatus) {
        int maxPageNumber = drinkRepository.maxPageNumberByCategoriesAndAlcoholStatus(category, alcoholStatus);
        return maxPageNumber;
    }



    public SearchType checkingSearchingCase(Map<String, String[]> searchParam, int currentPage) {


        SearchType searchType = new SearchType();

        if (searchParam.containsKey("category") && searchParam.containsKey("alcoholStatus")) {

            String[] categoriesQuery = searchParam.get("category");

            List<Long> searchingCategory = searchByCategoryService(categoriesQuery);

            String[] alcoholStatusQuery = searchParam.get("alcoholStatus");

            List<String> searchingAlcoholStatus = searchByAlcoholStatusService(alcoholStatusQuery);

            List<FullDrinkView> drinksByCategoriesAndAlcoholStatus = findByCategoriesAndAlcoholStatus (searchingCategory, searchingAlcoholStatus, currentPage);

            String queryName = "category=" + Arrays.stream(categoriesQuery).collect(Collectors.joining("&&category=")); /////TODO

            searchType.setDrinkViewList(drinksByCategoriesAndAlcoholStatus);

            searchType.setQueryName(queryName);

            int maxPage = maxPageNumberByCategoriesAndAlcoholStatus(searchingCategory, searchingAlcoholStatus);

            searchType.setMaxPage(maxPage);



        } else if (searchParam.containsKey("category")) {


            String[] categoriesQuery = searchParam.get("category");

            List<Long> searchingCategory = searchByCategoryService(categoriesQuery);

            List<FullDrinkView> drinksByCategories = findByCategories(searchingCategory, currentPage);

            searchType.setDrinkViewList(drinksByCategories);

            String queryName = "category=" + Arrays.stream(categoriesQuery).collect(Collectors.joining("&&category="));

            searchType.setQueryName(queryName);

            int maxPage = maxPageNumberByCategories(searchingCategory);

            searchType.setMaxPage(maxPage);



        } else if (searchParam.containsKey("alcoholStatus")) {

            String[] query = searchParam.get("alcoholStatus");

            List<String> searchingAlcoholStatus = searchByAlcoholStatusService(query);

            List<FullDrinkView> drinksByAlcoholStatus = findByAlcoholStatus(searchingAlcoholStatus,currentPage);

            searchType.setDrinkViewList(drinksByAlcoholStatus);

            String queryName = "alcoholStatus=" + Arrays.stream(query).collect(Collectors.joining("&&alcoholStatus="));

            searchType.setQueryName(queryName);

            int maxPage = maxPageNumberByAlcoholStatus(searchingAlcoholStatus);

            searchType.setMaxPage(maxPage);


        } else {

            List<FullDrinkView> paginatedDrinkList = findAll(currentPage);

            int maxPage = maxPageNumberFindAll();

            searchType.setDrinkViewList(paginatedDrinkList);
            searchType.setMaxPage(maxPage);

            return searchType;

        }

        return searchType;

    }

    private List<String> searchByAlcoholStatusService(String[] query) {
        return Arrays.stream(query)
                        .filter(Objects::nonNull)
                        .filter(StringUtils::isNoneBlank)
                        .collect(Collectors.toList());
    }

    private List<Long> searchByCategoryService(String[] query) {
        return Arrays.stream(query)
                        .filter(Objects::nonNull)
                        .filter(StringUtils::isNoneBlank)
                        .filter(s -> s.matches("[0-9]+"))
                        .map(s -> Long.valueOf(s))
                        .collect(Collectors.toList());
    }


}
