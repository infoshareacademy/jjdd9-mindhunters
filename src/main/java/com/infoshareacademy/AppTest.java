package com.infoshareacademy;

import com.infoshareacademy.service.DrinkManagement;
import com.infoshareacademy.service.DrinkService;

public class AppTest {

    public static void main(String[] args) {
        DrinkService.loadDrinkList();
        DrinkManagement drinkManagement = new DrinkManagement();
        drinkManagement.createUserDrink();



    }
}
