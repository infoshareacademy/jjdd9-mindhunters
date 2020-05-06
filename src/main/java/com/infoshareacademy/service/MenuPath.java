package com.infoshareacademy.service;

import java.util.ArrayList;
import java.util.List;

public class MenuPath {
    private static List<String> menuPath = new ArrayList<>();

    public static List<String> getPath() {
        for (int i = 0; i < menuPath.size(); i++) {

        }

        return menuPath;
    }

    public static void add(String step) {
        menuPath.add(step);
    }

    public static void reset() {
        menuPath.clear();
        menuPath.add("MENU");
    }


}
