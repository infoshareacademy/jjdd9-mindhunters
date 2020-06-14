package com.infoshareacademy.service;

import com.infoshareacademy.domain.DrinkJson;

import javax.enterprise.context.RequestScoped;
import java.io.IOException;
import java.util.List;


@RequestScoped
public class JsonService {

    public void save(String pathToJsonFile) {
        List<DrinkJson> drinksFromJson = filerToListOfDrinksJson(pathToJsonFile);
    }

    private List<DrinkJson> filerToListOfDrinksJson(String pathToJsonFile) {
        try {
            return JsonReader.jsonDrinkReader(pathToJsonFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return List.of();
    }
}
