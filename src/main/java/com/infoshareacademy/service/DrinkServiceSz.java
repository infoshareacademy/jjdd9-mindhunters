package com.infoshareacademy.service;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.DrinksDatabase;
import com.infoshareacademy.utilities.UserInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DrinkServiceSz {

    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private final UserInput userInput = new UserInput();

    List<Drink> database = DrinksDatabase.getINSTANCE().getDrinks();

    public DrinkServiceSz() {
    }

    public void searchDrinkByName() {

        clearScreen();
        List<Drink> outputSearch = new ArrayList<>();

        String inputSearch = userInput.getUserStringInput("\nInput drink name: ").toLowerCase();
        if (inputSearch.length() > 2) {
            for (Drink drink : database) {
                String name = drink.getDrinkName().toLowerCase();
                if (name.contains(inputSearch)) {
                    outputSearch.add(drink);
                    printFoundDrink(outputSearch);
                    STDOUT.info("\n");
                    chooseDrinkFromList(outputSearch);
                }
            }
            if (outputSearch.isEmpty()) {
                STDOUT.info("No matching result found.\n");
            }
        } else {
            STDOUT.info("Input min. 3 characters.\n");
            searchDrinkByName();
        }

        boolean isCorrect = false;
        while (!isCorrect && outputSearch.isEmpty()) {
            String input = userInput.getUserStringInput("\nDo you want to repeat the search? <y/n>: ");
            if (input.equalsIgnoreCase("y")) {
                clearScreen();
                searchDrinkByName();
                isCorrect = true;
            } else if (!input.equalsIgnoreCase("n")) {
                STDOUT.info("Wrong input.\n");
            } else break;
        }
    }

    public void searchDrinkByIngredient() {


        clearScreen();

        List<Drink> outputSearch = new ArrayList<>();

        List<String> inputSearch = new ArrayList<>();


        String ingredientName = userInput.getUserStringInput("\nInsert ingredient name: ");

        inputSearch.add(ingredientName);

        boolean isCorrect = false;
        while(!isCorrect) {
            String input = userInput.getUserStringInput("Do you want to add next ingredient to search? <y/n>: ");
            if (input.equalsIgnoreCase("y")) {
                clearScreen();
                userInput.getUserStringInput("\nInsert another ingredient: ");
                inputSearch.add(ingredientName);
            } else if (!input.equalsIgnoreCase("n")) {
                STDOUT.info("Wrong input. \n");
            } else isCorrect = true;
        }

        List<String> newInputList = inputSearch.stream()
                .filter(Objects::nonNull)
                .distinct()
                .filter(string -> string.length() > 2)
                .map(String::toLowerCase)
                .map(String::trim)
                .map(word -> word.replaceAll(" ", ""))
                .collect(Collectors.toList());

        List<Drink> newOutputSearch = database.stream()
                .filter(drink -> (drink.getIngredientsNamesList().stream()
                        .map(String::toLowerCase)
                        .map(word -> word.replaceAll(" ", ""))
                        .collect(Collectors.toList())
                ).containsAll(newInputList))
                .collect(Collectors.toList());

        if (newOutputSearch.isEmpty()) {
            STDOUT.info("No matching result found.\n");
        } else {
            printFoundDrink(newOutputSearch);
            chooseDrinkFromList(newOutputSearch);
        }
    }

    private void printFoundDrink(List<Drink> drinkList) {
        int count = 1;
        for (Drink drink : drinkList) {
            STDOUT.info("\n[{}] {}\n *ID: {}, *Category: {}, {};", count, drink.getDrinkName().toUpperCase(),
                    drink.getDrinkId(), drink.getCategoryName(), drink.getAlcoholStatus());
            STDOUT.info("\n {}", drink.getIngredientsNamesList());
            STDOUT.info("\n");
            count++;
        }
    }

    private void chooseDrinkFromList(List<Drink> outputSearch) {
        boolean isCorrect = false;
        STDOUT.info("\nWhich recipe would you like to display? ");
        do {
            int recipeNumber = userInput.getUserNumericInput();
            if (recipeNumber >= 1 && recipeNumber <= outputSearch.size()) {
                STDOUT.info(outputSearch.get(recipeNumber - 1).toString());
                isCorrect = true;
            } else STDOUT.info("\nInput correct number of desired recipe. ");
        } while (!isCorrect);
    }

    private static void clearScreen() {
        STDOUT.info("\033[H\033[2J");
    }


}


