package com.infoshareacademy.service;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.Ingredient;
import com.infoshareacademy.repository.DrinkRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class DrinkService {

    @EJB
    private DrinkRepository drinkRepository;

    public Drink findDrinkById(Long drinkId) {

        return drinkRepository.findDrinkById(drinkId);
    }

    public List<Drink> findDrinkByName(String partialDrinkName) {

        return drinkRepository.findDrinkByName(partialDrinkName);
    }

    public List<Drink> findDrinkByIngredients(List<String> partialIngredientNames) {

        return drinkRepository.findDrinkByIngredients(partialIngredientNames);
    }



/*    public List<Drink> findDrinkById(String partialDrinkName) {

        if (partialDrinkName.length() < 2) {
            //pusta lista + brak resultatów albo error/wyjatek/za krótka nazwa

        }

        return drinkRepository.findAllDrinks().stream();
    }*/
}
