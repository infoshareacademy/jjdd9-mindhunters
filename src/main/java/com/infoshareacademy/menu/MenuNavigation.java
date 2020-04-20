package com.infoshareacademy.menu;

public class MenuNavigation {

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
                System.out.println("Please choose number from the list.");
                Utilities.freezeConsole(1);
                mainNavigation();
        }
    }

    public void browseNavigation() {
        DisplayMenu.displayBrowseMenu();
        switch (userInput.getUserInput()) {
            case 1:
                returnNavigation();
                System.out.println("ALL DRINKS RECIPES - CALL OBJECTS");
                break;
            case 2:
                returnNavigation();
                System.out.println("SEARCH BY NAME - CALL OBJECTS");
                break;
            case 3:
                returnNavigation();
                System.out.println("SEARCH BY INGREDIENT - CALL OBJECTS");
                break;
            case 4:
                returnNavigation();
                System.out.println("SEARCH BY CATEGORY - CALL OBJECTS");
                break;
            case 5:
                mainNavigation();
                break;
            case 6:
                DisplayMenu.displayExit();
                System.exit(0);
            default:
                System.out.println("Please choose number from the list.");
                Utilities.freezeConsole(1);
                browseNavigation();
        }
    }

    public void manageNavigation() {
        DisplayMenu.displayManageMenu();
        switch (userInput.getUserInput()) {
            case 1:
                returnNavigation();
                System.out.println("ADD DRINK - CALL OBJECTS");
                break;
            case 2:
                returnNavigation();
                System.out.println("DELETE DRINK - CALL OBJECTS");
                break;
            case 3:
                returnNavigation();
                System.out.println("ADD TO FAVOURITES - CALL OBJECTS");
                break;
            case 4:
                returnNavigation();
                System.out.println("REMOVE FROM FAVOURITES - CALL OBJECTS");
                break;
            case 5:
                mainNavigation();
                break;
            case 6:
                DisplayMenu.displayExit();
                System.exit(0);
            default:
                System.out.println("Please choose number from the list.");
                Utilities.freezeConsole(1);
                manageNavigation();
        }
    }

    public void settingsNavigation() {
        DisplayMenu.displaySettingsMenu();
        switch (userInput.getUserInput()) {
            case 1:
                returnNavigation();
                System.out.println("LOAD/CHANGE CONFIGURATION - CALL OBJECTS");
                break;
            case 2:
                returnNavigation();
                System.out.println("CHANGE DRINKS SORTING ORDER - CALL OBJECTS");
                break;
            case 3:
                returnNavigation();
                System.out.println("SET RECIPE DATA MODIFICATION FORMAT - CALL OBJECTS");
                break;
            case 4:
                mainNavigation();
                break;
            case 5:
                DisplayMenu.displayExit();
                System.exit(0);
            default:
                System.out.println("Please choose number from the list.");
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
                System.out.println("Please choose number from the list.");
                Utilities.freezeConsole(1);
                returnNavigation();
        }
    }
}
