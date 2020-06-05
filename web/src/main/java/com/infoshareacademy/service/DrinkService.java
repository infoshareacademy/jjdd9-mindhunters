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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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


    public List<FullDrinkView> findAllDrinks() {
        List<Drink> drinks = drinkRepository.findAllDrinks();
        return fullDrinkMapper.toView(drinks);
    }

    public List<FullDrinkView> findByCategories(List<Long> category, int pageNumber) {
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


    public int maxPageNumberByCategories(List<Long> category) {
        int maxPageNumber = drinkRepository.maxPageNumberDrinksByCategories(category);
        return maxPageNumber;

    }


    public SearchType checkingSearchingCase(Map<String, String[]> searchParam, int currentPage) {


        SearchType searchType = new SearchType();

        if (searchParam.containsKey("category") && searchParam.containsKey("alcoholicStatus")) {


            return null;


        } else if (searchParam.containsKey("category")) {


            String[] query = searchParam.get("category");

            List<Long> searchingCategory = Arrays.stream(query)
                    .filter(Objects::nonNull)
                    .filter(StringUtils::isNoneBlank)
                    .filter(s -> s.matches("[0-9]+"))
                    .map(s -> Long.valueOf(s))
                    .collect(Collectors.toList());

            List<FullDrinkView> drinksByCategories = findByCategories(searchingCategory, currentPage);

            searchType.setDrinkViewList(drinksByCategories);

            String queryName = "category=" + Arrays.stream(query).collect(Collectors.joining("&&category="));

            searchType.setQueryName(queryName);

            int maxPage = maxPageNumberByCategories(searchingCategory);

            searchType.setMaxPage(maxPage);

            return searchType;


        } else if (searchParam.containsKey("alcoholicStatus")) {


            return null;
        } else {

            List<FullDrinkView> paginatedDrinkList = paginationDrinkList(currentPage);

            int maxPage = maxPageNumberDrinkList();

            searchType.setMaxPage(maxPage);

            return searchType;

        }


    }


}
