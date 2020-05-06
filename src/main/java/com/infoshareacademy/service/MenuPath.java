package com.infoshareacademy.service;

import java.util.ArrayList;
import java.util.List;

public class MenuPath {
    private static List<String> menuPath = new ArrayList<>();

    public static List<String> getPath() {
        return menuPath;
    }

    public static void add(String step) {
        menuPath.add(step);
    }

    public static void reset() {
        menuPath.clear();
        menuPath.add("MENU");
    }
    public static void remove() {
        menuPath.remove(menuPath.get(menuPath.size()-1));
    }



}
