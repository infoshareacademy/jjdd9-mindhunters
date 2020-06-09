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


    private final static Integer PAGE_SIZE = 5;

    @EJB
    private DrinkRepository drinkRepository;

    @Inject
    private FullDrinkMapper fullDrinkMapper;

    @Inject
    private IngredientMapper ingredientMapper;


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

    public FullDrinkView findDrinkById(Long drinkId) {
        Drink foundDrink = drinkRepository.findDrinkById(drinkId);
        if (foundDrink == null) {
            return null;
        }
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


    public List<FullDrinkView> findByCategories(List<Long> category, int pageNumber) {
        int startPosition = (pageNumber - 1) * PAGE_SIZE;
        int endPosition = PAGE_SIZE;

        List<Drink> drinks = drinkRepository.findByCategories(category, startPosition, endPosition);
        return fullDrinkMapper.toView(drinks);
    }

    public int countPagesByCategories(List<Long> category) {
        int maxPageNumber = drinkRepository.countPagesByCategories(category);
        return maxPageNumber;

    }


    public List<FullDrinkView> findByAlcoholStatus(List<String> alcoholStatus, int pageNumber) {
        int startPosition = (pageNumber - 1) * PAGE_SIZE;
        int endPosition = PAGE_SIZE;

        List<Drink> drinks = drinkRepository.findByAlcoholStatus(alcoholStatus, startPosition, endPosition);
        return fullDrinkMapper.toView(drinks);
    }

    public int countPagesByAlcoholStatus(List<String> alcoholStatus) {
        int maxPageNumber = drinkRepository.countPagesByAlcoholStatus(alcoholStatus);
        return maxPageNumber;

    }

    public List<FullDrinkView> findByCategoriesAndAlcoholStatus(List<Long> category, List<String> alcoholStatus, int pageNumber) {
        int startPosition = (pageNumber - 1) * PAGE_SIZE;
        int endPosition = PAGE_SIZE;

        List<Drink> drinks = drinkRepository.findByCategoriesAndAlcoholStatus(category, alcoholStatus, startPosition, endPosition);
        return fullDrinkMapper.toView(drinks);
    }

    public int countPagesByCategoriesAndAlcoholStatus(List<Long> category, List<String> alcoholStatus) {
        int maxPageNumber = drinkRepository.countPagesByCategoriesAndAlcoholStatus(category, alcoholStatus);
        return maxPageNumber;
    }

    public SearchType checkingSearchingCase(Map<String, String[]> searchParam, int currentPage) {

        SearchType searchType = new SearchType();

        List<String> alcoholStatusQuery = Optional.ofNullable((searchParam.get("alcoholStatus")))
                .map(Arrays::asList).orElse(Collections.emptyList());
        List<String> categoriesQuery = Optional.ofNullable((searchParam.get("category")))
                .map(Arrays::asList).orElse(Collections.emptyList());

        List<Long> searchingCategory = new ArrayList<>();
        List<String> searchingAlcoholStatus = new ArrayList<>();

        String queryName;

        if (searchParam.containsKey("category") && searchParam.containsKey("alcoholStatus")) {

            searchingCategory = searchByCategoryService(searchParam.get("category"));

            searchingAlcoholStatus = searchByAlcoholStatusService(searchParam.get("alcoholStatus"));

        } else if (searchParam.containsKey("category")) {

            searchingCategory = searchByCategoryService(searchParam.get("category"));

        } else if (searchParam.containsKey("alcoholStatus")) {

            searchingAlcoholStatus = searchByAlcoholStatusService(searchParam.get("alcoholStatus"));

        }


        if (searchingCategory.size() > 0 && searchingAlcoholStatus.size() > 0) {


            List<FullDrinkView> drinksByCategoriesAndAlcoholStatus = findByCategoriesAndAlcoholStatus(searchingCategory, searchingAlcoholStatus, currentPage);

            queryName = "category=" + String.join("&&category=", categoriesQuery)
                    + "&&alcoholStatus=" + String.join("&&alcoholStatus=", alcoholStatusQuery);


            searchType.setDrinkViewList(drinksByCategoriesAndAlcoholStatus);

            searchType.setQueryName(queryName);

            int maxPage = countPagesByCategoriesAndAlcoholStatus(searchingCategory, searchingAlcoholStatus);

            searchType.setMaxPage(maxPage);


        } else if (searchingCategory.size() > 0) {


            List<FullDrinkView> drinksByCategories = findByCategories(searchingCategory, currentPage);

            searchType.setDrinkViewList(drinksByCategories);

            queryName = "category=" + String.join("&&category=", categoriesQuery);

            searchType.setQueryName(queryName);

            int maxPage = countPagesByCategories(searchingCategory);

            searchType.setMaxPage(maxPage);


        } else if (searchingAlcoholStatus.size() > 0) {

            List<FullDrinkView> drinksByAlcoholStatus = findByAlcoholStatus(searchingAlcoholStatus, currentPage);

            searchType.setDrinkViewList(drinksByAlcoholStatus);


            queryName = "&&alcoholStatus=" + String.join("&&alcoholStatus=", alcoholStatusQuery);

            searchType.setQueryName(queryName);

            int maxPage = countPagesByAlcoholStatus(searchingAlcoholStatus);

            searchType.setMaxPage(maxPage);


        } else {

            List<FullDrinkView> paginatedDrinkList = findAllDrinks(currentPage);

            int maxPage = countPagesFindAll();

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


    public static int getMaxPageNumber(String querySize) {
        return (int) Math.ceil((Double.valueOf(querySize) / PAGE_SIZE));
    }
}
