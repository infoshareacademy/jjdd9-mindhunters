package com.infoshareacademy.service;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.DrinkJson;
import com.infoshareacademy.repository.DrinkRepositoryBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class DrinkService {

    @Inject
    DrinkRepositoryBean drinkRepository;

    public List<DrinkJson> findAllDrinks() {
        return drinkRepository.findAllDrinks();
    }

    public List<DrinkJson> findAllDrinksByCategories(List<String> category) {
        return drinkRepository.findAllDrinksByCategories(category);
    }

    public List<DrinkJson> paginationDrinkList(int pageNumber) {
        return drinkRepository.paginationDrinkList(pageNumber);
    }

    public void save(Drink drink) {
        drinkRepository.save(drink);
    }
}
