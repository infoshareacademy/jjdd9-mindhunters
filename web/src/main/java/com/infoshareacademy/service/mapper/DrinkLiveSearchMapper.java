package com.infoshareacademy.service.mapper;


import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.dto.DrinkLiveSearchView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class DrinkLiveSearchMapper {

    private static final Logger logger = LoggerFactory.getLogger(DrinkLiveSearchMapper.class.getName());

    public List<DrinkLiveSearchView> toView(List<Drink> drinks) {
        List<DrinkLiveSearchView> drinkViews = new ArrayList<>();
        for (Drink drink : drinks) {
            drinkViews.add(toView(drink));
        }
            return drinkViews;
        }

    public DrinkLiveSearchView toView(Drink drink) {
        DrinkLiveSearchView drinkView = new DrinkLiveSearchView();
        drinkView.setId(drink.getId());
        drinkView.setDrinkId(drink.getDrinkId());
        drinkView.setDrinkName(drink.getDrinkName());
        return drinkView;
    }
}
