package com.infoshareacademy.service;

import com.infoshareacademy.cdi.JsonParserApiBean;
import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.DrinkJson;
import com.infoshareacademy.jsonSupport.CategoryJson;
import com.infoshareacademy.jsonSupport.JsonCategoryApiReader;
import com.infoshareacademy.mapper.DrinkMapper;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Singleton
@Startup
public class DbInitLoaderFromApi {

    private static final Logger logger = LoggerFactory.getLogger(DbInitLoaderFromApi.class.getName());

    @Inject
    private DrinkMapper drinkMapper;

    @Inject
    private JsonService jsonService;

    @Inject
    private DrinkService drinkService;

    @Inject
    private JsonParserApiBean jsonParserApiBean;

    @PostConstruct
    public void loadApi() {

        List<DrinkJson> drinkJsons = new ArrayList<>();
        List<CategoryJson> categoryJson = new ArrayList<>();

        for (char alphabet = 'a'; alphabet <= 'z'; alphabet++) {
            Request fromAlphabet = Request.Get("https://www.thecocktaildb.com/api/json/v1/1/search.php?f=" + alphabet);
            String stringDrinkJson = null;
            try {
                stringDrinkJson = fromAlphabet.execute().returnContent().asString();
            } catch (IOException e) {
                e.printStackTrace();
            }

            List<DrinkJson> letterDrinkJsons = null;
            try {
                letterDrinkJsons = Optional.ofNullable(jsonParserApiBean.jsonDrinkReaderFromString(stringDrinkJson))
                        .orElse(Collections.emptyList());
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (DrinkJson letterDrinkJson : letterDrinkJsons) {
                drinkJsons.add(letterDrinkJson);
            }
        }

        Request getCat = Request.Get("https://www.thecocktaildb.com/api/json/v1/1/list.php?c=list");
        String stringCatDrinkJson = null;
        try {
            stringCatDrinkJson = getCat.execute().returnContent().asString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            categoryJson = JsonCategoryApiReader.jsonCategoryReader(stringCatDrinkJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Drink drink = new Drink();
        for (DrinkJson drinkJson : drinkJsons) {

            drink = drinkMapper.toEntity(drinkJson, categoryJson.get(1));
            drinkService.save(drink);
        }
    }
}
