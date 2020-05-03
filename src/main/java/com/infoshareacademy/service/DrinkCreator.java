package com.infoshareacademy.service;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.DrinksDatabase;
import com.infoshareacademy.domain.Ingredient;
import com.infoshareacademy.menu.DisplayMenu;
import com.infoshareacademy.utilities.UserInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DrinkCreator implements DatabaseOperator {

    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private static int maxExistingId = 0;
    private final UserInput userInput = new UserInput();

    @Override
    public boolean operate(String drinkId) {
        Drink userDrink = new Drink();
        userDrink.setDrinkId(drinkId.toString());

        DisplayMenu.clearScreen();
        userDrink.setDrinkName(userInput.getUserStringInput("Type name of drink: "));

        DisplayMenu.clearScreen();
        setUserDrinkCategory(userDrink);

        DisplayMenu.clearScreen();
        setUserDrinkAlcoholStatus(userDrink);

        DisplayMenu.clearScreen();
        userDrink.setRecipe(userInput.getUserStringInput("Type drink recipe: "));

        DisplayMenu.clearScreen();
        userDrink.setImageUrl(userInput.getUserStringInput("Type drink image URL: "));

        DisplayMenu.clearScreen();
        userDrink.setIngredients(setUserDrinkIngredientAndMeasure(15));
        userDrink.setModifiedDate(LocalDateTime.now());

        DrinksDatabase.getINSTANCE().addDrink(userDrink);
        return true;
    }

    void setUserDrinkCategory(Drink userDrink) {
        STDOUT.info("Choose category number:\n");
        DrinkService.printAllCategories(DrinksDatabase.getINSTANCE());
        int userChoice = 0;
        do {
            userChoice = userInput.getUserNumericInput();
            if (userChoice > 0 && userChoice <= DrinkService.getAllCategories(DrinksDatabase.getINSTANCE()).size()) {
                break;
            }
            STDOUT.info("Wrong input.\n");
        } while (true);
        userDrink.setCategoryName(DrinkService.getAllCategories(DrinksDatabase.getINSTANCE()).get(userChoice - 1));
    }

    public static String generateUserDrinkId() {
        if (maxExistingId == 0) {
            maxExistingId = Collections.max(DrinkService.getAllDrinkIdNumbers(DrinksDatabase.getINSTANCE()));
        }
        maxExistingId++;
        return String.valueOf(maxExistingId);
    }

    void setUserDrinkAlcoholStatus(Drink userDrink) {
        STDOUT.info("Choose alcohol status:\n");
        DrinkService.printAllAlcoholStatuses(DrinksDatabase.getINSTANCE());
        int userChoice = 0;
        do {
            userChoice = userInput.getUserNumericInput();
            if (userChoice > 0 && userChoice <= DrinkService.getAlcoholStatuses(DrinksDatabase.getINSTANCE()).size()) {
                break;
            }
            STDOUT.info("Wrong input.\n");
        } while (true);
        userDrink.setAlcoholStatus((DrinkService.getAlcoholStatuses(DrinksDatabase.getINSTANCE()).get(userChoice - 1)));
    }

    List<Ingredient> setUserDrinkIngredientAndMeasure(int maxCapacity) {
        List<Ingredient> ingredients = new ArrayList<>();
        String name;
        String measure;
        int counter = 1;
        do {
            name = userInput.getUserStringInput("Type ingredient no." + counter + " name: ");
            StringBuilder builder = new StringBuilder();
            String message =
                    builder.append("Type ").append("'").append(name).append("'").append(" measure: ").toString();
            measure = userInput.getUserStringInput(message);
            ingredients.add(new Ingredient(name, measure));
            STDOUT.info("\n");
            counter++;
        } while (userInput.getUserStringInput("If you want to add another ingredient [max 15] press [y]: ").equalsIgnoreCase(
                "y") && (ingredients.size() <= maxCapacity));
        return ingredients;
    }
}
