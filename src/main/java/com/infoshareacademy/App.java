package com.infoshareacademy;

import com.infoshareacademy.menu.DisplayMenu;
import com.infoshareacademy.menu.MenuControl;
import com.infoshareacademy.utilities.PropertiesUtilities;

import java.io.*;
import java.util.Properties;
import com.infoshareacademy.utilities.Utilities;

public class App {


        /*(new PropertiesUtilities()).setProperties("orderby", "desc");
        String orderby = (new PropertiesUtilities()).getProperty("orderby");

        PropertiesUtilities propertiesUtilities = new PropertiesUtilities();
        propertiesUtilities.setProperties("orderby", "DESC");
        System.out.println(propertiesUtilities.getProperty("orderby"));*/

    public static void main(String[] args) {
        DisplayMenu.logo();
        Utilities.freezeConsole();
        MenuControl menuControl = new MenuControl();
        menuControl.mainNavigation();
    }
}

