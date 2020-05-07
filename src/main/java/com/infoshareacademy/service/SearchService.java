package com.infoshareacademy.service;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.DrinksDatabase;
import com.infoshareacademy.domain.Ingredient;
import com.infoshareacademy.utilities.UserInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchService {

    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private final UserInput userInput = new UserInput();
    List<Drink> database = DrinksDatabase.getINSTANCE().getDrinks();
    List<String> allIngredients = getAllIngredient(database);

    public SearchService() {
    }

    public Drink searchDrinkByName() {

        clearScreen();
        List<Drink> outputSearch = new ArrayList<>();

        Drink foundDrink = new Drink();

        boolean isFound = false;
        do {
            String inputSearch = userInput.getUserStringInput("\nInput drink name: ").toLowerCase();
            if (inputSearch.length() > 2) {
                for (Drink drink : database) {
                    String name = drink.getDrinkName().toLowerCase();
                    if (name.contains(inputSearch)) {
                        outputSearch.add(drink);
                    }
                }
                if (outputSearch.size() > 0) {
                    printFoundDrinkList(outputSearch);
                    STDOUT.info("\n");
                    foundDrink = chooseDrinkFromList(outputSearch);
                    isFound = true;
                } else if (outputSearch.isEmpty()) {
                    STDOUT.info("No matching result found.\n");
                    break;
                }
            } else {
                STDOUT.info("Input min. 3 characters.\n");
            }
        } while (!isFound);

        boolean isCorrect = false;
        while (!isCorrect && !isFound) {
            String input = userInput.getUserStringInput("\nDo you want to repeat the search? <y/n>: ");
            if (input.equalsIgnoreCase("y")) {
                clearScreen();
                searchDrinkByName();
                isCorrect = true;
            } else if (!input.equalsIgnoreCase("n")) {
                STDOUT.info("Wrong input.\n");
            } else break;
        }
        return foundDrink;
    }

    public void searchDrinkByIngredient() {

        clearScreen();
        List<String> foundIngredients = new ArrayList<>();

        addIngredientToList(foundIngredients);

        addNextIngridientsToList(foundIngredients);

        List<String> ingredientsChosenByUser = normalizeIngridientsList(foundIngredients);
        STDOUT.info(ingredientsChosenByUser.toString());
        List<Drink> OutputSearch = getDrinks(database, ingredientsChosenByUser);

        if (OutputSearch.isEmpty()) {
            STDOUT.info("No matching drink name found.\n");
        } else {
            printFoundDrinkList(OutputSearch);
            chooseDrinkFromList(OutputSearch);
        }
    }

    private List<String> normalizeIngridientsList(List<String> foundIngredients) {
        return foundIngredients.stream()
                    .filter(s -> !s.isBlank())
                    .distinct()
                    .map(String::toLowerCase)
                    .map(String::trim)
                    .map(word -> word.replaceAll(" ", ""))
                    .collect(Collectors.toList());
    }

    private void addNextIngridientsToList(List<String> foundIngredients) {
        boolean isCorrect = false;
        while (!isCorrect) {
            String input = userInput.getUserStringInput("Do you want to add next ingredient to search? <y/n>: ");
            if (input.equalsIgnoreCase("y")) {
                clearScreen();
                addIngredientToList(foundIngredients);
            } else if (!input.equalsIgnoreCase("n")) {
                STDOUT.info("Wrong input. \n");
            } else isCorrect = true;
        }
    }

    private void addIngredientToList(List<String> foundIngredients) {
        String ingredientName = userInput.getUserStringInput("\nInput ingredient name: ");

        String search = findIngredient(ingredientName);
        if (!search.isBlank()) {
            foundIngredients.add(search);
        }
    }

    List<Drink> getDrinks(List<Drink> drinks, List<String> ingredients) {
        return drinks.stream()
                .filter(drink -> containsIngredients(ingredients, drink))
                .collect(Collectors.toList());
    }

    private boolean containsIngredients(List<String> ingredients, Drink drink) {

        return drink.getIngredientsNamesList().stream()
                .map(String::toLowerCase)
                .map(word -> word.replaceAll(" ", ""))
                .collect(Collectors.toList())
                .containsAll(ingredients);
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

    private Drink chooseDrinkFromList(List<Drink> outputSearch) {
        Drink foundDrink = new Drink();
        boolean isCorrect = false;
        STDOUT.info("\nWhich recipe would you like to display? ");
        do {
            int recipeNumber = userInput.getUserNumericInput();
            if (recipeNumber >= 1 && recipeNumber <= outputSearch.size()) {
                //DrinkService.printSingleDrink(outputSearch.get(recipeNumber - 1));
                foundDrink = outputSearch.get(recipeNumber-1);
                isCorrect = true;
            } else STDOUT.info("\nInput correct number of desired recipe. ");
        } while (!isCorrect);
        return foundDrink ;
    }

    public List<String> getAllIngredient(List<Drink> drinkList) {

        List<String> ingredients = drinkList.stream()
                .flatMap(a -> a.getIngredients().stream())
                .map(Ingredient::getName)
                .map(String::toString)
                .map(String::toUpperCase)
                .distinct()
                .collect(Collectors.toList());

        return ingredients;
    }

    private String findIngredient(String inputSearch) {


        List<String> outputSearch = new ArrayList<>();


            if (inputSearch.length() > 2) {
                for (String ingredient : allIngredients) {
                    if (ingredient.toLowerCase().contains(inputSearch.toLowerCase()) && !(inputSearch.length() == 0)) {
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
                return findIngredient(newInputSearch);

            }

        return "";
    }

    private void printFoundIngredientList(List<String> ingredientList) {
        int count = 1;
        for (String ingredient : ingredientList) {
            STDOUT.info("\n[{}] {}", count, ingredient);
            count++;
        }
    }

    private String chooseIngredientFromList(List<String> outputSearch) {
        STDOUT.info("\nWhich ingredient would you like to add to search list? ");
        do {
            int ingredientNumber = userInput.getUserNumericInput();
            if (ingredientNumber >= 1 && ingredientNumber <= outputSearch.size()) {
                return outputSearch.get(ingredientNumber - 1);
            } else STDOUT.info("\nInput correct number of desired ingredient. ");
        } while (true);
    }

    private static void clearScreen() {
        STDOUT.info("\033[H\033[2J");
    }

    //TODO wyswietlanie wszystkich drinków przy szukaniu błednego ingredients, kasowanie poprzedniego ingredients przy złym
    // wyniku wyszukiwania - screen

}


