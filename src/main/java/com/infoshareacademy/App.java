package com.infoshareacademy;

import com.infoshareacademy.menu.MenuControl;
import com.infoshareacademy.utilities.PropertiesUtilities;

import java.io.*;
import java.util.Properties;

public class App {

    public static void main(String[] args) throws IOException {
        /*(new PropertiesUtilities()).setProperties("orderby", "desc");
        String orderby = (new PropertiesUtilities()).getProperty("orderby");

        PropertiesUtilities propertiesUtilities = new PropertiesUtilities();
        propertiesUtilities.setProperties("orderby", "DESC");
        System.out.println(propertiesUtilities.getProperty("orderby"));*/

        MenuControl menuControl = new MenuControl();
        menuControl.mainNavigation();
    }
}

