package com.infoshareacademy.service;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.dto.DrinkLiveSearchView;
import com.infoshareacademy.repository.DrinkRepository;
import com.infoshareacademy.service.mapper.DrinkLiveSearchMapper;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class DrinkRestService {

    @Inject
    private DrinkRepository drinkRepository;

    @EJB
    private DrinkLiveSearchMapper liveSearchMapper;

    public List<DrinkLiveSearchView> findByNameLiveSearch(String partialDrinkName) {

        final List<Drink> drinks = drinkRepository.liveSearchDrinksByName(partialDrinkName);

        return liveSearchMapper.toView(drinks);
    }

}
