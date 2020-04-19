package com.infoshareacademy.menu;

public class DisplayMenu {

    public static void displayMainMenu() {
        System.out.println("--------------- Main Menu ---------------");
        System.out.println("ENTER [1] to browse drinks recipes \nENTER [2] to manage recipes \nENTER [3] to change " +
                "settings \nENTER [4] to EXIT ");
    }

    public static void displayBrowseMenu() {
        System.out.println("--------------- Drink Browse Menu ---------------");
        System.out.println("ENTER [1] to display all drinks list \nENTER [2] to search drink by name \nENTER [3] to search drink by ingredient \nENTER [4] to search drink by category \nENTER [5] to return to previous menu \nENTER [6] to EXIT ");
    }

    public static void displayManageMenu() {
        System.out.println("--------------- Drink Management Menu ---------------");
        System.out.println("ENTER [1] to add drink \nENTER [2] to delete drink \nENTER [3] to add drink to favourites \nENTER [4] to remove drink from favourites \nENTER [5] to return to previous menu \nENTER [6] to EXIT ");
    }

    public static void displaySettingsMenu() {
        System.out.println("--------------- Settings ---------------");
        System.out.println("ENTER [1] to load/change configuration \nENTER [2] to change drinks sorting order \nENTER [3] to set recipe data modification format \nENTER [4] to return to previous menu \nENTER [5] to EXIT ");
    }

    public static void displayReturnMenu() {
        System.out.println("--------------- RETURN MENU ---------------");
        System.out.println("ENTER [1] to return to previous menu \nENTER [2] to EXIT \nENTER [3] to stay in this menu");
    }

}
