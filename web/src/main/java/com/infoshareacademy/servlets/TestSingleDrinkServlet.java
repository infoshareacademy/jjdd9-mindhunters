package com.infoshareacademy.servlets;

import com.infoshareacademy.freemarker.TemplateProvider;
import com.infoshareacademy.testClasses.Drink;
import com.infoshareacademy.testClasses.DrinkService;
import com.infoshareacademy.testClasses.DrinksDatabase;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@WebServlet("/drink")
public class TestSingleDrinkServlet extends HttpServlet {

    @Inject
    private TemplateProvider templateProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        //ladujemy baze app json
        DrinkService.loadDrinkList();

        //wyciagamy liste drinkow
        final List<Drink> drinkList = DrinksDatabase.getINSTANCE().getDrinks();

        //losujemy drinka
        Integer randomNum = new Random().nextInt(101);
        Drink testDrink = drinkList.get(randomNum);

        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("drink", testDrink);

        Template template = templateProvider.getTemplate(getServletContext(), "singleDrinkView.ftlh");

        PrintWriter printWriter = resp.getWriter();

        try {
            template.process(dataModel, printWriter);
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }
}
