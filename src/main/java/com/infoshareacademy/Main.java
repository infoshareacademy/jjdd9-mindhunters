package com.infoshareacademy;

import java.util.Scanner;


public class Main {

    String currWords;
    Scanner scanner;

    Main() {
        currWords = "Please chose option";
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {

        try {
            Main main = new Main();
            main.run();

        } catch (Exception e) {
            System.out.println("Unexpected error");
        }
    }

    private void run() {
        System.out.println(" ");
        System.out.println("Welcome in drinks menu");
        //this.userChoice = "nothing";
        do {
            int mLvl = 1;
            boolean display = true;
            Menu1 menu1 = showMenu(display);
            {
                switch (menu1) {
                    case LISTOFRECIPIES:

                        Menu2 menu2 = showMenu2(true);
                    {
                        switch (menu2) {
                            case ADDORDELETE:
                                display = false;
                                System.out.println("Searching");
                                break;
                            case FAVORITES:

                                System.out.println("Categories");
                                break;
                            case DETAILS:

                                System.out.println("Add or delete");
                                break;
                            case LASTMODIFICATION:

                                System.out.println("Settings");
                                break;
                            case BACK:

                                break;
                            default: {
                                System.out.println("See you next time");
                                return;
                            }
                        }
                    }


                    break;
                    case SEARCHING:
                        System.out.println("Searching");
                        break;
                    case CATEGORIES:
                        System.out.println("Categories");
                        break;
                    case ADDORDELETE:
                        System.out.println("Add or delete");
                        break;
                    case SETTINGS:
                        System.out.println("Settings");
                        break;
                    default: {
                        System.out.println("See you next time");
                        return;
                    }

                }
            }

        } while (true);

    }

    String getString(String choiceLine) {
        System.out.println(" ");
        System.out.print(choiceLine + " : ");
        return this.scanner.nextLine();
    }

    Menu1 showMenu(boolean displayM) {
        System.out.println("How can I help You today?");
        System.out.println(" ");

        if (displayM) {
            int choice;
            for (int indmenu = 0; indmenu < Menu1.values().length; indmenu++) {
                System.out.println(indmenu + 1 + "-" + Menu1.values()[indmenu]);
            }
            do {
                String value = getString("Whats your choice");
                choice = (Integer.parseInt(value));
                if ((choice - 1 >= 0) && (choice - 1 < Menu1.values().length))
                    return Menu1.values()[choice - 1];
                if ((choice > Menu1.values().length) || (choice - 1 < Menu1.values().length)) {
                    getString("Wrong, choose one more time");

                }
            } while (true);

        } else {
            String value = getString("Can not create");
            int choice = (Integer.parseInt(value));
            return Menu1.values()[1];
        }

    }

    Menu2 showMenu2(boolean displayM2) {
        System.out.println("How can I help You now?");
        System.out.println(" ");
        int choice;
        if (displayM2) {

            for (int indmenu = 0; indmenu < Menu2.values().length; indmenu++) {
                System.out.println(indmenu + 1 + "-" + Menu2.values()[indmenu]);
            }
            do {
                String value = getString("Whats your choice");
                choice = (Integer.parseInt(value) - 1);
                if ((choice >= 0) && (choice < Menu2.values().length))
                    return Menu2.values()[choice];

            } while (true);
        } else do {
            String value = getString("Whats your choice");
            choice = (Integer.parseInt(value) - 1);
            if ((choice >= 0) && (choice < Menu2.values().length))
                return Menu2.values()[choice];
        } while (true);
    }


}