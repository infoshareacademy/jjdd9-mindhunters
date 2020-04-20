package com.infoshareacademy.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class UserInput {

    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private static final String CHOICE_MESSAGE = "\nType your choice: ";

    Scanner scanner = new Scanner(System.in);

    public int getUserInput() {
        STDOUT.info(CHOICE_MESSAGE);
        String userChoice = scanner.nextLine();

        return Integer.parseInt(userChoice);
    }

}
