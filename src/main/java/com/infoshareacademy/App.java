package com.infoshareacademy;

import com.infoshareacademy.menu.MenuControl;
import com.infoshareacademy.service.DrinkManagement;
import com.infoshareacademy.service.DrinkService;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
/*        MenuControl menuControl = new MenuControl();
        menuControl.mainNavigation();*/

        DrinkService.loadDrinkList();
        DrinkManagement drinkManagement = new DrinkManagement();
        drinkManagement.createUserDrink();
    }
}

