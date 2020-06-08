package com.infoshareacademy.service;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.dto.DrinkLiveSearchView;
import com.infoshareacademy.repository.DrinkRepository;
import com.infoshareacademy.service.mapper.DrinkLiveSearchMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class DrinkRestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DrinkRestService.class.getName());

    @Inject
    private DrinkRepository drinkRepository;

    @EJB
    private DrinkLiveSearchMapper liveSearchMapper;

    public List<DrinkLiveSearchView> findByNameLiveSearch(String partialDrinkName) {
        LOGGER.debug("Searching by name in livesearch");
        final List<Drink> drinks = drinkRepository.liveSearchDrinksByName(partialDrinkName);

        return liveSearchMapper.toView(drinks);
    }

}
