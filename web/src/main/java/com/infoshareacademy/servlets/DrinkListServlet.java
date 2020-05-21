package com.infoshareacademy.servlets;


import com.infoshareacademy.cdi.DrinksDatabaseBean;
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
import java.util.Map;

@WebServlet("/drinks-list")
public class DrinkListServlet extends HttpServlet {

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private DrinksDatabaseBean drinksDatabaseBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, Object> dataModel = new HashMap<>();

        String drink = req.getParameter("drink");

        dataModel.put("drink", drink);

        Template template = templateProvider.getTemplate(getServletContext(), "receipeList.ftlh");

        PrintWriter printWriter = resp.getWriter();

        try {
            template.process(dataModel, printWriter);
        } catch (TemplateException e) {
            e.printStackTrace();
        }



    }
}
