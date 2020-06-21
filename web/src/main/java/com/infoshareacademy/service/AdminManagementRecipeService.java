package com.infoshareacademy.service;

import com.infoshareacademy.domain.*;
import com.infoshareacademy.repository.DrinkRepository;
import com.infoshareacademy.repository.StatisticsRepositoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Stateless
public class AdminManagementRecipeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminManagementRecipeService.class.getName());

    @EJB
    private DrinkRepository drinkRepository;

    @EJB
    StatisticsRepositoryBean statisticsRepositoryBean;

    @EJB
    MeasureService measureService;

    @EJB
    IngredientService ingredientService;

    @EJB
    CategoryService categoryService;


    @Transactional
    public boolean deleteDrinkById(Long id) {

        Drink drink = drinkRepository.findDrinkById(id);

        List<User> users = drink.getUsers();
        for (User user : users) {
            user.getDrinks().remove(drink);
        }
        statisticsRepositoryBean.deleteFavouritesByDrink(drink);
        drinkRepository.delete(id);
        return true;
    }

    @Transactional
    public boolean updateDrink(Long id,Drink newDrink) {
        Drink drink = drinkRepository.findDrinkById(id);

        if (newDrink != null) {

            drink.setDrinkName(newDrink.getDrinkName());
            drink.setAlcoholStatus(newDrink.getAlcoholStatus());
            drink.setImage(newDrink.getImage());

            Category category =categoryService.getOrCreate(newDrink.getCategory().getName()) ;
            drink.setCategory(category);


            List<String> measures = newDrink.getDrinkIngredients().stream()
                    .map(drinkIngredient -> drinkIngredient.getMeasure())
                    .map(measure -> measure.getQuantity())
                    .collect(Collectors.toList());

            List<String> ingredients = newDrink.getDrinkIngredients().stream()
                    .map(drinkIngredient -> drinkIngredient.getIngredient())
                    .map(ingredient -> ingredient.getName())
                    .collect(Collectors.toList());

            List<Measure> measureList = new ArrayList<>();
            List<Ingredient> ingredientList = new ArrayList<>();

            String measure1 = measures.get(0);

            for (String measure : measures) {
                measureList.add(measureService.getOrCreate(measure));
            }
            for (String ingredient : ingredients) {
                ingredientList.add(ingredientService.getOrCreate(ingredient.toString()));
            }

            List<DrinkIngredient> drinkIngredientsList = new ArrayList<>();

            for (int i = 0; i < measureList.size(); i++) {
                DrinkIngredient drinkIngredient = new DrinkIngredient();

                drinkIngredient.setMeasure(measureList.get(i));
                drinkIngredient.setIngredient(ingredientList.get(i));
                drinkIngredient.setDrinkId(drink);

                drinkIngredientsList.add(drinkIngredient);
            }
            drink.getDrinkIngredients().clear();
            drink.setDrinkIngredients(drinkIngredientsList);


            LocalDateTime formatDateTime = LocalDateTime.now();
            drink.setDate(formatDateTime);

//            String now = LocalDateTime.now().toString();
//
//            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//
//            LocalDateTime formatDateTime = LocalDateTime.parse(now, dateFormatter);
//
//            drink.setDate(formatDateTime);


            drinkRepository.deleteIngredientsFromDrink(id);
            drinkRepository.update(id, drink);
            return true;
        }
        return false;
    }
}
