package com.infoshareacademy.jsonSupport;

import java.io.IOException;
import java.util.List;

public class JsonCategoryService {

    public void save(String pathToJsonFile) {
        List<CategoryJson> categoryFromJson = filerToListOfCategoryJson(pathToJsonFile);
    }

    private List<CategoryJson> filerToListOfCategoryJson(String pathToJsonFile) {
        try {
            return JsonCategoryReader.jsonCategoryReader(pathToJsonFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return List.of();
    }
}
