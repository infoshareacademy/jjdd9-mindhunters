package com.infoshareacademy.service;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.DrinksDatabase;
import com.infoshareacademy.menu.DisplayMenu;
import com.infoshareacademy.utilities.UserInput;
import com.infoshareacademy.utilities.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

import static com.infoshareacademy.menu.DisplayMenu.clearScreen;


public class DrinkEditor {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private DrinkCreator drinkCreator = new DrinkCreator();
    private Drink editedDrink = new Drink();
    private Drink preEditDrink = new Drink();
    private static final String END_LINE = "\n -------------------------------------------- ";
    private UserInput userInput = new UserInput();
    private static final String USER_MESSAGE = "Wrong input. Please choose number from the list.";


    public DrinkEditor() {

    }

    public boolean editDrinkFromDatabase(String id) {
        for (Drink drink : DrinksDatabase.getINSTANCE().getDrinks()) {
            if (drink.getDrinkId().trim().equalsIgnoreCase(id)) {
                editedDrink = drink;
                preEditDrink = drink;
                editNavigation(editedDrink);
                return true;
            }
        }
        return false;
    }

    private void editNavigation(Drink editedDrink) {
        boolean cont = true;
        do {
            displayEditMenu();
            switch (userInput.getUserNumericInput()) {
                case 1:
                    DisplayMenu.clearScreen();
                    STDOUT.info("Previous drink name: {}\n\n", editedDrink.getDrinkName());
                    editedDrink.setDrinkName(userInput.getUserStringInput("Type name of drink: "));
                    editedDrink.setModifiedDate(LocalDateTime.now());
                    break;
                case 2:
                    DisplayMenu.clearScreen();
                    STDOUT.info("Previous drink category: {}\n\n", editedDrink.getCategoryName());
                    drinkCreator.setUserDrinkCategory(editedDrink);
                    editedDrink.setModifiedDate(LocalDateTime.now());
                    break;
                case 3:
                    DisplayMenu.clearScreen();
                    STDOUT.info("Previous drink alcohol status: {}\n\n", editedDrink.getAlcoholStatus());
                    drinkCreator.setUserDrinkAlcoholStatus(editedDrink);
                    editedDrink.setModifiedDate(LocalDateTime.now());
                    break;
                case 4:
                    DisplayMenu.clearScreen();
                    STDOUT.info("Previous drink recipe: {}\n\n", editedDrink.getRecipe());
                    editedDrink.setRecipe(userInput.getUserStringInput("Type drink recipe: "));
                    editedDrink.setModifiedDate(LocalDateTime.now());
                    break;
                case 5:
                    DisplayMenu.clearScreen();
                    STDOUT.info("Previous drink image URL: {}\n\n", editedDrink.getImageUrl());
                    editedDrink.setImageUrl(userInput.getUserStringInput("Type drink image URL: "));
                    editedDrink.setModifiedDate(LocalDateTime.now());
                    break;
                case 6:
                    DisplayMenu.clearScreen();
                    DrinkService.printDrinkIngrAndMeasures(editedDrink);
                    editedDrink.setIngredients(drinkCreator.setUserDrinkIngredientAndMeasure(15));
                    editedDrink.setModifiedDate(LocalDateTime.now());
                    break;
                case 7:
                    STDOUT.info("Drink edit complete. Thank you.\n");
                    cont = false;
                    break;
                default:
                    STDOUT.info(USER_MESSAGE);
                    Utilities.freezeConsole();
                    break;
            }
        } while (cont);
    }


    private void displayEditMenu() {
        clearScreen();
        STDOUT.info("\n ---------------- DRINK EDIT ----------------- ");
        STDOUT.info("\n|  [1] to edit drink name                     |" +
                "\n|  [2] to edit drink category                 |" +
                "\n|  [3] to edit alcohol status                 |" +
                "\n|  [4] to edit recipe                         |" +
                "\n|  [5] to edit image url                      |" +
                "\n|  [6] to edit ingredients                    |" +
                "\n|  [7] to complete drink edit                 |");
        STDOUT.info(END_LINE);
    }

}
