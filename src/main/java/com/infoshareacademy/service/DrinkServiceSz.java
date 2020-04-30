package com.infoshareacademy.service;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.DrinksDatabase;
import com.infoshareacademy.domain.Ingredient;
import org.davidmoten.text.utils.WordWrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
                if (name.contains(inputSearch)) {
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
        List<Drink> outputSearch = new ArrayList<>();

        List<String> inputSearch = new ArrayList<>();
        String ingridientName = "";

        STDOUT.info("\nInsert ingridient name: ");
        ingridientName = scanner.nextLine();

        inputSearch.add(ingridientName);


        String userInput = "";
        do {
            STDOUT.info("\nDo you want to add next ingridient to search? <y/n>: ");
            userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("y")) {
                clearScreen();
                STDOUT.info("\nInsert another ingridient: ");
                ingridientName = scanner.nextLine();
                inputSearch.add(ingridientName);
            } else if (!userInput.equalsIgnoreCase("n")) {
                STDOUT.info("Wrong input. Please insert <y/n>.");
            }
        } while (!(userInput.equalsIgnoreCase("n")));

        outputSearch = database.stream()
                .filter (drink ->
                                inputSearch.stream()
                                                .distinct()
                                                .filter(string -> string.length() > 2)
                                                .allMatch(input -> input.equalsIgnoreCase(drink
                                                        .getIngridientsNamesList()




/*
        for (String input : inputSearch) {
            Iterator i = inputSearch.iterator();
            if (input.length() <= 2) {
                inputSearch.remove(i);
            }
        }

        for (Drink drink : database) {
            List<String> ingrediensNamestList = drink.getIngridientsNamesList();

            for (String ingredientName : ingrediensNamestList) {
                if (ingrediensNamestList.containsAll(inputSearch)) {
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
*/


    }


    public void printFoundDrink(List<Drink> drinkList) {
        for (Drink drink : drinkList) {
            STDOUT.info("\n{}\n *ID: {}, *Category: {}, {};", drink.getDrinkName().toUpperCase(),
                    drink.getDrinkId(), drink.getCategoryName(), drink.getAlcoholStatus());
            STDOUT.info("\n {}", drink.getIngridientsNamesList());
            STDOUT.info("\n {}", drink.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            STDOUT.info("\n");
        }
    }

    private static void clearScreen() {
        STDOUT.info("\033[H\033[2J");
    }


}


