package com.infoshareacademy.service;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.User;
import com.infoshareacademy.repository.DrinkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;


@Stateless
public class AdminManagementRecipeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminManagementRecipeService.class.getName());

    @EJB
    private DrinkRepository drinkRepository;

    public boolean deleteDrinkById(Long id) {

        Drink drink = drinkRepository.findDrinkById(id);

        List<User> users = drink.getUsers();
        for (User user : users) {
            user.getDrinks().remove(drink);
        }

        drinkRepository.delete(id);
        return true;
    }


    public boolean updateDrink(Long id,Drink drink) {

        if (drink != null) {
            drinkRepository.update(id, drink);
            return true;
        }
        return false;
    }
}
