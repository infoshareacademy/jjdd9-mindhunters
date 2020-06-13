package com.infoshareacademy.servlets;

import com.infoshareacademy.cdi.JsonParserApiBean;
import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.DrinkJson;
import com.infoshareacademy.jsonSupport.CategoryJson;
import com.infoshareacademy.jsonSupport.JsonCategoryApiReader;
import com.infoshareacademy.mapper.DrinkMapper;
import com.infoshareacademy.service.DrinkService;
import com.infoshareacademy.service.JsonService;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/upload-json-api")
public class UploadDbFromApiServlet extends HttpServlet {

    private static final Logger packageLogger = LoggerFactory.getLogger(UploadDbFromApiServlet.class.getName());

    @Inject
    DrinkMapper drinkMapper;

    @Inject
    private JsonService jsonService;

    @Inject
    DrinkService drinkService;

    @Inject
    private JsonParserApiBean jsonParserApiBean;

//    @Inject
//    private JsonCategoryApiReader jsonCategoryApiReader;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<DrinkJson> drinkJsons = null;
        for (char alphabet = 'a'; alphabet <= 'd'; alphabet++) {
            Request fromAlphabet = Request.Get("https://www.thecocktaildb.com/api/json/v1/1/search.php?f=" + alphabet);
            String stringDrinkJson = fromAlphabet.execute().returnContent().asString();

            drinkJsons = new ArrayList<>();
            drinkJsons = jsonParserApiBean.jsonDrinkReaderFromString(stringDrinkJson);
        }


        Request getCat = Request.Get("https://www.thecocktaildb.com/api/json/v1/1/list.php?c=list");
        String stringCatDrinkJson = getCat.execute().returnContent().asString();
        List<CategoryJson> categoryJson = new ArrayList<>();
        categoryJson = JsonCategoryApiReader.jsonCategoryReader(stringCatDrinkJson);


        Drink drink = new Drink();
        for (DrinkJson drinkJson : drinkJsons) {
            drink = drinkMapper.toEntity(drinkJson, categoryJson.get(1));
            drinkService.save(drink);
        }
        resp.sendRedirect("/adminPage");

    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
//            throws IOException, ServletException {
//
//
//    }


}
