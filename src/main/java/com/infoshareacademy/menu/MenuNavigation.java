package com.infoshareacademy.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MenuNavigation {
    private final static Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    UserInput userInput = new UserInput();
    int submenuChoice = 0;

    public void mainNavigation() {
        DisplayMenu.displayMainMenu();
        submenuChoice = userInput.getUserInput();
        switch (submenuChoice) {
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
                STDOUT.info("Please choose number from the list.");
                Utilities.freezeConsole(1);
                mainNavigation();
        }
    }

    public void browseNavigation() {
        DisplayMenu.displayBrowseMenu();
        switch (userInput.getUserInput()) {
            case 1:
                returnNavigation();
                STDOUT.info("ALL DRINKS RECIPES - CALL OBJECTS");
                break;
            case 2:
                returnNavigation();
                STDOUT.info("SEARCH BY NAME - CALL OBJECTS");
                break;
            case 3:
                returnNavigation();
                STDOUT.info("SEARCH BY INGREDIENT - CALL OBJECTS");
                break;
            case 4:
                returnNavigation();
                STDOUT.info("SEARCH BY CATEGORY - CALL OBJECTS");
                break;
            case 5:
                mainNavigation();
                break;
            case 6:
                DisplayMenu.displayExit();
                System.exit(0);
            default:
                STDOUT.info("Please choose number from the list.");
                Utilities.freezeConsole(1);
                browseNavigation();
        }
    }

    public void manageNavigation() {
        DisplayMenu.displayManageMenu();
        switch (userInput.getUserInput()) {
            case 1:
                returnNavigation();
                STDOUT.info("ADD DRINK - CALL OBJECTS");
                break;
            case 2:
                returnNavigation();
                STDOUT.info("DELETE DRINK - CALL OBJECTS");
                break;
            case 3:
                returnNavigation();
                STDOUT.info("ADD TO FAVOURITES - CALL OBJECTS");
                break;
            case 4:
                returnNavigation();
                STDOUT.info("REMOVE FROM FAVOURITES - CALL OBJECTS");
                break;
            case 5:
                mainNavigation();
                break;
            case 6:
                DisplayMenu.displayExit();
                System.exit(0);
            default:
                STDOUT.info("Please choose number from the list.");
                Utilities.freezeConsole(1);
                manageNavigation();
        }
    }

    public void settingsNavigation() {
        DisplayMenu.displaySettingsMenu();
        switch (userInput.getUserInput()) {
            case 1:
                returnNavigation();
                STDOUT.info("LOAD/CHANGE CONFIGURATION - CALL OBJECTS");
                break;
            case 2:
                returnNavigation();
                STDOUT.info("CHANGE DRINKS SORTING ORDER - CALL OBJECTS");
                break;
            case 3:
                returnNavigation();
                STDOUT.info("SET RECIPE DATA MODIFICATION FORMAT - CALL OBJECTS");
                break;
            case 4:
                mainNavigation();
                break;
            case 5:
                DisplayMenu.displayExit();
                System.exit(0);
            default:
                STDOUT.info("Please choose number from the list.");
                Utilities.freezeConsole(1);
                settingsNavigation();
        }
    }

    public void returnNavigation() {
        DisplayMenu.displayReturnMenu();
        switch (userInput.getUserInput()) {
            case 1:
                if (submenuChoice == 1) {
                    browseNavigation();
                } else if (submenuChoice == 2) {
                    manageNavigation();
                } else {
                    settingsNavigation();
                }
                break;
            case 2:
                DisplayMenu.displayExit();
                System.exit(0);
            case 3:
                break;
            default:
                STDOUT.info("Please choose number from the list.");
                Utilities.freezeConsole(1);
                returnNavigation();
        }
    }
}
