package com.infoshareacademy.utilities;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class UserInput {

    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private static final String INT_CHOICE_MESSAGE = "\nType your choice: ";
    private static final String PRESS_ANY_KEY = "\n\nPress ENTER to continue... ";
    private static final String STRING_CHOICE_MESSAGE = "\nEnter your values: ";

    Scanner scanner = new Scanner(System.in);

    public int getUserNumericInput() {
        STDOUT.info(INT_CHOICE_MESSAGE);
        String userChoice = scanner.nextLine();
        if (NumberUtils.isCreatable(userChoice)) {
            return Integer.parseInt(userChoice);
        }
        return 0;
    }

    public String getUserStringInput() {
        STDOUT.info(STRING_CHOICE_MESSAGE);
        String userChoice = scanner.nextLine().trim();
        return userChoice;
    }

    public void getUserInputAnyKey() {
        scanner.nextLine();
    }
}
