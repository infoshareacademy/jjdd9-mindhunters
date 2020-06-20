package com.infoshareacademy.service;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.User;
import com.infoshareacademy.repository.DrinkRepository;
import com.infoshareacademy.repository.StatisticsRepositoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;
import java.util.List;


@Stateless
public class AdminManagementRecipeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminManagementRecipeService.class.getName());

    @EJB
    private DrinkRepository drinkRepository;

    @EJB
    StatisticsRepositoryBean statisticsRepositoryBean;

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


    public boolean updateDrink(Long id,Drink newDrink) {
        Drink drink = drinkRepository.findDrinkById(id);

        if (newDrink != null) {

            drink.setDrinkName(newDrink.getDrinkName());
            drink.setAlcoholStatus(newDrink.getAlcoholStatus());
            drink.setImage(newDrink.getImage());
            drink.setCategory(newDrink.getCategory());
            drink.setRecipe(newDrink.getRecipe());
            drink.setDrinkIngredient(newDrink.getDrinkIngredient());

            drinkRepository.update(id, drink);
            return true;
        }
        return false;
    }
}
