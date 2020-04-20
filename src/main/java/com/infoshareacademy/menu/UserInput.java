package com.infoshareacademy.menu;

import java.util.Scanner;

public class UserInput {

    Scanner scanner = new Scanner(System.in);

    public int getUserInput() {
        System.out.print("Type your choice: ");
        String userChoice = scanner.nextLine();

        Integer userChoiceParsed = -1; //value not available in any menu
        try {
            userChoiceParsed = Integer.parseInt(userChoice);
        } catch (NumberFormatException numberFormatException) {
            System.out.println("Wrong input format! Only numbers accepted. Try again. ");
            Utilities.freezeConsole(1);
        }
        return userChoiceParsed;
    }

}
