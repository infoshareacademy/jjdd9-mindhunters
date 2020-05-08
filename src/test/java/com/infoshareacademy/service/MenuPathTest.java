package com.infoshareacademy.service;



import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MenuPathTest {

    @Test
    void testGetPath() {
        List<String> testMenuPath = new ArrayList<>();
        StringBuilder testStringBuilder= new StringBuilder();
        testMenuPath.add("TestPath");
        testStringBuilder = testStringBuilder.append("/").append(testMenuPath);
        //when
       MenuPath.getPath();
        //then
        Assertions.assertThat(testStringBuilder).contains(testMenuPath);
    }

    @Test
    void testAdd() {
    }

    @Test
    void testReset() {
    }

    @Test
    void testRemove() {
    }
}