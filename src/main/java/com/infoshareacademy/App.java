package com.infoshareacademy;

import com.infoshareacademy.menu.DisplayMenu;
import com.infoshareacademy.menu.MenuControl;
import com.infoshareacademy.utilities.Utilities;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        DisplayMenu.logo();
        Utilities.freezeConsole();
        MenuControl menuControl = new MenuControl();
        menuControl.mainNavigation();
    }
}

