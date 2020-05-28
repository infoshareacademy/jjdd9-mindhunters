package com.infoshareacademy.service;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.repository.DrinkRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class DrinkService {

    @Inject
    DrinkRepository drinkRepository;

    public List<Drink> findAll() {
        return drinkRepository.findAll();
    }


}
