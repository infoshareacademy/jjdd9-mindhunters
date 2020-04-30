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
    private DrinksDatabase database;
    private Drink editedDrink = new Drink();
    private static final String END_LINE = "\n -------------------------------------------- ";
    private UserInput userInput;
    private static final String USER_MESSAGE = "Wrong input. Please choose number from the list.";


    public DrinkEditor() {
        this.database = DrinksDatabase.getINSTANCE();
    }

    private boolean editDrinkFromDatabase(String id) {
        for (Drink drink : database.getDrinks()) {
            if (drink.getDrinkId().trim().equalsIgnoreCase(id)) {
                editedDrink = drink;
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
                    editedDrink.setDrinkName(userInput.getUserStringInput("Type name of drink: "));
                    editedDrink.setModifiedDate(LocalDateTime.now());
                    break;
                case 2:
                    DrinkService.printAllCategories(database);
                    drinkCreator.setUserDrinkCategory(editedDrink);
                    editedDrink.setModifiedDate(LocalDateTime.now());
                    break;
                case 3:
                    DrinkService.printAllAlcoholStatuses(database);
                    DisplayMenu.clearScreen();
                    drinkCreator.setUserDrinkAlcoholStatus(editedDrink);
                    editedDrink.setModifiedDate(LocalDateTime.now());
                    break;
                case 4:
                    DisplayMenu.clearScreen();
                    editedDrink.setRecipe(userInput.getUserStringInput("Type drink recipe: "));
                    editedDrink.setModifiedDate(LocalDateTime.now());
                    break;
                case 5:
                    DisplayMenu.clearScreen();
                    editedDrink.setImageUrl(userInput.getUserStringInput("Type drink image URL: "));
                    editedDrink.setModifiedDate(LocalDateTime.now());
                    break;
                case 6:
                    DisplayMenu.clearScreen();
                    editedDrink.setIngredients(drinkCreator.setUserDrinkIngredientAndMeasure(15));
                    editedDrink.setModifiedDate(LocalDateTime.now());
                    break;
                case 7:
                    STDOUT.info("Drink edit complete. Thank you. ");
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
        STDOUT.info("\n|  [1] to edit drink name           |" +
                "\n|  [2] to edit drink category        |" +
                "\n|  [3] to edit alcohol status |" +
                "\n|  [4] to edit recipe            |" +
                "\n|  [5] to edit image url                           |" +
                "\n|  [6] to edit ingredients                           |" +
                "\n|  [7] to complete drink edit                           |");
        STDOUT.info(END_LINE);
    }

}
