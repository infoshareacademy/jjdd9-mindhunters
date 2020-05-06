package com.infoshareacademy.service;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.DrinksDatabase;
import com.infoshareacademy.domain.FavouritesDatabase;
import com.infoshareacademy.utilities.PropertiesUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

public class FavouritesService {

    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    public void loadFavouritesList() {
        FavouritesDatabase favourDatabase = FavouritesDatabase.getInstFavourites();
        if (favourDatabase.getFavouritesIds().isEmpty()) {
            Set<String> favourIdSet = new HashSet<>();
            String fileName = "Favourites.json";
            try {
                favourIdSet.addAll(JsonReader.jsonFavouritesReader(fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            favourDatabase.addAllFavourites(favourIdSet);
        }
    }

    public void printAllFavourites(FavouritesDatabase database) {

        List<Drink> allDrinks = DrinksDatabase.getINSTANCE().getDrinks();
        List<Drink> favourDrinks = new ArrayList<>();
        for (Drink drink : allDrinks) {
            String drinkId = drink.getDrinkId();
            if (database.getFavouritesIds().contains(drinkId)) {
                favourDrinks.add(drink);
            }
        }
        if (favourDrinks.isEmpty()) {
            STDOUT.info("\nFavourites list empty.\n");

        } else {
            PropertiesUtilities propertiesUtilities = new PropertiesUtilities();
            String orderby = propertiesUtilities.getProperty("orderby");
            Stream<Drink> sorted = favourDrinks.stream();
            switch (orderby) {
                case "asc":
                    sorted = sorted.sorted(Comparator.comparing(Drink::getDrinkName));
                    break;
                case "desc":
                    sorted = sorted.sorted(Comparator.comparing(Drink::getDrinkName).reversed());
                    break;
            }
            sorted.forEach(drink -> {
                STDOUT.info("\n{}\n *ID: {}, *Category: {}, {};", drink.getDrinkName().toUpperCase(),
                        drink.getDrinkId(), drink.getCategoryName(), drink.getAlcoholStatus());
                STDOUT.info("\n");
            });
        }

    }

}