package com.infoshareacademy.service;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.DrinksDatabase;
import com.infoshareacademy.domain.Ingredient;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.infoshareacademy.service.Colours.ANSI_RED;
import static com.infoshareacademy.service.Colours.ANSI_RESET;

public class DrinkService {

    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    private DrinkService() {
    }

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
            database.addDrinks(drinks);
        }
    }

    public static void printAllDrinks(DrinksDatabase database) {


        for (Drink drink : database.getDrinks()) {
            STDOUT.info("\n{}\n *ID: {}, *Category: {}, {};", drink.getDrinkName().toUpperCase(),
                    drink.getDrinkId(), drink.getCategoryName(), drink.getAlcoholStatus());
            STDOUT.info("\n");
        }
    }

    public static void printSingleDrink(Drink drink) {
        String alcoContColour;
        if (drink.getAlcoholStatus().equals("Alcoholic")) {
            alcoContColour = ANSI_RED;
        } else {
            alcoContColour = ANSI_RESET;
        }
        STDOUT.info("\n" + Colours.ANSI_BACKGROUND_YELLOW_BLACK +
                StringUtils.center(drink.getDrinkName().toUpperCase(), 46, "-")
                + Colours.ANSI_RESET);
        STDOUT.info("\n                              Drink Id :" + drink.getDrinkId() +
                "\n  Category : " + drink.getCategoryName() + alcoContColour + "(" +
                drink.getAlcoholStatus() + ")" + ANSI_RESET + "\n");

        STDOUT.info("\n" + Colours.ANSI_BRIGHT_YELLOW +
                StringUtils.rightPad("Recipe :", 46, "=")
                + Colours.ANSI_RESET);

        String wrapText = WordUtils.wrap(drink.getRecipe(), 46);
        STDOUT.info("\n{}", wrapText + "\n");

        STDOUT.info(Colours.ANSI_BRIGHT_YELLOW +
                StringUtils.rightPad("=", 46, "=")
                + Colours.ANSI_RESET + "\n");

        STDOUT.info("\n" + Colours.ANSI_BRIGHT_YELLOW +
                StringUtils.rightPad("Ingredients :", 46, "=")
                + Colours.ANSI_RESET);

        List<Ingredient> ingredients = drink.getIngredients();
        String emptySpaces = "                           ";
        for (int j = 0; j < ingredients.size(); j++) {
            String adjustedName = drink.getIngredients().get(j).getName() + emptySpaces;
            STDOUT.info("\n" + adjustedName.substring(0, 24) +
                    " : " + drink.getIngredients().get(j).getMeasure());
        }
        STDOUT.info("\n" + Colours.ANSI_BRIGHT_YELLOW +
                StringUtils.rightPad("=", 46, "=")
                + Colours.ANSI_RESET + "\n");

        STDOUT.info("\nPhoto link : " + "\n" + Colours.ANSI_BLUE + drink.getImageUrl() + ANSI_RESET +
                "\n" + "\n" + Colours.ANSI_BACKGROUND_YELLOW_BLACK + "          Last modification : " +
                drink.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) +
                Colours.ANSI_RESET);
    }
}




