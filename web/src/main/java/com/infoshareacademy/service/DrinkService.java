package com.infoshareacademy.service;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.DrinkIngredient;
import com.infoshareacademy.domain.Ingredient;
import com.infoshareacademy.domain.Measure;
import com.infoshareacademy.domain.dto.FullDrinkView;
import com.infoshareacademy.domain.dto.IngredientView;
import com.infoshareacademy.repository.DrinkRepository;
import com.infoshareacademy.service.mapper.FullDrinkMapper;
import com.infoshareacademy.service.mapper.IngredientMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Stateless
public class DrinkService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DrinkService.class.getName());
    private static final Integer PAGE_SIZE = 20;


    @EJB
    private DrinkRepository drinkRepository;

    @Inject
    private FullDrinkMapper fullDrinkMapper;

    @Inject
    private IngredientMapper ingredientMapper;

    @EJB
    private MeasureService measureService;

    @EJB
    private IngredientService ingredientService;

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

    public void save(Drink drink) {
        drinkRepository.save(drink);
    }

    public void deleteDrinkById(String drinkId) {
        Long id = Long.valueOf(drinkId);

        Drink drink = drinkRepository.findDrinkById(id);
        if (drink != null) {
            drinkRepository.delete(drink);
        }
    }
    @Transactional
    public void update(String drinkId, Drink updatedDrink) {
        Long id = Long.valueOf(drinkId);

        Drink drink = drinkRepository.findDrinkById(id);
        drink.setDrinkName(updatedDrink.getDrinkName());
        drink.setRecipe(updatedDrink.getRecipe());
        drink.setCategory(updatedDrink.getCategory());
        drink.setImage(updatedDrink.getImage());
        drink.setAlcoholStatus(updatedDrink.getAlcoholStatus());

        if (drink != null) {
            drinkRepository.update(drink);
        }
    }

}
