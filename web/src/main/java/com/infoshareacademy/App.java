package com.infoshareacademy;

import com.infoshareacademy.jsonSupport.CategoryJson;
import com.infoshareacademy.jsonSupport.JsonCategoryReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {

        List<CategoryJson> categoryList = new ArrayList<>();
        String fileName = "LIST_CATEGORIES.json";
        try {
            categoryList.addAll(JsonCategoryReader.jsonCategoryReader(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        CategoryJson categoryJson = categoryList.get(1);

        System.out.println(categoryList.get(1));
        System.out.println(categoryJson.getCategoryName());

    }
}
