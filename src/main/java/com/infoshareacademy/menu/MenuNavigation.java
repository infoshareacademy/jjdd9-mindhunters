package com.infoshareacademy.menu;

public class MenuNavigation {

    UserInput userInput = new UserInput();
    int submenuChoice = 0;

    public void mainNavigation() {
        DisplayMenu.displayMainMenu();
        submenuChoice = userInput.getUserInput();
        switch (submenuChoice) {
            case 1:
                DisplayMenu.displayBrowseMenu();
                browseNavigation();
                break;
            case 2:
                DisplayMenu.displayManageMenu();
                manageNavigation();
                break;
            case 3:
                DisplayMenu.displaySettingsMenu();
                settingsNavigation();
                break;
            case 4:
                System.out.println("See you next time!");
                System.exit(0);
        }
    }

    public void browseNavigation() {

        switch (userInput.getUserInput()) {
            case 1:
                DisplayMenu.displayReturnMenu();
                returnNavigation();
                System.out.println("ALL DRINKS RECIPES - CALL OBJECTS");
                break;
            case 2:
                DisplayMenu.displayReturnMenu();
                returnNavigation();
                System.out.println("SEARCH BY NAME - CALL OBJECTS");
                break;
            case 3:
                DisplayMenu.displayReturnMenu();
                returnNavigation();
                System.out.println("SEARCH BY INGREDIENT - CALL OBJECTS");
                break;
            case 4:
                DisplayMenu.displayReturnMenu();
                returnNavigation();
                System.out.println("SEARCH BY CATEGORY - CALL OBJECTS");
                break;
            case 5:
                mainNavigation();
                break;
            case 6:
                System.out.println("See you next time!");
                System.exit(0);
        }
    }

    public void manageNavigation() {

        switch (userInput.getUserInput()) {
            case 1:
                DisplayMenu.displayReturnMenu();
                returnNavigation();
                System.out.println("ADD DRINK");
                break;
            case 2:
                DisplayMenu.displayReturnMenu();
                returnNavigation();
                System.out.println("DELETE DRINK - CALL OBJECTS");
                break;
            case 3:
                DisplayMenu.displayReturnMenu();
                returnNavigation();
                System.out.println("ADD TO FAVOURITES - CALL OBJECTS");
                break;
            case 4:
                DisplayMenu.displayReturnMenu();
                returnNavigation();
                System.out.println("REMOVE FROM FAVOURITES - CALL OBJECTS");
                break;
            case 5:
                mainNavigation();
                break;
            case 6:
                System.out.println("See you next time!");
                System.exit(0);
        }
    }

    public void settingsNavigation() {

        switch (userInput.getUserInput()) {
            case 1:
                DisplayMenu.displayReturnMenu();
                returnNavigation();
                System.out.println("LOAD/CHANGE CONFIGURATION - CALL OBJECTS");
                break;
            case 2:
                DisplayMenu.displayReturnMenu();
                returnNavigation();
                System.out.println("CHANGE DRINKS SORTING ORDER - CALL OBJECTS");
                break;
            case 3:
                DisplayMenu.displayReturnMenu();
                returnNavigation();
                System.out.println("SET RECIPE DATA MODIFICATION FORMAT - CALL OBJECTS");
                break;
            case 4:
                mainNavigation();
                break;
            case 5:
                System.out.println("See you next time!");
                System.exit(0);
        }
    }

    public void returnNavigation() {

        switch (userInput.getUserInput()) {
            case 1:
                if (submenuChoice == 1) {
                    DisplayMenu.displayBrowseMenu();
                    browseNavigation();
                } else if (submenuChoice == 2) {
                    DisplayMenu.displayManageMenu();
                    manageNavigation();
                } else {
                    DisplayMenu.displaySettingsMenu();
                    settingsNavigation();
                }
                break;
            case 2:
                System.out.println("See you next time!");
                System.exit(0);
            case 3:
                break;
        }
    }
}
