package com.infoshareacademy.service;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.DrinksDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

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

        List<String> newInputList = inputSearch.stream()
                .filter(Objects::nonNull)
                .distinct()
                .filter(string -> string.length() > 2)
                .map(String::toLowerCase)
                .map(String::trim)
                .map(word -> word.replaceAll(" ", ""))
                .collect(Collectors.toList());

        outputSearch = database.stream()
                .filter(drink -> (drink.getIngridientsNamesList().stream()
                        .map(String::toLowerCase)
                        .map(word -> word.replaceAll(" ", ""))
                        .collect(Collectors.toList())
                ).containsAll(newInputList))
                .collect(Collectors.toList());

        if (outputSearch.isEmpty()) {
            STDOUT.info("No matching result found.\n");
        } else {
            printFoundDrink(outputSearch);
            STDOUT.info("\n");
        }


    }


    public void printFoundDrink(List<Drink> drinkList) {
        for (Drink drink : drinkList) {
            STDOUT.info("\n{}\n *ID: {}, *Category: {}, {};", drink.getDrinkName().toUpperCase(),
                    drink.getDrinkId(), drink.getCategoryName(), drink.getAlcoholStatus());
            STDOUT.info("\n {}", drink.getIngridientsNamesList());
            STDOUT.info("\n");
        }
    }

    private static void clearScreen() {
        STDOUT.info("\033[H\033[2J");
    }


}


