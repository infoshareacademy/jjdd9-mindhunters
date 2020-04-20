package com.infoshareacademy.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MenuControl {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private static final String userMessage = "Please choose number from the list.";

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
                    System.exit(0);
                default:
                    STDOUT.info(userMessage);
                    break;
            }
        } while (true);
    }

    public void browseNavigation() {
        boolean cont = true;
        do {
            DisplayMenu.displayBrowseMenu();
            switch (userInput.getUserInput()) {
                case 1:
                    STDOUT.info("ALL DRINKS RECIPES - CALL OBJECTS");
                    break;
                case 2:
                    STDOUT.info("SEARCH BY NAME - CALL OBJECTS");
                    break;
                case 3:
                    STDOUT.info("SEARCH BY INGREDIENT - CALL OBJECTS");
                    break;
                case 4:
                    STDOUT.info("SEARCH BY CATEGORY - CALL OBJECTS");
                    break;
                case 5:
                    cont = false;
                    break;
                case 6:
                    DisplayMenu.displayExit();
                    System.exit(0);
                default:
                    STDOUT.info(userMessage);
                    break;
            }
        } while (cont);
    }

    public void manageNavigation() {
        boolean cont = true;
        do {
            DisplayMenu.displayManageMenu();
            switch (userInput.getUserInput()) {
                case 1:
                    STDOUT.info("ADD DRINK - CALL OBJECTS");
                    break;
                case 2:
                    ;
                    STDOUT.info("DELETE DRINK - CALL OBJECTS");
                    break;
                case 3:
                    STDOUT.info("ADD TO FAVOURITES - CALL OBJECTS");
                    break;
                case 4:
                    STDOUT.info("REMOVE FROM FAVOURITES - CALL OBJECTS");
                    break;
                case 5:
                    cont = false;
                    break;
                case 6:
                    DisplayMenu.displayExit();
                    System.exit(0);
                default:
                    STDOUT.info(userMessage);
                    break;
            }
        } while (cont);
    }

    public void settingsNavigation() {
        boolean cont = true;
        do {
            DisplayMenu.displaySettingsMenu();
            switch (userInput.getUserInput()) {
                case 1:
                    STDOUT.info("LOAD/CHANGE CONFIGURATION - CALL OBJECTS");
                    break;
                case 2:
                    STDOUT.info("CHANGE DRINKS SORTING ORDER - CALL OBJECTS");
                    break;
                case 3:
                    STDOUT.info("SET RECIPE DATA MODIFICATION FORMAT - CALL OBJECTS");
                    break;
                case 4:
                    cont = false;
                    break;
                case 5:
                    DisplayMenu.displayExit();
                    System.exit(0);
                default:
                    STDOUT.info(userMessage);
                    break;
            }
        } while (cont);
    }
}
