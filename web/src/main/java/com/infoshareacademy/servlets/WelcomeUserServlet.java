package com.infoshareacademy.servlets;

import com.infoshareacademy.cdi.FileUploadProcessor;
import com.infoshareacademy.freemarker.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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


@WebServlet("/welcome")
public class WelcomeUserServlet extends HttpServlet {

    private static final Logger packageLogger = LoggerFactory.getLogger(LoggerServlet.class.getName());

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private FileUploadProcessor fileUploadProcessor;


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
                packageLogger.error("Template not created");
        }

    }
}