package com.infoshareacademy.servlets;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.freemarker.TemplateProvider;
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
public class DrinkListServlet extends HttpServlet {
    @Inject
    private TemplateProvider templateProvider;


    @Inject
    private DrinkServiceBEAN drinkServiceBEAN;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Drink> testDrink = drinkServiceBEAN.;



        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("drink", testDrink);
/*//        req.getParameter("name");
        if (name == null || name.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        dataModel.put("name", name);*/
        Template template = templateProvider.getTemplate(getServletContext(), "template.ftlh");
        PrintWriter printWriter = resp.getWriter();
        try {
            template.process(dataModel, printWriter);
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
