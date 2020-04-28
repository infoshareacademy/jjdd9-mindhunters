package com.infoshareacademy;

import com.infoshareacademy.domain.DrinksDatabase;
import com.infoshareacademy.service.DrinkService;

public class AppTest {

    public static void main(String[] args) {
        DrinkService.loadDrinkList();
        DrinkService.printAllCategories(DrinksDatabase.getINSTANCE());
    }


}
