package com.infoshareacademy.menu;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.FavouritesDatabase;
import com.infoshareacademy.service.DrinkService;
import com.infoshareacademy.service.FavouritesService;
import com.infoshareacademy.service.JsonWriter;
import com.infoshareacademy.service.SearchService;
import com.infoshareacademy.utilities.PropertiesUtilities;
import com.infoshareacademy.utilities.UserInput;
import com.infoshareacademy.utilities.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

import static com.infoshareacademy.domain.DrinksDatabase.getINSTANCE;
import static com.infoshareacademy.domain.FavouritesDatabase.getInstFavourites;

public class MenuControl {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private static final String USER_MESSAGE = "Wrong input. Please choose number from the list.";

    private boolean exit = false;
    private final UserInput userInput = new UserInput();
    private final DrinkService drinkService = new DrinkService();
    private final FavouritesService favouritesService = new FavouritesService();

    public void mainNavigation() {
        DrinkService.loadDrinkList();
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
        SearchService search = new SearchService();
        do {
            DisplayMenu.displayBrowseMenu();
            switch (userInput.getUserNumericInput()) {
                case 1:
                    drinkService.printAllDrinks(getINSTANCE());
                    userInput.getUserInputAnyKey();
                    break;
                case 2:
                    favouritesService.printAllFavourites(FavouritesDatabase.getInstFavourites());
                    userInput.getUserInputAnyKey();
                    break;
                case 3:
                    Drink foundDrinkByName = search.searchDrinkByName();
                    if (foundDrinkByName.getDrinkId() != null) {
                        DrinkService.printSingleDrink(foundDrinkByName);
                        if (userInput.getYesOrNo("Do you want to add this drink to favourites? <y/n>: ")){
                            addToFavourites(foundDrinkByName.getDrinkId());
                            userInput.getUserInputAnyKey();
                        }
                    }
                    break;
                case 4:
                    Drink foundDrinkByIngr = search.searchDrinkByIngredient();
                    if (foundDrinkByIngr.getDrinkId() != null) {
                        DrinkService.printSingleDrink(foundDrinkByIngr);
                        if (userInput.getYesOrNo("Do you want to add this drink to favourites? <y/n>: ")){
                            addToFavourites(foundDrinkByIngr.getDrinkId());
                            userInput.getUserInputAnyKey();
                        }
                    }
                    break;
                case 5:
                    STDOUT.info("SEARCH BY CATEGORY");
                    break;
                case 6:
                    JsonWriter.writeAllToJson(getInstFavourites(), "Favourites.json");
                    cont = false;
                    break;
                case 7:
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
                    //
                    String id = userInput.getUserStringInput("Type drink id to add to favourites: ");

                    addToFavourites(id);
                    userInput.getUserInputAnyKey();
                    break;
                case 5:
                    //

                    String id2 = userInput.getUserStringInput("Type drink id to remove from favourites: ");
                    removeFromFavourites(id2);
                    userInput.getUserInputAnyKey();
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

    private void removeFromFavourites(String id) {
        final Set<String> favourIds = getInstFavourites().getFavouritesIds();
        if (favourIds.contains(id)) {
            favourIds.remove(id);
        }
        STDOUT.info("Drink removed from favourites.");
    }

    private void addToFavourites(String id) {
        final Set<String> favouritesIds = getInstFavourites().getFavouritesIds();
        favouritesIds.add(id);
        STDOUT.info("Drink added to favourites.");
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
                STDOUT.info("Drink ID not found. ");
                if (!userInput.getYesOrNo("Do you want to try again? <y/n>")) {
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
                STDOUT.info("Drink ID not found. ");
                if (!userInput.getYesOrNo("Do you want to try again? <y/n>")) {
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
