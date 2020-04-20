package com.infoshareacademy.menu;

import java.util.concurrent.TimeUnit;

public class Utilities {

    public static void freezeConsole(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
