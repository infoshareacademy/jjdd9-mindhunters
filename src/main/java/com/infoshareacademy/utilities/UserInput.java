package com.infoshareacademy.utilities;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

import static com.infoshareacademy.utilities.Utilities.clearScreen;

public class UserInput {

    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private static final String CHOICE_MESSAGE = "\nType your choice: ";
    private static final String CHOICE_MESSAGE_DRINK = "\nType your drink: ";
    private static final String WRONG_INPUT_MESSAGE = "Wrong input. ";
    private static final String INT_CHOICE_MESSAGE = "\nType your choice: ";
    private static final String PRESS_ANY_KEY = "\n\nPress ENTER to continue... ";

    Scanner scanner = new Scanner(System.in);

    public int getUserNumericInput() {
        STDOUT.info(INT_CHOICE_MESSAGE);
        String userChoice = scanner.nextLine();
        if (NumberUtils.isCreatable(userChoice)) {
            return Integer.parseInt(userChoice);
        }
        return 0;
    }

    public String getUserStringInput(String choiceMessage) {
        STDOUT.info(choiceMessage);
        return scanner.nextLine().trim();
    }

    public void getUserInputAnyKey() {
        scanner.nextLine();
    }

    public boolean getYesOrNo(String choiceMessage) {
        STDOUT.info(choiceMessage);
        String input = scanner.nextLine();
        while (true) {
            for (ChoiceYesNo choice : ChoiceYesNo.values()) {
                if (choice.toString().equalsIgnoreCase(input)) {
                    //Utilities.clearScreen();
                    return choice.getValue();
                }
            }
            input = getUserStringInput("Unrecognised input. Try again: ");
        }
    }
}
