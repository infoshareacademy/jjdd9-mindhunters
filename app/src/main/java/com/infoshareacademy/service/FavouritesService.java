package com.infoshareacademy.service;

import com.infoshareacademy.domain.DrinkJson;
import com.infoshareacademy.domain.DrinksDatabase;
import com.infoshareacademy.domain.FavouritesDatabase;
import com.infoshareacademy.utilities.PropertiesUtilities;
import com.infoshareacademy.utilities.UserInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.infoshareacademy.domain.FavouritesDatabase.getInstFavourites;

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

    public List<DrinkJson> getAllFavourites(FavouritesDatabase database) {

        List<DrinkJson> favourDrinks = getFavourDrinkList(database);
        List<DrinkJson> sortedList = new ArrayList<>();
        if (favourDrinks.isEmpty()) {
            STDOUT.info("\nFavourites list empty.\n");

        } else {
            PropertiesUtilities propertiesUtilities = new PropertiesUtilities();
            String orderby = propertiesUtilities.getProperty("orderby");
            Stream<DrinkJson> sortedStream = favourDrinks.stream();
            switch (orderby) {
                case "asc":
                    sortedStream = sortedStream.sorted(Comparator.comparing(DrinkJson::getDrinkName));
                    sortedList = sortedStream.collect(Collectors.toList());
                    printSortedFavouritesList(Collections.unmodifiableList(sortedList));
                    break;
                case "desc":
                    sortedStream = sortedStream.sorted(Comparator.comparing(DrinkJson::getDrinkName).reversed());
                    sortedList = sortedStream.collect(Collectors.toList());
                    printSortedFavouritesList(Collections.unmodifiableList(sortedList));
                    break;
            }
        }
        return sortedList;
    }

    public DrinkJson chooseOneFavRecipeFromList(List<DrinkJson> sortedList) {
        DrinkJson foundDrink = new DrinkJson();
        UserInput userInput = new UserInput();
        boolean isCorrectNumber = false;
        if (userInput.getYesOrNo("\nWould you like to see details of recipe from the list? <y/n> ")) {
            STDOUT.info("\nWhich recipe would you like to display? ");
            do {
                int recipeNumber = userInput.getUserNumericInput();
                if (recipeNumber >= 1 && recipeNumber <= sortedList.size()) {
                    if (sortedList != null) {
                        foundDrink = sortedList.get(recipeNumber - 1);
                    }
                    isCorrectNumber = true;
                } else
                    STDOUT.info("\nInput correct number of desired recipe. ");
            } while (!isCorrectNumber);
        }
        return foundDrink;
    }

    private void printSortedFavouritesList(List<DrinkJson> sortedList) {
        int counter = 1;
        for (DrinkJson drink : sortedList) {
            STDOUT.info("\n[{}] {}\n *ID: {}, *Category: {}, {};", counter, drink.getDrinkName().toUpperCase(),
                    drink.getDrinkId(), drink.getCategoryName(), drink.getAlcoholStatus());
            STDOUT.info("\n {}", drink.getIngredientsNamesList());
            STDOUT.info("\n");
            counter++;
        }
    }

    private List<DrinkJson> getFavourDrinkList(FavouritesDatabase database) {
        List<DrinkJson> allDrinks = DrinksDatabase.getINSTANCE().getDrinks();
        List<DrinkJson> favourDrinks = new ArrayList<>();
        for (DrinkJson drink : allDrinks) {
            String drinkId = drink.getDrinkId();
            if (database.getFavouritesIds().contains(drinkId)) {
                favourDrinks.add(drink);
            }
        }
        return favourDrinks;
    }

    public void removeFromFavourites(String id) {
        final Set<String> favourIds = getInstFavourites().getFavouritesIds();
        if (favourIds.contains(id)) {
            favourIds.remove(id);
            STDOUT.info("Drink removed from favourites.");
        } else {
            STDOUT.info("Drink id not found. Try again. ");
        }
    }

    public void addToFavourites(String id) {
        final Set<String> favouritesIds = getInstFavourites().getFavouritesIds();
        for (DrinkJson drink : DrinksDatabase.getINSTANCE().getDrinks()) {
            if (drink.getDrinkId().equals(id)) {
                favouritesIds.add(id);
                STDOUT.info("Drink added to favourites.");
                return;
            }
        }
        STDOUT.info("Drink id not found. Try again. ");
    }

    public DrinkJson chooseOneFavouriteToRemoveFromList(List<DrinkJson> sortedList) {
        DrinkJson foundDrink = new DrinkJson();
        UserInput userInput = new UserInput();
        boolean isCorrectNumber = false;
        if (userInput.getYesOrNo("\nWould you like to remove any recipe from favourites? <y/n> ")) {
            STDOUT.info("\nWhich recipe would you like to remove? ");
            do {
                int recipeNumber = userInput.getUserNumericInput();
                if (recipeNumber >= 1 && recipeNumber <= sortedList.size()) {
                    if (sortedList != null) {
                        foundDrink = sortedList.get(recipeNumber - 1);
                    }
                    isCorrectNumber = true;
                } else
                    STDOUT.info("\nInput correct number of desired recipe. ");
            } while (!isCorrectNumber);
        }
        return foundDrink;
    }

}