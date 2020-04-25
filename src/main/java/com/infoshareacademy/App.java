package com.infoshareacademy;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.DrinksDatabase;
import com.infoshareacademy.menu.MenuControl;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        DrinksDatabase drinksDatabase = DrinksDatabase.getINSTANCE();
        //MenuControl menuControl = new MenuControl();
       // menuControl.mainNavigation();

        System.out.println(drinksDatabase.getDrinks().size());

    }
}

