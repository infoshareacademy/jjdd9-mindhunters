package com.infoshareacademy.menu;

import com.infoshareacademy.domain.DrinksDatabase;
import com.infoshareacademy.service.DrinkCreator;
import com.infoshareacademy.service.DrinkRemover;
import com.infoshareacademy.service.DrinkService;
import com.infoshareacademy.utilities.UserInput;
import com.infoshareacademy.utilities.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MenuControl {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private static final String USER_MESSAGE = "Wrong input. Please choose number from the list.";

    private boolean exit = false;

    UserInput userInput = new UserInput();

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
                    DrinkCreator creator = new DrinkCreator();
                    creator.createUserDrink();
                    STDOUT.info("Drink added to database.\n");
                    break;
                case 2:
                    DrinkRemover remover = new DrinkRemover();
                    boolean idNotFound = true;
                    while (idNotFound) {
                        if (remover.removeDrinkFromDatabase(userInput.getUserStringInput("Please type drink id to be " +
                                "removed: " +
                                " "))) {
                            idNotFound = false;
                            STDOUT.info("Drink removal complete.\n");
                        } else if (userInput.getUserStringInput("Drink ID not found. Press [y] to try again: ").equalsIgnoreCase("y")) {
                            idNotFound = true;
                        } else {
                            idNotFound = false;
                            STDOUT.info("Drink removal unsuccessful - drink not found.\n");
                        }
                    }
                    break;
                case 3:
                    STDOUT.info("ADD TO FAVOURITES");
                    break;
                case 4:
                    STDOUT.info("REMOVE FROM FAVOURITES");
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
