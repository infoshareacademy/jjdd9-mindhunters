package com.infoshareacademy.utilities;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class UserInput {

    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private static final String CHOICE_MESSAGE = "\nType your choice: ";
    private static final String WRONG_INPUT_MESSAGE = "Wrong input. ";
    private static final String PRESS_ANY_KEY = "\n\nPress ENTER to continue... ";

    Scanner scanner = new Scanner(System.in);

    public int getUserInput() {
        STDOUT.info(CHOICE_MESSAGE);
        String userChoice = scanner.nextLine();
        if (NumberUtils.isCreatable(userChoice)) {
            return Integer.parseInt(userChoice);
        }
        return 0;
    }

    public void getUserInputAnyKey() {
        STDOUT.info(PRESS_ANY_KEY);
        scanner.nextLine();
    }
}
