package com.infoshareacademy.service;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.repository.DrinkRepository;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class DrinkService {

    private DrinkRepository drinkRepository;

    public List<Drink> findDrinkByName(String partialDrinkName) {

        if (partialDrinkName.length() < 2) {
            //pusta lista + brak resultatów albo error/wyjatek/za krótka nazwa

        }

        return drinkRepository.findAllDrinks().stream().fi;
    }
}
