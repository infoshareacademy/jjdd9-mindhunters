package com.infoshareacademy;

import com.infoshareacademy.domain.DrinksDatabase;
import com.infoshareacademy.service.DrinkCreator;
import com.infoshareacademy.service.DrinkEditor;
import com.infoshareacademy.service.DrinkRemover;
import com.infoshareacademy.service.DrinkService;

public class AppTest {

    public static void main(String[] args) {
        DrinkService.loadDrinkList();
        DrinkCreator creator = new DrinkCreator();
        DrinkEditor editor = new DrinkEditor();
        DrinkRemover remover = new DrinkRemover();
        creator.createUserDrink();
        editor.editDrinkFromDatabase("17222");
        System.out.println(DrinksDatabase.getINSTANCE().getDrinks().get(0));
        remover.removeDrinkFromDatabase("17222");
        System.out.println(DrinksDatabase.getINSTANCE().getDrinks().get(0));





    }
}
