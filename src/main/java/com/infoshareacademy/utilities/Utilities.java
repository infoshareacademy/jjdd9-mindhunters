package com.infoshareacademy.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class Utilities {

    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    public static void freezeConsole() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void clearScreen() {
        STDOUT.info("\033[H\033[2J");
    }
}
