package com.infoshareacademy.menu;

import java.util.concurrent.TimeUnit;

public class Utilities {

    public static void freezeConsole(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
