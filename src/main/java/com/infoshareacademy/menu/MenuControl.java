package com.infoshareacademy.menu;

import com.infoshareacademy.domain.DrinksDatabase;
import com.infoshareacademy.service.DrinkService;
import com.infoshareacademy.service.JsonWriter;
import com.infoshareacademy.utilities.UserInput;
import com.infoshareacademy.utilities.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MenuControl {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private static final String USER_MESSAGE = "Wrong input. Please choose number from the list.";

    private boolean exit = false;
    private final UserInput userInput = new UserInput();
    private final DrinkService drinkService = new DrinkService();

    public void mainNavigation() {
        drinkService.loadDrinkList();
        do {
            DisplayMenu.displayMainMenu();
            switch (userInput.getUserNumericInput()) {
                case 1:
                    browseNavigation();
                    break;
                case 2:
                    manageNavigation();
                    break;
                case 3:
                    settingsNavigation();
                    break;
                case 4:
                    DisplayMenu.displayExit();
                    exit = true;
                    break;
                default:
                    STDOUT.info(USER_MESSAGE);
                    Utilities.freezeConsole();
                    break;
            }
        } while (!exit);
    }

    public void browseNavigation() {
        boolean cont = true;
        do {
            DisplayMenu.displayBrowseMenu();
            switch (userInput.getUserNumericInput()) {
                case 1:
                    drinkService.printAllDrinks(DrinksDatabase.getINSTANCE());
                    userInput.getUserInputAnyKey();
                    break;
                case 2:
                    STDOUT.info("SEARCH BY NAME");
                    break;
                case 3:
                    STDOUT.info("SEARCH BY INGREDIENT");
                    break;
                case 4:
                    STDOUT.info("SEARCH BY CATEGORY");
                    break;
                case 5:
                    cont = false;
                    break;
                case 6:
                    DisplayMenu.displayExit();
                    exit = true;
                    break;
                default:
                    STDOUT.info(USER_MESSAGE);
                    Utilities.freezeConsole();
                    break;
            }
        } while (cont && (!exit));
    }

    public void manageNavigation() {
        boolean cont = true;
        do {
            DisplayMenu.displayManageMenu();
            switch (userInput.getUserNumericInput()) {
                case 1:
                    save();
                    break;
                case 2:
                    delete();
                    break;
                case 3:
                    update();
                    break;
                case 4:
                    STDOUT.info("ADD TO FAVOURITES");
                    break;
                case 5:
                    STDOUT.info("REMOVE FROM FAVOURITES");
                    break;
                case 6:
                    JsonWriter.writeJsonToFile(DrinksDatabase.getINSTANCE(), "AllDrinks.json");
                    cont = false;
                    break;
                case 7:
                    JsonWriter.writeJsonToFile(DrinksDatabase.getINSTANCE(), "AllDrinks.json");
                    DisplayMenu.displayExit();
                    exit = true;
                    break;
                default:
                    STDOUT.info(USER_MESSAGE);
                    Utilities.freezeConsole();
                    break;
            }
        } while (cont && (!exit));
    }

    private void update() {
        Utilities.clearScreen();
        boolean checkId = false;
        while (!checkId) {
            String drinkIdToEdit = userInput.getUserStringInput("Please type drink id to be edited: ");

            if (drinkService.editDrink(drinkIdToEdit)) {
                STDOUT.info("\nDrink update complete. Press any key to continue:\n");
                userInput.getUserInputAnyKey();
                checkId = true;
            } else {
                String input = userInput.getUserStringInput("\nDrink ID not found. Press [y] to try again: ");

                if (!input.equalsIgnoreCase("y")) {
                    STDOUT.info("Drink edit unsuccessful - drink not found. Press any key to continue:\n");
                    userInput.getUserInputAnyKey();
                    checkId = true;
                }
            }
        }
    }

    private void delete() {
        Utilities.clearScreen();
        boolean checkId = false;
        while (!checkId) {
            String drinkIdToDelete = userInput.getUserStringInput("Please type drink id to be removed: ");

            if (drinkService.removeDrink(drinkIdToDelete)) {
                STDOUT.info("\nDrink removal complete. Press any key to continue: \n");
                userInput.getUserInputAnyKey();
                checkId = true;
            } else {
                String input = userInput.getUserStringInput("\nDrink ID not found. Press [y] to try again: ");

                if (!input.equalsIgnoreCase("y")) {

                    STDOUT.info("Drink removal unsuccessful - drink not found. Press any key to continue:\n");
                    userInput.getUserInputAnyKey();
                    checkId = true;
                }
            }
        }
    }

    private void save() {
        drinkService.createDrink();
        STDOUT.info("\nDrink added to database. Press any key to continue: \n");
        userInput.getUserInputAnyKey();
    }

    public void settingsNavigation() {
        boolean cont = true;
        do {
            DisplayMenu.displaySettingsMenu();
            switch (userInput.getUserNumericInput()) {
                case 1:
                    STDOUT.info("LOAD/CHANGE CONFIGURATION");
                    break;
                case 2:
                    STDOUT.info("CHANGE DRINKS SORTING ORDER");
                    break;
                case 3:
                    STDOUT.info("SET RECIPE DATA MODIFICATION FORMAT");
                    break;
                case 4:
                    cont = false;
                    break;
                case 5:
                    DisplayMenu.displayExit();
                    exit = true;
                    break;
                default:
                    STDOUT.info(USER_MESSAGE);
                    Utilities.freezeConsole();
                    break;
            }
        } while (cont && (!exit));
    }
}
