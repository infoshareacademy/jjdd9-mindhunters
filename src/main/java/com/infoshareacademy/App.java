package com.infoshareacademy;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.DrinksDatabase;
import com.infoshareacademy.menu.MenuControl;

public class App {

    public static void main(String[] args) {
        DrinksDatabase drinksDatabase = DrinksDatabase.getINSTANCE();
        MenuControl menuControl = new MenuControl();
        menuControl.mainNavigation();
    }
}

