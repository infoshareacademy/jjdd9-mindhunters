package com.infoshareacademy.service;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.DrinksDatabase;
import com.infoshareacademy.domain.Ingredient;
import com.infoshareacademy.utilities.UserInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class DrinkManagement {

    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    DrinksDatabase database = DrinksDatabase.getINSTANCE();
    private int initialDrinkId = 0; //to be loaded from properties
    UserInput userInput = new UserInput();

    public void createUserDrink() {
        Drink userDrink = new Drink();
        setUserDrinkId(userDrink);
        STDOUT.info("Type name of drink:");
        userDrink.setDrinkName(userInput.getUserStringInput());
        setUserDrinkCategory(userDrink);
        setUserDrinkAlcoholStatus(userDrink);
        STDOUT.info("Type drink recipe: ");
        userDrink.setRecipe(userInput.getUserStringInput());
        STDOUT.info("Type drink image URL: ");
        userDrink.setRecipe(userInput.getUserStringInput());
        userDrink.setIngredients(setUserDrinkIngredientAndMeasure(15));


    }

    private void setUserDrinkCategory(Drink userDrink) {
        STDOUT.info("Choose category number:");
        DrinkService.printAllCategories(database);
        int userChoice = 0;
        do {
            userChoice = userInput.getUserNumericInput();
            if (userChoice > 0 && userChoice < 10) {
                break;
            }
            STDOUT.info("Wrong input.");
        } while (true);
        userDrink.setCategoryName(DrinkService.getCategories(database).get(userChoice - 1));
    }

    public void setUserDrinkId(Drink userDrink) {
        initialDrinkId++;
        String drinkId = "u" + initialDrinkId;
        userDrink.setDrinkId(drinkId);
    }

    private void setUserDrinkAlcoholStatus(Drink userDrink) {
        STDOUT.info("Choose alcohol status:");
        DrinkService.printAllAlcoholStatuses(database);
        int userChoice = 0;
        do {
            userChoice = userInput.getUserNumericInput();
            if (userChoice > 0 && userChoice < DrinkService.getAlcoholStatuses(database).size() + 1) {
                break;
            }
            STDOUT.info("Wrong input.");
        } while (true);
        userDrink.setAlcoholStatus((DrinkService.getAlcoholStatuses(database).get(userChoice - 1)));
    }

    private List<Ingredient> setUserDrinkIngredientAndMeasure(int maxCapacity) {
        List<Ingredient> ingredients = new ArrayList<>();
        String name;
        String measure;
        while (true) {
            STDOUT.info("Type ingredient and measure separated by '|' ");
            String[] ingredientInput = userInput.getUserStringInput().split("|");
            while (ingredientInput.length < 2 || ingredientInput[0].isBlank() || ingredientInput[1].isBlank()) {
                STDOUT.info("Wrong input. Try again. ");
                ingredientInput = userInput.getUserStringInput().split("|");
            }
            name = ingredientInput[0];
            measure = ingredientInput[1];
            ingredients.add(new Ingredient(name, measure));
            STDOUT.info("Would you like to add more ingredients/measures? Press [y] to continue...");
            if (!userInput.getUserStringInput().toLowerCase().equals("y") || ingredients.size() < maxCapacity) {
                break;
            }
        }
        return ingredients;
    }

}
