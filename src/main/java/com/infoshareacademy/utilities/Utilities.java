package com.infoshareacademy.utilities;

import java.util.concurrent.TimeUnit;

public class Utilities {

    public static void freezeConsole() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
