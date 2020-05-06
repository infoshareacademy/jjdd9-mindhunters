package com.infoshareacademy.service;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.DrinksDatabase;
import com.infoshareacademy.domain.Ingredient;
import com.infoshareacademy.utilities.UserInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class DrinkServiceSz {

    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private final UserInput userInput = new UserInput();
    List<Drink> database = DrinksDatabase.getINSTANCE().getDrinks();
    List<String> allIngredients = getAllIngredient(database);

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
                    printFoundDrinkList(outputSearch);
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
        List<String> inputSearch = new ArrayList<>();

        String ingredientName = userInput.getUserStringInput("\nInput ingredient name: ");

        inputSearch.add(findIngredient(ingredientName));

        boolean isCorrect = false;
        while (!isCorrect) {
            String input = userInput.getUserStringInput("Do you want to add next ingredient to search? <y/n>: ");
            if (input.equalsIgnoreCase("y")) {
                clearScreen();
                String nextIngredientName = userInput.getUserStringInput("\nInput another ingredient: ");
                inputSearch.add(findIngredient(nextIngredientName));
                            } else if (!input.equalsIgnoreCase("n")) {
                STDOUT.info("Wrong input. \n");
            } else isCorrect = true;
        }

        List<String> newInputList = inputSearch.stream()
                .filter(Objects::nonNull)
                .distinct()
                .map(String::toLowerCase)
                .map(String::trim)
                .map(word -> word.replaceAll(" ", ""))
                .collect(Collectors.toList());

        List<Drink> OutputSearch = database.stream()
                .filter(drink -> (drink.getIngredientsNamesList().stream()
                        .map(String::toLowerCase)
                        .map(word -> word.replaceAll(" ", ""))
                        .collect(Collectors.toList())
                ).containsAll(newInputList))
                .collect(Collectors.toList());



        if (OutputSearch.isEmpty()) {
            STDOUT.info("No matching drink name found.\n");
        } else {
            printFoundDrinkList(OutputSearch);
            chooseDrinkFromList(OutputSearch);
        }
    }

    private void printFoundDrinkList(List<Drink> drinkList) {
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
                DrinkService.printSingleDrink(outputSearch.get(recipeNumber - 1));
                isCorrect = true;
            } else STDOUT.info("\nInput correct number of desired recipe. ");
        } while (!isCorrect);
    }

    public List<String> getAllIngredient(List<Drink> drinkList) {

        List<String> ingredients = drinkList.stream()
                .flatMap(a -> a.getIngredients().stream())
                .map(Ingredient::getName)
                .map(String::toString)
                .map(String::toUpperCase)
                .distinct()
                .collect(Collectors.toList());

        STDOUT.info(ingredients.toString());
        return ingredients;
    }

    private String findIngredient(String inputSearch){


        List<String> outputSearch = new ArrayList<>();

            if (inputSearch.length() > 2) {
                for (String ingredient : allIngredients) {
                    if (ingredient.toLowerCase().contains(inputSearch.toLowerCase())) {
                        outputSearch.add(ingredient);
                    }
                }
                if (outputSearch.isEmpty()) {
                    STDOUT.info("No matching ingredient found.\n");
                } else {
                    printFoundIngredientList(outputSearch);
                    STDOUT.info("\n");
                    return chooseIngredientFromList(outputSearch);
                }
            } else {
                STDOUT.info("Input min. 3 characters.\n");
                String newInputSearch = userInput.getUserStringInput("\nInput ingredient name: ").toLowerCase();
                findIngredient(newInputSearch);
            }
            return null;
    }

    private void printFoundIngredientList(List<String> ingredientList) {
        int count = 1;
        for (String ingredient : ingredientList) {
            STDOUT.info("\n[{}] {}\n ", count, ingredient);
            count++;
        }
    }

    private String chooseIngredientFromList(List<String> outputSearch) {
        boolean isCorrect = false;
        STDOUT.info("\nWhich ingredient would you like to add to search list? ");
        do {
            int ingredientNumber = userInput.getUserNumericInput();
            if (ingredientNumber >= 1 && ingredientNumber <= outputSearch.size()) {
                return outputSearch.get(ingredientNumber - 1);
            } else STDOUT.info("\nInput correct number of desired ingredient. ");
        } while (!isCorrect);
        return null;
    }

    private static void clearScreen() {
        STDOUT.info("\033[H\033[2J");
    }

        //TODO wyswietlanie wszystkich drinków przy szukaniu błednego ingredients, kasowanie poprzedniego ingredients przy złym
        //TODO wyniku wyszukiwania - screen
}


