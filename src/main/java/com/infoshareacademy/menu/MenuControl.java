package com.infoshareacademy.menu;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.DrinksDatabase;
import com.infoshareacademy.service.DrinkService;
import com.infoshareacademy.service.JsonWriter;
import com.infoshareacademy.utilities.PropertiesUtilities;
import com.infoshareacademy.service.MenuPath;
import com.infoshareacademy.utilities.ChoiceYesNo;
import com.infoshareacademy.utilities.UserInput;
import com.infoshareacademy.utilities.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

import static com.infoshareacademy.domain.DrinksDatabase.getINSTANCE;

import java.util.ArrayList;
import java.util.List;

public class MenuControl {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private static final String USER_MESSAGE = "Wrong input. Please choose number from the list.";

    private boolean exit = false;
    private final UserInput userInput = new UserInput();
    private final DrinkService drinkService = new DrinkService();
    private final MenuPath menuPath = new MenuPath();

    public void mainNavigation() {
        MenuPath.reset();
        drinkService.loadDrinkList();
        do {
            DisplayMenu.displayMainMenu();
            switch (userInput.getUserNumericInput()) {
                case 1:
                    MenuPath.add("BROWSE");
                    browseNavigation();
                    break;
                case 2:
                    MenuPath.add("MANAGEMENT");
                    manageNavigation();
                    break;
                case 3:
                    MenuPath.add("SETTINGS");
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
                    MenuPath.add("ALL_DRINKS");
                    drinkService.printAllDrinks(getINSTANCE());
                    userInput.getUserInputAnyKey();
                    MenuPath.remove();
                    break;
                case 2:
                    MenuPath.add("SEARCH_BY_NAME");
                    //STDOUT.info(" -------------SEARCH BY NAME------------------");
                    Drink drink = DrinksDatabase.getINSTANCE().getDrinks().get(0);
                    DrinkService.printSingleDrink(drink);
                    userInput.getUserInputAnyKey();
                    MenuPath.remove();
                    break;
                case 3:
                    MenuPath.add("SEARCH_BY_INGREDIENT");
                    STDOUT.info("\n" + MenuPath.getPath());
                    MenuPath.remove();
                    break;
                case 4:
                    MenuPath.add("SEARCH_BY_CATEGORY");
                    STDOUT.info("\n" + MenuPath.getPath());
                    MenuPath.remove();
                    break;
                case 5:
                    MenuPath.remove();
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
                    MenuPath.add("SAVE");
                    save();
                    MenuPath.remove();
                    break;
                case 2:
                    MenuPath.add("DELETE");
                    delete();
                    MenuPath.remove();
                    break;
                case 3:
                    MenuPath.add("UPDATE");
                    update();
                    MenuPath.remove();
                    break;
                case 4:
                    MenuPath.add("ADD_FAVOURITE");
                    STDOUT.info("\n" + MenuPath.getPath()+"\n");
                    MenuPath.remove();
                    break;
                case 5:
                    MenuPath.add("REMOVE_FAVORITE");
                    STDOUT.info("\n" + MenuPath.getPath()+"\n");
                    MenuPath.remove();
                    break;
                case 6:
                    JsonWriter.writeJsonToFile(getINSTANCE(), "AllDrinks.json");
                    cont = false;
                    MenuPath.remove();
                    break;
                case 7:
                    MenuPath.remove();
                    JsonWriter.writeJsonToFile(getINSTANCE(), "AllDrinks.json");
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
        boolean checkId = false;
        while (!checkId) {
            Utilities.clearScreen();
            String drinkIdToEdit = userInput.getUserStringInput("\nPlease type drink id to be edited: ");

            if (drinkService.editDrink(drinkIdToEdit)) {
                Utilities.clearScreen();
                STDOUT.info("Drink update complete. Press any key to continue: ");
                userInput.getUserInputAnyKey();
                checkId = true;
            } else {
                Utilities.clearScreen();
                String input = userInput.getUserStringInput("\nDrink ID not found. Press [Y] to try again: ");

                if (!userInput.getYesOrNo(input)) {
                    Utilities.clearScreen();
                    STDOUT.info("\nDrink edit unsuccessful - drink not found. Press any key to continue: ");
                    userInput.getUserInputAnyKey();
                    checkId = true;
                }
            }
        }
    }

    private void delete() {
        boolean checkId = false;
        while (!checkId) {
            Utilities.clearScreen();
            String drinkIdToDelete = userInput.getUserStringInput("\nPlease type drink id to be removed: ");

            if (drinkService.removeDrink(drinkIdToDelete)) {
                Utilities.clearScreen();
                STDOUT.info("Drink removal complete. Press any key to continue: ");
                userInput.getUserInputAnyKey();
                checkId = true;
            } else {
                Utilities.clearScreen();
                String input = userInput.getUserStringInput("\nDrink ID not found. Press [Y] try again: ");

                if (!userInput.getYesOrNo(input)) {
                    Utilities.clearScreen();
                    STDOUT.info("\nDrink removal unsuccessful - drink not found. Press any key to continue: ");
                    userInput.getUserInputAnyKey();
                    checkId = true;
                }
            }
        }
    }

    private void save() {
        drinkService.createDrink();
        Utilities.clearScreen();
        STDOUT.info("\nDrink added to database. Press any key to continue: ");
        userInput.getUserInputAnyKey();

    }

    public void settingsNavigation() {
        boolean cont = true;
        do {
            DisplayMenu.displaySettingsMenu();
            switch (userInput.getUserNumericInput()) {
                case 1:
                    MenuPath.add("LOAD/CHANGE_CONFIG");
                    STDOUT.info("\n" + MenuPath.getPath()+"\n");
                    //STDOUT.info("LOAD/CHANGE CONFIGURATION");
                    MenuPath.remove();
                    break;
                case 2:
                    MenuPath.add("SORTING_ORDER");
                    settingsOrderNavigation();
                    break;
                case 3:
                    MenuPath.add("DATE_FORMAT");
                    settingsDateFormatNavigation();
                    break;
                case 4:
                    cont = false;
                    MenuPath.remove();
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

    public void settingsOrderNavigation() {
        boolean cont = true;
        do {
            DisplayMenu.displaySettingsOrderMenu();
            switch (userInput.getUserNumericInput()) {
                case 1:
                    (new PropertiesUtilities()).setProperties("orderby", "asc");
                    break;
                case 2:
                    (new PropertiesUtilities()).setProperties("orderby", "desc");
                    break;
                case 3:
                    cont = false;
                    MenuPath.remove();
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
        } while (cont && (!exit));
    }

    public void settingsDateFormatNavigation() {
        boolean cont = true;
        do {
            DisplayMenu.displaySettingsDateFormatMenu();
            switch (userInput.getUserNumericInput()) {
                case 1:
                    (new PropertiesUtilities()).setProperties("date.format", "YYYY-MM-dd HH:mm");
                    break;
                case 2:
                    (new PropertiesUtilities()).setProperties("date.format", "dd-MM-YYYY HH:mm");
                    break;
                case 3:
                    cont = false;
                    MenuPath.remove();
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
        } while (cont && (!exit));
    }
}
