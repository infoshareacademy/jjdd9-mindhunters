package com.infoshareacademy.jsonSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class JsonCategoryService {

    private static final Logger packageLogger = LoggerFactory.getLogger(JsonCategoryService.class.getName());

    public void save(String pathToJsonFile) {
        List<CategoryJson> categoryFromJson = filerToListOfCategoryJson(pathToJsonFile);
    }

    private List<CategoryJson> filerToListOfCategoryJson(String pathToJsonFile) {
        try {
            return JsonCategoryReader.jsonCategoryReader(pathToJsonFile);
        } catch (IOException e) {
            packageLogger.error(e.getMessage(),"Json not created");
        }
        return List.of();
    }
}
