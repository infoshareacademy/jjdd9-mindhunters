package com.infoshareacademy.servlets;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.DrinksDatabase;
import com.infoshareacademy.freemarker.TemplateProvider;
import com.infoshareacademy.service.DrinkService;
import com.infoshareacademy.service.SearchService;
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

@WebServlet("/drink")
public class TestSingleDrinkServlet extends HttpServlet {

    @Inject
    private TemplateProvider templateProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //ladujemy baze app json
        DrinkService.loadDrinkList();

        //wyciagamy liste drinkow
        final List<Drink> drinkList = DrinksDatabase.getINSTANCE().getDrinks();
        Drink testDrink = drinkList.get(5);


        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("drink", testDrink);


/*//        req.getParameter("name");

        if (name == null || name.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        dataModel.put("name", name);*/
        Template template = templateProvider.getTemplate(getServletContext(),"singleDrinkView.ftlh");

        PrintWriter printWriter = resp.getWriter();

        try {
            template.process(dataModel, printWriter);
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }
}
