package com.infoshareacademy.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DisplayMenu {

    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private static final String END_LINE = "\n --------------------------------------------------- ";

    private DisplayMenu() {
    }

    public static void displayMainMenu() {
        clearScreen();
        STDOUT.info("\n ------------------ Main Menu ---------------------- ");
        STDOUT.info("\n|  ENTER [1] to browse drinks recipes               |" +
                "\n|  ENTER [2] to manage recipes                      |" +
                "\n|  ENTER [3] to change settings                     |" +
                "\n|  ENTER [4] to EXIT                                |");
        STDOUT.info(END_LINE);
    }

    public static void displayBrowseMenu() {
        clearScreen();
        STDOUT.info("\n --------------- Drink Browse Menu ----------------- ");
        STDOUT.info("\n|  ENTER [1] to display all drinks list             |" +
                "\n|  ENTER [2] to search drink by name                |" +
                "\n|  ENTER [3] to search drink by ingredient          |" +
                "\n|  ENTER [4] to search drink by category            |" +
                "\n|  ENTER [5] to return to previous menu             |" +
                "\n|  ENTER [6] to EXIT                                |");
        STDOUT.info(END_LINE);
    }

    public static void displayManageMenu() {
        clearScreen();
        STDOUT.info("\n ------------- Drink Management Menu --------------- ");
        STDOUT.info("\n|  ENTER [1] to add drink                           |" +
                "\n|  ENTER [2] to delete drink                        |" +
                "\n|  ENTER [3] to add drink to favourites             |" +
                "\n|  ENTER [4] to remove drink from favourites        |" +
                "\n|  ENTER [5] to return to previous menu             |" +
                "\n|  ENTER [6] to EXIT                                |");
        STDOUT.info(END_LINE);
    }

    public static void displaySettingsMenu() {
        clearScreen();
        STDOUT.info("\n ------------------- Settings ---------------------- ");
        STDOUT.info("\n|  ENTER [1] to load/change configuration           |" +
                "\n|  ENTER [2] to change drinks sorting order         |" +
                "\n|  ENTER [3] to set recipe data modification format |" +
                "\n|  ENTER [4] to return to previous menu             |" +
                "\n|  ENTER [5] to EXIT                                |");
        STDOUT.info(END_LINE);
    }

    public static void displayExit() {
        clearScreen();
        STDOUT.info("\n --------------------- QUIT ------------------------ ");
        STDOUT.info("\n|  Hope to see you again soon.                      |" +
                "\n|                                                   |" +
                "\n|  Enjoy responsibly!                               |");
        STDOUT.info(END_LINE);
        STDOUT.info("\n");
    }

    private static void clearScreen() {
        STDOUT.info("\033[H\033[2J");
    }
}