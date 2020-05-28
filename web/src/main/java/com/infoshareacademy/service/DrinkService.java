package com.infoshareacademy.service;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.repository.DrinkRepositoryBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.management.Query;
import java.util.List;
import java.util.Optional;

@RequestScoped
public class DrinkService {

    @Inject
    DrinkRepositoryBean drinkRepository;

    public List<Drink> findAllDrinks() {
        return drinkRepository.findAllDrinks();
    }

    public List<Drink> findAllDrinksByCategories(List<String> category) {
        return drinkRepository.findAllDrinksByCategories(category);
    }
}
