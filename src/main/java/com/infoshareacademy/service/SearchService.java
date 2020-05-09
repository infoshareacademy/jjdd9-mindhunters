package com.infoshareacademy.service;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.DrinksDatabase;
import com.infoshareacademy.domain.Ingredient;
import com.infoshareacademy.utilities.PropertiesUtilities;
import com.infoshareacademy.utilities.UserInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SearchService {

    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private final UserInput userInput = new UserInput();
    List<Drink> database = DrinksDatabase.getINSTANCE().getDrinks();
    List<String> allIngredients = getAllIngredient(database);
    List<String> allCategories = getAllCategories(database);
    PropertiesUtilities propertiesUtilities = new PropertiesUtilities();
    String orderby = propertiesUtilities.getProperty("orderby");

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
                    STDOUT.info("\n");
                    foundDrink = chooseDrinkFromList(outputSearch);
                    isFound = true;
                } else {
                    STDOUT.info("No matching result found.\n");
                    break;
                }
            } else {
                STDOUT.info("Input min. 3 characters.\n");
            }
        } while (!isFound);

        if (!isFound) {
            foundDrink = repeatSearchDrinkByName();
        }
        return foundDrink;
    }

    private Drink repeatSearchDrinkByName() {
        Drink foundDrink = new Drink();
        while (true) {
            String input = userInput.getUserStringInput("\nDo you want to repeat the search? <y/n>: ");
            if (input.equalsIgnoreCase("y")) {
                clearScreen();
                return searchDrinkByName();
            } else if (!input.equalsIgnoreCase("n")) {
                STDOUT.info("Wrong input.\n");
            } else break;
        }
        return foundDrink;
    }

    public Drink searchDrinkByIngredient() {
        Drink foundDrink = new Drink();
        clearScreen();
        List<String> foundIngredients = new ArrayList<>();

        addIngredientToList(foundIngredients);

        addNextIngredientsToList(foundIngredients);

        List<String> ingredientsChosenByUser = normalizeIngredientsList(foundIngredients);
        List<Drink> OutputSearch = getDrinkList(database, ingredientsChosenByUser);

        if (OutputSearch.isEmpty() || ingredientsChosenByUser.isEmpty()) {
            STDOUT.info("No matching drink name found.\n");
        } else {
            foundDrink = chooseDrinkFromList(OutputSearch);
        }
        return foundDrink;
    }

    private List<String> normalizeIngredientsList(List<String> foundIngredients) {
        return foundIngredients.stream()
                .filter(s -> !s.isBlank())
                .distinct()
                .map(String::toLowerCase)
                .map(String::trim)
                .map(word -> word.replaceAll(" ", ""))
                .collect(Collectors.toList());
    }

    private void addIngredientToList(List<String> foundIngredients) {
        String inputIngredientName = userInput.getUserStringInput("\nInput ingredient name: ");

        String search = findIngredient(inputIngredientName);
        if (!search.isBlank()) {
            foundIngredients.add(search);
            clearScreen();
        }
    }

    private void addNextIngredientsToList(List<String> foundIngredients) {
        boolean isCorrect = false;
        while (!isCorrect) {
            printCurrentlyFoundIngredients(foundIngredients);
            String input = userInput.getUserStringInput("\nDo you want to add next ingredient to search? <y/n>: ");
            clearScreen();
            if (input.equalsIgnoreCase("y")) {
                clearScreen();
                addIngredientToList(foundIngredients);
            } else if (!input.equalsIgnoreCase("n")) {
                STDOUT.info("Wrong input. \n");
            } else {
                isCorrect = true;
            }
        }
    }

    private void printCurrentlyFoundIngredients(List<String> foundIngredients) {
        if (!foundIngredients.isEmpty()) {
            STDOUT.info("\nCurrently added ingredients to search: {}\n", foundIngredients.stream()
                    .distinct().collect(Collectors.toList()).toString());
        }
    }

    List<Drink> getDrinkList(List<Drink> drinks, List<String> ingredients) {
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

    private Drink chooseDrinkFromList(List<Drink> outputSearch) {
        Drink foundDrink = new Drink();

        Stream<Drink> drinkStream = outputSearch.stream();
        List<Drink> sortedList = null;
        switch (orderby) {
            case "asc":
                sortedList = drinkStream.sorted(Comparator.comparing(Drink::getDrinkName)).collect(Collectors.toList());
                printFoundDrinkList(Collections.unmodifiableList(sortedList));
                break;
            case "desc":
                sortedList = drinkStream.sorted(Comparator.comparing(Drink::getDrinkName).reversed()).collect(Collectors.toList());
                printFoundDrinkList(Collections.unmodifiableList(sortedList));
                break;
        }

        boolean isCorrectNumber = false;
        STDOUT.info("\nWhich recipe would you like to display?\n ");
        do {
            int recipeNumber = userInput.getUserNumericInput();
            if (recipeNumber >= 1 && recipeNumber <= outputSearch.size()) {
                if (sortedList != null) {
                    foundDrink = sortedList.get(recipeNumber - 1);
                }
                isCorrectNumber = true;
            } else {
                STDOUT.info("\nInput correct number of desired recipe. ");
            }
        } while (!isCorrectNumber);
        return foundDrink;
    }

    private void printFoundDrinkList(List<Drink> drinkList) {
        int count = 1;
        clearScreen();
        for (Drink drink : drinkList) {
            STDOUT.info("\n[{}] {}\n *ID: {}, *Category: {}, {};", count, drink.getDrinkName().toUpperCase(),
                    drink.getDrinkId(), drink.getCategoryName(), drink.getAlcoholStatus());
            STDOUT.info("\n {}", drink.getIngredientsNamesList());
            STDOUT.info("\n");
            count++;
        }
    }

    protected List<String> getAllIngredient(List<Drink> drinkList) {

        return drinkList.stream()
                .flatMap(a -> a.getIngredients().stream())
                .map(Ingredient::getName)
                .map(String::toString)
                .map(String::toUpperCase)
                .distinct()
                .collect(Collectors.toList());
    }

    private String findIngredient(String inputSearch) {

        List<String> outputSearch = new ArrayList<>();

        if (inputSearch.length() > 2) {
            for (String ingredient : allIngredients) {
                if (ingredient.toLowerCase().contains(inputSearch.toLowerCase())) {
                    outputSearch.add(ingredient);
                }
            }
            if (outputSearch.isEmpty()) {
                STDOUT.info("\nNo matching ingredient found.\n");
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
        STDOUT.info("\nWhich ingredient would you like to add to search list?\n ");
        do {
            int ingredientNumber = userInput.getUserNumericInput();
            if (ingredientNumber >= 1 && ingredientNumber <= outputSearch.size()) {
                return outputSearch.get(ingredientNumber - 1);
            } else STDOUT.info("\nInput correct number of desired ingredient. ");
        } while (true);
    }

    protected List<String> getAllCategories(List<Drink> drinkList) {

        return drinkList.stream()
                .map(drink -> drink.getCategoryName())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public Drink SearchByCategory() {

        Stream<String> categoryStream = allCategories.stream();
        List<String> sortedList = null;
        switch (orderby) {
            case "asc":
                sortedList = categoryStream.sorted(Comparator.naturalOrder()).collect(Collectors.toList());
                printFoundCategoryList(sortedList);
                break;
            case "desc":
                sortedList = categoryStream.sorted(Comparator.reverseOrder()).collect(Collectors.toList());
                printFoundCategoryList(sortedList);
                break;
        }
        String category = chooseCategoryFromList(sortedList);
        List<Drink> drinks = searchDrinkByCategory(category);
        clearScreen();
        STDOUT.info("\nChosen category: {}\n", category);
        return chooseDrinkFromList(drinks);
    }

    private void printFoundCategoryList(List<String> categoryList) {
        int count = 1;
        for (String category : categoryList) {
            STDOUT.info("\n[{}] {}", count, category);
            count++;
        }
    }

    private String chooseCategoryFromList(List<String> categories) {
        STDOUT.info("\n\nWhich category would you like to choose?\n ");
        do {
            int categoryNumber = userInput.getUserNumericInput();
            if (categoryNumber >= 1 && categoryNumber <= categories.size()) {
                return categories.get(categoryNumber - 1);
            } else STDOUT.info("\nInput correct number of desired category. ");
        } while (true);
    }

    private List<Drink> searchDrinkByCategory(String category) {

        return database.stream()
                .filter(drink -> drink.getCategoryName().equals(category))
                .collect(Collectors.toList());
    }

    private static void clearScreen() {
        STDOUT.info("\033[H\033[2J");
    }

}


