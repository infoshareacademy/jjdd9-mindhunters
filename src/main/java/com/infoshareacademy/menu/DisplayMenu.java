package com.infoshareacademy.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class DisplayMenu {
    private final static Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    public static void displayMainMenu() {
        clearConsole();
        STDOUT.info(" ------------------ Main Menu ----------------------");
        STDOUT.info("|  ENTER [1] to browse drinks recipes               |" +
                         "\n|  ENTER [2] to manage recipes                      |" +
                         "\n|  ENTER [3] to change settings                     |" +
                         "\n|  ENTER [4] to EXIT                                |");
        STDOUT.info(" ---------------------------------------------------");
    }

    public static void displayBrowseMenu() {
        clearConsole();
        STDOUT.info(" --------------- Drink Browse Menu -----------------");
        STDOUT.info("|  ENTER [1] to display all drinks list             |" +
                         "\n|  ENTER [2] to search drink by name                |" +
                         "\n|  ENTER [3] to search drink by ingredient          |" +
                         "\n|  ENTER [4] to search drink by category            |" +
                         "\n|  ENTER [5] to return to previous menu             |" +
                         "\n|  ENTER [6] to EXIT                                |");
        STDOUT.info(" ---------------------------------------------------");

    }

    public static void displayManageMenu() {
        clearConsole();
        STDOUT.info(" ------------- Drink Management Menu ---------------");
        STDOUT.info("|  ENTER [1] to add drink                           |" +
                         "\n|  ENTER [2] to delete drink                        |" +
                         "\n|  ENTER [3] to add drink to favourites             |" +
                         "\n|  ENTER [4] to remove drink from favourites        |" +
                         "\n|  ENTER [5] to return to previous menu             |" +
                         "\n|  ENTER [6] to EXIT                                |");
        STDOUT.info(" ---------------------------------------------------");
    }

    public static void displaySettingsMenu() {
        clearConsole();
        STDOUT.info(" ------------------- Settings ----------------------");
        STDOUT.info("|  ENTER [1] to load/change configuration           |" +
                         "\n|  ENTER [2] to change drinks sorting order         |" +
                         "\n|  ENTER [3] to set recipe data modification format |" +
                         "\n|  ENTER [4] to return to previous menu             |" +
                         "\n|  ENTER [5] to EXIT                                |");
        STDOUT.info(" ---------------------------------------------------");
    }

    public static void displayReturnMenu() {
        clearConsole();
        STDOUT.info(" ----------------- RETURN MENU ---------------------");
        STDOUT.info("|  ENTER [1] to return to previous menu             |" +
                         "\n|  ENTER [2] to EXIT                                |" +
                         "\n|  ENTER [3] to stay in this menu                   |");
        STDOUT.info(" ---------------------------------------------------");
    }

    public static void displayExit() {
        clearConsole();
        STDOUT.info(" -------------- SEE YOU NEXT TIME ------------------ ");
    }

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            STDOUT.info("Can not clear the console!");
        }
    }

}
