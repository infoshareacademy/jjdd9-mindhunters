package com.infoshareacademy.menu;

import com.infoshareacademy.domain.DrinksDatabase;
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
        do {
            DisplayMenu.displayMainMenu();
            switch (userInput.getUserInput()) {
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
        DrinkService.loadDrinkList();
        do {
            DisplayMenu.displayBrowseMenu();
            switch (userInput.getUserInput()) {
                case 1:
                    DrinkService.printAllDrinks(DrinksDatabase.getINSTANCE());
                    userInput.getUserInputAnyKey();
                    break;
                case 2:
                    STDOUT.info(" -------------SEARCH BY NAME------------------");
                    //String singleDrink = userInput.getUserInputString();
                    //DrinkService.printSingleDrink(DrinksDatabase.getINSTANCE(),singleDrink);
                    DrinkService.printSingleDrink(DrinksDatabase.getINSTANCE(),userInput.getUserInputString());
                    userInput.getUserInputAnyKey();
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
            switch (userInput.getUserInput()) {
                case 1:
                    STDOUT.info("ADD DRINK");
                    break;
                case 2:
                    STDOUT.info("DELETE DRINK");
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
            switch (userInput.getUserInput()) {
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
