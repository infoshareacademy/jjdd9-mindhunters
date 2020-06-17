package com.infoshareacademy.servlets;

import com.infoshareacademy.context.ContextHolder;
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


@WebServlet("/admin/page")
public class AdminUploadServlet extends HttpServlet {

    private static final Logger packageLogger = LoggerFactory.getLogger(AdminUploadServlet.class.getName());

    @Inject
    private TemplateProvider templateProvider;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        Map<String, Object> dataModel = new HashMap<>();
        String name = req.getParameter("name");

        if (name == null || name.isEmpty()) {
            name = "Stranger";
        }
        dataModel.put("name", name.toUpperCase());

        ContextHolder contextHolder = new ContextHolder(req.getSession());
        dataModel.put("name", contextHolder.getName());
        dataModel.put("role", contextHolder.getRole());

        Template template = templateProvider.getTemplate(getServletContext(), "adminUploadPage.ftlh");

        PrintWriter printWriter = resp.getWriter();

        try {
            template.process(dataModel, printWriter);
        } catch (TemplateException e) {
                packageLogger.error("Template not created");
        }

    }
}