package com.infoshareacademy;

import com.infoshareacademy.domain.DrinksDatabase;
import com.infoshareacademy.menu.MenuControl;
import com.infoshareacademy.service.DrinkCreator;
import com.infoshareacademy.service.DrinkEditor;
import com.infoshareacademy.service.DrinkRemover;
import com.infoshareacademy.service.DrinkService;

public class App {

    public static void main(String[] args) {
        DrinkService.loadDrinkList();
        MenuControl menuControl = new MenuControl();
        menuControl.mainNavigation();

        //tests
        //DrinkCreator creator = new DrinkCreator();
        //DrinkEditor editor = new DrinkEditor();
        //DrinkRemover remover = new DrinkRemover();
        //creator.createUserDrink();
        //editor.editDrinkFromDatabase("17222");
        //System.out.println(DrinksDatabase.getINSTANCE().getDrinks().get(0));
        //System.out.println(remover.removeDrinkFromDatabase("17222"));
        //System.out.println(DrinksDatabase.getINSTANCE().getDrinks().get(0));


    }
}

