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

public class DrinkManagement {

    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private DrinksDatabase database;
    private UserInput userInput;
    private Integer maxExistingId;

    public DrinkManagement() {
        this.database = DrinksDatabase.getINSTANCE();
        this.userInput =  new UserInput();
        this.maxExistingId = 0;
    }

    public void createUserDrink() {
        Drink userDrink = new Drink();
        setUserDrinkId(userDrink);

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

        //TESTTESTSTESTSTEST
        System.out.println("");
        System.out.println(userDrink.toString());
    }

    private void setUserDrinkCategory(Drink userDrink) {
        STDOUT.info("Choose category number:\n");
        DrinkService.printAllCategories(database);
        int userChoice = 0;
        do {
            userChoice = userInput.getUserNumericInput();
            if (userChoice > 0 && userChoice <= DrinkService.getCategories(database).size()) {
                break;
            }
            STDOUT.info("Wrong input.\n");
        } while (true);
        userDrink.setCategoryName(DrinkService.getCategories(database).get(userChoice - 1));
    }

    public void setUserDrinkId(Drink userDrink) {
        if (maxExistingId == 0){
            maxExistingId = Collections.max(DrinkService.getDrinkIdNumbers(database));
        }
        maxExistingId++;
        userDrink.setDrinkId(maxExistingId.toString());
    }

    private void setUserDrinkAlcoholStatus(Drink userDrink) {
        STDOUT.info("Choose alcohol status:\n");
        DrinkService.printAllAlcoholStatuses(database);
        int userChoice = 0;
        do {
            userChoice = userInput.getUserNumericInput();
            if (userChoice > 0 && userChoice <= DrinkService.getAlcoholStatuses(database).size()) {
                break;
            }
            STDOUT.info("Wrong input.\n");
        } while (true);
        userDrink.setAlcoholStatus((DrinkService.getAlcoholStatuses(database).get(userChoice - 1)));
    }

    private List<Ingredient> setUserDrinkIngredientAndMeasure(int maxCapacity) {
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
