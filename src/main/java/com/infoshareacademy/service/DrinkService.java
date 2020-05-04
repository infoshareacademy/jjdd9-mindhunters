package com.infoshareacademy.service;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.DrinksDatabase;
import com.infoshareacademy.domain.Ingredient;
import com.infoshareacademy.menu.DisplayMenu;
import com.infoshareacademy.utilities.UserInput;
import com.infoshareacademy.utilities.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

public class DrinkService {

    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private static final String USER_MESSAGE = "Wrong input. Please choose number from the list.";
    private final UserInput userInput = new UserInput();

    private int maxExistingId = 0;

    public static void loadDrinkList() {

        DrinksDatabase database = DrinksDatabase.getINSTANCE();

        if (database.getDrinks().isEmpty()) {
            List<Drink> drinks = new ArrayList<>();
            for (int i = 0; i <= 4; i++) {
                char letter = (char) (97 + i);
                String fileName = "LIST_" + letter + "LETTER.json";

                try {
                    drinks.addAll(JsonReader.objectMapper(fileName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            database.addAllDrinks(drinks);
        }
    }

    public static void printAllDrinks(DrinksDatabase database) {

        for (Drink drink : database.getDrinks()) {
            STDOUT.info("\n{}\n *ID: {}, *Category: {}, {};", drink.getDrinkName().toUpperCase(),
                    drink.getDrinkId(), drink.getCategoryName(), drink.getAlcoholStatus());
            STDOUT.info("\n");
        }
    }

    public static List<Integer> getAllDrinkIdNumbers(DrinksDatabase database) {
        List<Integer> idNumbers = new ArrayList<>();
        database.getDrinks().forEach(drink -> idNumbers.add(Integer.parseInt(drink.getDrinkId())));
        return idNumbers;
    }


    public static void printAllCategories(DrinksDatabase database) {
        List<String> categories = getAllCategories(database);
        int counter = 0;
        for (String category : categories) {
            counter++;
            STDOUT.info("[{}], {}\n", counter, category);
        }
    }

    public static List<String> getAllCategories(DrinksDatabase database) {
        TreeSet<String> categories = new TreeSet<>();
        database.getDrinks().forEach(drink -> categories.add(drink.getCategoryName()));
        return List.copyOf(categories);
    }

    public static void printAllAlcoholStatuses(DrinksDatabase database) {
        List<String> alcoholStatuses = getAlcoholStatuses(database);
        int counter = 0;
        for (String alcoholStatus : alcoholStatuses) {
            counter++;
            STDOUT.info("[{}], {}\n", counter, alcoholStatus);
        }
    }

    public static List<String> getAlcoholStatuses(DrinksDatabase database) {
        TreeSet<String> alcoholStatuses = new TreeSet<>();
        database.getDrinks().forEach(drink -> alcoholStatuses.add(drink.getAlcoholStatus()));
        return List.copyOf(alcoholStatuses);
    }

    public static void printDrinkIngrAndMeasures(Drink drink) {
        drink.getIngredients().forEach(i -> STDOUT.info("Ingredient: {}, measure: {}\n", i.getName(), i.getMeasure()));
        STDOUT.info("\n");
    }

    public boolean deleteDrink(String id) {
        DrinksDatabase database = DrinksDatabase.getINSTANCE();
        for (Drink drink : database.getDrinks()) {
            if (drink.getDrinkId().trim().equalsIgnoreCase(id)) {
                database.getDrinks().remove(drink);
                return true;
            }
        }
        return false;
    }

    public void saveDrink() {
        Drink userDrink = new Drink();
        userDrink.setDrinkId(generateUserDrinkId());

        DisplayMenu.clearScreen();
        userDrink.setDrinkName(userInput.getUserStringInput("Type name of drink: "));

        DisplayMenu.clearScreen();
        setUserDrinkCategory(userDrink);

        DisplayMenu.clearScreen();
        setUserDrinkAlcoholStatus(userDrink);

        DisplayMenu.clearScreen();
        userDrink.setRecipe(userInput.getUserStringInput("Type drink recipe: "));

        DisplayMenu.clearScreen();
        userDrink.setImageUrl(userInput.getUserStringInput("Type drink image URL: "));

        DisplayMenu.clearScreen();
        userDrink.setIngredients(setUserDrinkIngredientAndMeasure(15));
        userDrink.setModifiedDate(LocalDateTime.now());

        DrinksDatabase.getINSTANCE().addDrink(userDrink);
    }

    private String generateUserDrinkId() {
        if (maxExistingId == 0) {
            maxExistingId = Collections.max(DrinkService.getAllDrinkIdNumbers(DrinksDatabase.getINSTANCE()));
        }
        maxExistingId++;
        return String.valueOf(maxExistingId);
    }

    void setUserDrinkCategory(Drink userDrink) {
        STDOUT.info("Choose category number:\n");
        DrinkService.printAllCategories(DrinksDatabase.getINSTANCE());
        int userChoice = 0;
        do {
            userChoice = userInput.getUserNumericInput();
            if (userChoice > 0 && userChoice <= DrinkService.getAllCategories(DrinksDatabase.getINSTANCE()).size()) {
                break;
            }
            STDOUT.info("Wrong input.\n");
        } while (true);
        userDrink.setCategoryName(DrinkService.getAllCategories(DrinksDatabase.getINSTANCE()).get(userChoice - 1));
    }

    void setUserDrinkAlcoholStatus(Drink userDrink) {
        STDOUT.info("Choose alcohol status:\n");
        DrinkService.printAllAlcoholStatuses(DrinksDatabase.getINSTANCE());
        int userChoice = 0;
        do {
            userChoice = userInput.getUserNumericInput();
            if (userChoice > 0 && userChoice <= DrinkService.getAlcoholStatuses(DrinksDatabase.getINSTANCE()).size()) {
                break;
            }
            STDOUT.info("Wrong input.\n");
        } while (true);
        userDrink.setAlcoholStatus((DrinkService.getAlcoholStatuses(DrinksDatabase.getINSTANCE()).get(userChoice - 1)));
    }

    List<Ingredient> setUserDrinkIngredientAndMeasure(int maxCapacity) {
        List<Ingredient> ingredients = new ArrayList<>();
        String name;
        String measure;
        int counter = 1;
        do {
            name = userInput.getUserStringInput("Type ingredient no." + counter + " name: ");
            StringBuilder builder = new StringBuilder();
            String message =
                    builder.append("Type ").append("'").append(name).append("'").append(" measure: ").toString();
            measure = userInput.getUserStringInput(message);
            ingredients.add(new Ingredient(name, measure));
            STDOUT.info("\n");
            counter++;
        } while (userInput.getUserStringInput("If you want to add another ingredient [max 15] press [y]: ").equalsIgnoreCase(
                "y") && (ingredients.size() <= maxCapacity));
        return ingredients;
    }

    public boolean updateDrink(String drinkId) {
        for (Drink drink : DrinksDatabase.getINSTANCE().getDrinks()) {
            if (drink.getDrinkId().trim().equalsIgnoreCase(drinkId)) {
                editNavigation(drink);
                return true;
            }
        }
        return false;
    }

    private void editNavigation(Drink editedDrink) {
        boolean cont = true;
        do {
            DisplayMenu.displayEditMenu();
            switch (userInput.getUserNumericInput()) {
                case 1:
                    DisplayMenu.clearScreen();
                    STDOUT.info("Previous drink name: {}\n\n", editedDrink.getDrinkName());
                    editedDrink.setDrinkName(userInput.getUserStringInput("Type name of drink: "));
                    editedDrink.setModifiedDate(LocalDateTime.now());
                    break;
                case 2:
                    DisplayMenu.clearScreen();
                    STDOUT.info("Previous drink category: {}\n\n", editedDrink.getCategoryName());
                    setUserDrinkCategory(editedDrink);
                    editedDrink.setModifiedDate(LocalDateTime.now());
                    break;
                case 3:
                    DisplayMenu.clearScreen();
                    STDOUT.info("Previous drink alcohol status: {}\n\n", editedDrink.getAlcoholStatus());
                    setUserDrinkAlcoholStatus(editedDrink);
                    editedDrink.setModifiedDate(LocalDateTime.now());
                    break;
                case 4:
                    DisplayMenu.clearScreen();
                    STDOUT.info("Previous drink recipe: {}\n\n", editedDrink.getRecipe());
                    editedDrink.setRecipe(userInput.getUserStringInput("Type drink recipe: "));
                    editedDrink.setModifiedDate(LocalDateTime.now());
                    break;
                case 5:
                    DisplayMenu.clearScreen();
                    STDOUT.info("Previous drink image URL: {}\n\n", editedDrink.getImageUrl());
                    editedDrink.setImageUrl(userInput.getUserStringInput("Type drink image URL: "));
                    editedDrink.setModifiedDate(LocalDateTime.now());
                    break;
                case 6:
                    DisplayMenu.clearScreen();
                    DrinkService.printDrinkIngrAndMeasures(editedDrink);
                    editedDrink.setIngredients(setUserDrinkIngredientAndMeasure(15));
                    editedDrink.setModifiedDate(LocalDateTime.now());
                    break;
                case 7:
                    cont = false;
                    break;
                default:
                    STDOUT.info(USER_MESSAGE);
                    Utilities.freezeConsole();
                    break;
            }
        } while (cont);
    }
}



