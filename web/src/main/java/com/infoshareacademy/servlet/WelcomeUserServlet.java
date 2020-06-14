package com.infoshareacademy.servlet;

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
import java.util.logging.Logger;


@WebServlet("")
public class WelcomeUserServlet extends HttpServlet {

    private final static Logger logger = Logger.getLogger(WelcomeUserServlet.class.getName());


    @Inject
    private TemplateProvider templateProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, Object> dataModel = new HashMap<>();
        String name = req.getParameter("name");

        if (name == null || name.isEmpty()) {
            name = "Stranger";
        }
        dataModel.put("name", name.toUpperCase());
        Template template = templateProvider.getTemplate(getServletContext(), "welcomePage.ftlh");

        PrintWriter printWriter = resp.getWriter();

        try {
            template.process(dataModel, printWriter);
        } catch (TemplateException e) {
            logger.warning("Template not created");
        }

    }
}