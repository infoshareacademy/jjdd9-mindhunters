package com.infoshareacademy.service;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class MenuPathTest {

    @Test
    void testGetPath() {
        MenuPath testMenuPath = new MenuPath();
        StringBuilder testStringBuilder = new StringBuilder();
        testMenuPath.add("TestPath");
        testStringBuilder = testStringBuilder.append("/").append(testMenuPath);
        //when
        testMenuPath.getPath();
        //then
        Assertions.assertThat(MenuPath.getPath()).contains("TestPath");
    }

    @Test
    void testAdd() {
        MenuPath testMenuPath = new MenuPath();
        String testString = "TestPath";
        //when
        testMenuPath.add(testString);
        //then
        Assertions.assertThat(MenuPath.getPath().toString().contains("TestPath"));

    }

    @Test
    void testReset() {
        MenuPath menuPath = new MenuPath();
        String a = "cos";
        String b = "tez";
        //when
        menuPath.add(a);
        menuPath.add(b);
        menuPath.reset();
        //then
        Assertions.assertThat(menuPath.toString().contains("MENU"));

    }

    @Test
    void testRemove() {
        List<String> testMenuPath = new ArrayList<>();
        //when
        testMenuPath.add("cos");
        testMenuPath.add("tez");
        testMenuPath.remove(1);
        //then
        Assertions.assertThat(testMenuPath.size()==1);
        Assertions.assertThat(testMenuPath).contains("cos");
    }
}