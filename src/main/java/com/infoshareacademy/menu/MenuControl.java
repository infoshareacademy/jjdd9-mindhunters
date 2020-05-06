package com.infoshareacademy.menu;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.DrinksDatabase;
import com.infoshareacademy.service.DrinkService;
import com.infoshareacademy.service.FavouritesService;
import com.infoshareacademy.service.JsonWriter;
import com.infoshareacademy.utilities.PropertiesUtilities;
import com.infoshareacademy.utilities.UserInput;
import com.infoshareacademy.utilities.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

import static com.infoshareacademy.domain.DrinksDatabase.getINSTANCE;
import static com.infoshareacademy.domain.FavouritesDatabase.*;

public class MenuControl {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private static final String USER_MESSAGE = "Wrong input. Please choose number from the list.";

    private boolean exit = false;
    private final UserInput userInput = new UserInput();
    private final DrinkService drinkService = new DrinkService();
    private final FavouritesService favouritesService = new FavouritesService();

    public void mainNavigation() {
        drinkService.loadDrinkList();
        favouritesService.loadFavouritesList();
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
                    drinkService.printAllDrinks(getINSTANCE());
                    userInput.getUserInputAnyKey();
                    break;
                case 2:
                    STDOUT.info(" -------------SEARCH BY NAME------------------");
                    Drink drink = DrinksDatabase.getINSTANCE().getDrinks().get(0);
                    DrinkService.printSingleDrink(drink);
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
                    String temp = "17222";
                    final Set<String> favouritesIds = getInstFavourites().getFavouritesIds();

                    if (!favouritesIds.contains(temp)) {
                        favouritesIds.add(temp);
                    }
                    STDOUT.info("Drink added to favourites.");

                    break;
                case 5:
                    STDOUT.info("REMOVE FROM FAVOURITES");

                    String temp2 = "17222";
                    final Set<String> favourIds = getInstFavourites().getFavouritesIds();

                    if (favourIds.contains(temp2)) {
                        favourIds.remove(temp2);
                    }
                    STDOUT.info("Drink removed from favourites.");

                    break;
                case 6:
                    JsonWriter.writeAllToJson(getINSTANCE(), "AllDrinksTEST.json");
                    JsonWriter.writeAllToJson(getInstFavourites(), "Favourites.json");
                    cont = false;
                    break;
                case 7:
                    JsonWriter.writeAllToJson(getINSTANCE(), "AllDrinksTEST.json");
                    JsonWriter.writeAllToJson(getInstFavourites(), "Favourites.json");
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
            String drinkIdToEdit = userInput.getUserStringInput("Please type drink id to be edited: ");

            if (drinkService.editDrink(drinkIdToEdit)) {
                Utilities.clearScreen();
                STDOUT.info("Drink update complete. Press any key to continue: ");
                userInput.getUserInputAnyKey();
                checkId = true;
            } else {
                Utilities.clearScreen();
                String input = userInput.getUserStringInput("Drink ID not found. Press [Y] to try again: ");

                if (!userInput.getYesOrNo(input)) {
                    Utilities.clearScreen();
                    STDOUT.info("Drink edit unsuccessful - drink not found. Press any key to continue: ");
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
            String drinkIdToDelete = userInput.getUserStringInput("Please type drink id to be removed: ");

            if (drinkService.removeDrink(drinkIdToDelete)) {
                Utilities.clearScreen();
                STDOUT.info("Drink removal complete. Press any key to continue: ");
                userInput.getUserInputAnyKey();
                checkId = true;
            } else {
                Utilities.clearScreen();
                String input = userInput.getUserStringInput("Drink ID not found. Press [Y] try again: ");

                if (!userInput.getYesOrNo(input)) {
                    Utilities.clearScreen();
                    STDOUT.info("Drink removal unsuccessful - drink not found. Press any key to continue: ");
                    userInput.getUserInputAnyKey();
                    checkId = true;
                }
            }
        }
    }

    private void save() {
        drinkService.createDrink();
        Utilities.clearScreen();
        STDOUT.info("Drink added to database. Press any key to continue: ");
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
                    settingsOrderNavigation();
                    break;
                case 3:
                    settingsDateFormatNavigation();
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
