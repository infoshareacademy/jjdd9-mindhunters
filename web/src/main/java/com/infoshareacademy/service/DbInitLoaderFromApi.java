package com.infoshareacademy.service;

import com.infoshareacademy.cdi.JsonParserApiBean;
import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.DrinkJson;
import com.infoshareacademy.jsonSupport.CategoryJson;
import com.infoshareacademy.jsonSupport.JsonCategoryApiReader;
import com.infoshareacademy.mapper.UploadDrinkMapper;
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

    private static final Logger packageLogger = LoggerFactory.getLogger(DbInitLoaderFromApi.class.getName());

    private final String API_URL_DRINKS = "http://isa-proxy.blueazurit.com/cocktails/1/search.php?f=";

    private final String API_URL_CATEGORIES = "http://isa-proxy.blueazurit.com/cocktails/1/list.php?c=list";

    @Inject
    private UploadDrinkMapper uploadDrinkMapper;

    @Inject
    private DrinkService drinkService;

    @Inject
    private JsonParserApiBean jsonParserApiBean;

    @PostConstruct
    public void loadApi() {



        List<DrinkJson> drinkJsons = new ArrayList<>();
        List<CategoryJson> categoryJson = new ArrayList<>();

        for (char alphabet = 'a'; alphabet <= 'b'; alphabet++) { //todo: zmienic litery
            Request fromAlphabet = Request.Get(API_URL_DRINKS + alphabet);
            String stringDrinkJson = null;
            try {
                stringDrinkJson = fromAlphabet.execute().returnContent().asString();
            } catch (IOException e) {
                packageLogger.error("DrinkJson not found");
            }

            List<DrinkJson> letterDrinkJsons = null;
            try {
                letterDrinkJsons = Optional.ofNullable(jsonParserApiBean.jsonDrinkReaderFromString(stringDrinkJson))
                        .orElse(Collections.emptyList());
            } catch (IOException e) {
                packageLogger.error("letterDrinkJson not found");

            }
            for (DrinkJson letterDrinkJson : letterDrinkJsons) {
                drinkJsons.add(letterDrinkJson);
            }
        }

        Request getCat = Request.Get(API_URL_CATEGORIES);
        String stringCatDrinkJson = null;
        try {
            stringCatDrinkJson = getCat.execute().returnContent().asString();
        } catch (IOException e) {
            packageLogger.error("stringCatDrinkJson not found");
        }
        try {
            categoryJson = JsonCategoryApiReader.jsonCategoryReader(stringCatDrinkJson);
        } catch (IOException e) {
            packageLogger.error("categoryJson not found");
        }
        Drink drink = new Drink();
        for (DrinkJson drinkJson : drinkJsons) {

            drink = uploadDrinkMapper.toEntity(drinkJson, categoryJson.get(1));
            drinkService.save(drink);
        }
    }
}
