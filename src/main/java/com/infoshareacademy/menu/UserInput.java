package com.infoshareacademy.menu;

import java.util.Scanner;

public class UserInput {

    Scanner scanner = new Scanner(System.in);

    public int getUserInput(){
        System.out.print("Type your choice: ");
        String userChoice = scanner.nextLine();
        return Integer.parseInt(userChoice);
    }
}
