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
    UserInput userInput = new UserInput();
    DrinkService drinkService = new DrinkService();

    public void mainNavigation() {
        DrinkService.loadDrinkList();
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
                    DrinkService.printAllDrinks(DrinksDatabase.getINSTANCE());
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
                    drinkService.saveDrink();
                    STDOUT.info("\nDrink added to database. Press any key to continue: \n");
                    userInput.getUserInputAnyKey();
                    break;
                case 2:
                    boolean idNotFound = true;
                    while (idNotFound) {
                        if (drinkService.deleteDrink(userInput.getUserStringInput("Please type drink id to be " +
                                "removed: " +
                                " "))) {
                            idNotFound = false;
                            STDOUT.info("\nDrink removal complete. Press any key to continue: \n");
                            userInput.getUserInputAnyKey();
                        } else if (!userInput.getUserStringInput("\nDrink ID not found. Press [y] to try again: ").equalsIgnoreCase("y")) {
                            idNotFound = false;
                            STDOUT.info("Drink removal unsuccessful - drink not found. Press any key to continue: " +
                                    "\n");
                            userInput.getUserInputAnyKey();
                        }
                    }
                    break;
                case 3:
                    boolean editIdNotFound = true;
                    while (editIdNotFound) {
                        if (drinkService.updateDrink(userInput.getUserStringInput("Please type drink id to be " +
                                "edited: " +
                                " "))) {
                            editIdNotFound = false;
                            STDOUT.info("\nDrink edit complete. Press any key to continue:\n");
                            userInput.getUserInputAnyKey();
                        } else if (!userInput.getUserStringInput("\nDrink ID not found. Press [y] to try again: ").equalsIgnoreCase("y")) {
                            editIdNotFound = false;
                            STDOUT.info("Drink edit unsuccessful - drink not found. Press any key to continue:\n");
                            userInput.getUserInputAnyKey();
                        }
                    }
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
