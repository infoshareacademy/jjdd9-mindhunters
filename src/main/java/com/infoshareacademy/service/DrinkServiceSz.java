package com.infoshareacademy.service;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.DrinksDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class DrinkServiceSz {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    List<Drink> database = DrinksDatabase.getINSTANCE().getDrinks();

    public DrinkServiceSz() {
    }

    public void searchDrinkByName() {

        Scanner scanner = new Scanner(System.in);

        String inputSearch = "";

        clearScreen();
        STDOUT.info("\nInput drink name: ");
        List<Drink> outputSearch = new ArrayList<>();
        inputSearch = scanner.next().toLowerCase();
        if (inputSearch.length() > 2) {
            for (Drink drink : database) {
                String name = drink.getDrinkName().toLowerCase();
                if (name.startsWith(inputSearch)) {
                    outputSearch.add(drink);
                }
            }
            if (outputSearch.isEmpty()) {
                STDOUT.info("No matching result found.\n");
            } else {
                printFoundDrink(outputSearch);
                STDOUT.info("\n");
            }
        } else {
            STDOUT.info("Input min. 3 characters.\n");
            searchDrinkByName();
        }
        String userInput = "";
        while (!(userInput.equalsIgnoreCase("y") || userInput.equalsIgnoreCase("n"))) {
            STDOUT.info("\nDo you want to repeat the search? <y/n>: ");
            userInput = scanner.next();
            clearScreen();
        }
        if (userInput.equalsIgnoreCase("y")) {
            clearScreen();
            searchDrinkByName();
        }
    }

    public void searchDrinkByIngridient() {

        Scanner scanner = new Scanner(System.in);

        clearScreen();
        STDOUT.info("\nInput ingridients name: ");
        List<Drink> outputSearch = new ArrayList<>();

        List<String> inputSearch = Arrays.asList(scanner.nextLine().toLowerCase().split(","));

        for (String input : inputSearch) {
            Iterator i = inputSearch.iterator();
            if (input.length() <= 2) {
                inputSearch.remove(i);
            }
        }

        for (Drink drink : database) {
            List<String> ingrediensNamestList = drink.getIngridientsNamesList();

            for (String ingredientName: ingrediensNamestList)
            {
                if (ingrediensNamestList.containsAll(inputSearch)){
                    outputSearch.add(drink);
                }
            }
        }
        if (outputSearch.isEmpty()) {
            STDOUT.info("No matching result found.\n");
        } else {
            printFoundDrink(outputSearch);
            STDOUT.info("\n");
        }

/*        String userInput = "";
        while (!(userInput.equalsIgnoreCase("y") || userInput.equalsIgnoreCase("n"))) {
            STDOUT.info("\nDo you want to repeat the search? <y/n>: ");
            userInput = scanner.next();
            clearScreen();
        }
        if (userInput.equalsIgnoreCase("y")) {
            clearScreen();
            searchDrinkByName();
        }*/
    }


    public void printFoundDrink(List<Drink> drinkList) {
        for (Drink drink : drinkList) {
            STDOUT.info("\n{}\n *ID: {}, *Category: {}, {};", drink.getDrinkName().toUpperCase(),
                    drink.getDrinkId(), drink.getCategoryName(), drink.getAlcoholStatus());
            STDOUT.info("\n {}",drink.getIngridientsNamesList());
            STDOUT.info("\n");

        }
    }

    private static void clearScreen() {
        STDOUT.info("\033[H\033[2J");
    }


}


