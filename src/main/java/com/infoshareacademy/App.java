package com.infoshareacademy;

import com.infoshareacademy.menu.MenuControl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class App {

    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");


    public static void main(String[] args) throws IOException {
        MenuControl menuControl = new MenuControl();
        menuControl.mainNavigation();
    }
}

