package com.infoshareacademy.service;

import java.util.ArrayList;
import java.util.List;

public class MenuPath {
    private static List<String> menuPath = new ArrayList<>();

    public static StringBuilder getPath() {
        StringBuilder pathBuilder = new StringBuilder();
        for (String menuPosition : menuPath) {
            if (menuPosition.equals(menuPath.get(menuPath.size() - 1))) {
                pathBuilder = pathBuilder.append("/").append(Colours.ANSI_BACKGROUND_REVERSED
                        + menuPosition + Colours.ANSI_RESET);
                return pathBuilder;
            }
            pathBuilder = pathBuilder.append("/").append(menuPosition);
        }
        return pathBuilder;
    }

    public static void add(String step) {
        menuPath.add(step);
    }

    public static void reset() {
        menuPath.clear();
        menuPath.add("MENU");
    }

    public static void remove() {
        menuPath.remove(menuPath.get(menuPath.size() - 1));
    }


}
