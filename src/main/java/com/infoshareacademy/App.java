package com.infoshareacademy;

import com.infoshareacademy.menu.DisplayMenu;
import com.infoshareacademy.menu.MenuControl;
import com.infoshareacademy.utilities.Utilities;

public class App {

    public static void main(String[] args) {
        DisplayMenu.logo();
        Utilities.freezeConsole();
        MenuControl menuControl = new MenuControl();
        menuControl.mainNavigation();
    }
}

