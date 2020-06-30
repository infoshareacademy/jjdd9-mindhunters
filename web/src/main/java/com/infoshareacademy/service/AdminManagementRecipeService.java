package com.infoshareacademy.service;

import com.infoshareacademy.context.ContextHolder;
import com.infoshareacademy.domain.*;
import com.infoshareacademy.repository.DrinkRepository;
import com.infoshareacademy.repository.StatisticsRepositoryBean;
import com.infoshareacademy.service.mapper.FullDrinkMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Stateless
public class AdminManagementRecipeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminManagementRecipeService.class.getName());

    @EJB
    private DrinkRepository drinkRepository;

    @EJB
    private StatisticsRepositoryBean statisticsRepositoryBean;

    @EJB
    private MeasureService measureService;

    @EJB
    private IngredientService ingredientService;

    @Inject
    private FullDrinkMapper fullDrinkMapper;

    @EJB
    CategoryService categoryService;




    @Transactional
    public boolean deleteDrinkById(Long id) {
        Drink drink = drinkRepository.findDrinkById(id);

        List<User> users = drink.getUsers();
        for (User user : users) {
            user.getDrinks().remove(drink);
        }
        statisticsRepositoryBean.deleteStatisticsByDrink(drink);
        drinkRepository.delete(id);
        return true;
    }

    @Transactional
    public boolean addOrUpdateDrink(Long id, Drink newDrink, ContextHolder contextHolder) {
        Drink drink = drinkRepository.findDrinkById(id);


        if (drink == null){
            drink = new Drink();
        }

        if (newDrink != null) {
            drink.setConfirmUserEmail(newDrink.getConfirmUserEmail());
            drink.setDrinkName(newDrink.getDrinkName());
            drink.setAlcoholStatus(newDrink.getAlcoholStatus());
            drink.setImage(newDrink.getImage());
            drink.setRecipe(newDrink.getRecipe());
            Category category =categoryService.getOrCreate(newDrink.getCategory().getName()) ;
            drink.setCategory(category);


            List<String> measures = newDrink.getDrinkIngredients().stream()
                    .filter(Objects::nonNull)
                    .map(drinkIngredient -> drinkIngredient.getMeasure())
                    .map(measure -> measure.getQuantity())
                    .collect(Collectors.toList());

            List<String> ingredients = newDrink.getDrinkIngredients().stream()
                    .filter(Objects::nonNull)
                    .map(drinkIngredient -> drinkIngredient.getIngredient())
                    .map(ingredient -> ingredient.getName())
                    .collect(Collectors.toList());

            List<Measure> measureList = new ArrayList<>();
            List<Ingredient> ingredientList = new ArrayList<>();

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


        }

        if (id != 0L){
            drink.setManageAction("EDITED");
            drinkRepository.deleteIngredientsFromDrink(id);
            drinkRepository.update(id, drink);
        } else {
            drink.setManageAction("ADDED");
            drinkRepository.save(drink);

        }
        return true;
    }

    public Drink setApproved(long drinkId) {
        Drink drink = drinkRepository.findDrinkById(drinkId);
        drink.setApproved(true);
        drinkRepository.update(drinkId, drink);

        return drink;
    }


    public Drink rejectDrinkProposal(long drinkId) {
        Drink drink = drinkRepository.findDrinkById(drinkId);
        drinkRepository.delete(drinkId);
        return drink;
    }

    public Drink setApprovedExistingDrink(long drinkId) {
        Drink newDrink = drinkRepository.findDrinkById(drinkId);
        statisticsRepositoryBean.deleteStatisticsByDrink(newDrink);

        Long newDrinkParentId = newDrink.getParentId();
        Drink oldDrink = drinkRepository.findDrinkById(newDrinkParentId);
        statisticsRepositoryBean.deleteStatisticsByDrink(oldDrink);

        newDrink.setApproved(true);
        newDrink.setId(newDrinkParentId);

        drinkRepository.update(newDrink.getId(), newDrink);
        drinkRepository.delete(drinkId);

        return newDrink;
    }

    public Drink setApprovedDeleteDrink(long drinkId) {
        Drink drink = drinkRepository.findDrinkById(drinkId);
        Long idTobeDeleted = drink.getParentId();

        statisticsRepositoryBean.deleteStatisticsByDrink(drink);

        drinkRepository.delete(drinkId);
        drinkRepository.delete(idTobeDeleted);

        return drink;
    }
}
