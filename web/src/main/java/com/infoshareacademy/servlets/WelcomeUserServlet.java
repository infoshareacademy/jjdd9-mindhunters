package com.infoshareacademy.servlets;

import com.infoshareacademy.cdi.FileUploadProcessor;
import com.infoshareacademy.exception.JsonNotFound;
import com.infoshareacademy.freemarker.TemplateProvider;
import com.infoshareacademy.mapper.DrinkMapper;
import com.infoshareacademy.service.JsonService;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@MultipartConfig
@WebServlet("/welcome")
public class WelcomeUserServlet extends HttpServlet {

    private final static Logger logger = Logger.getLogger(WelcomeUserServlet.class.getName());

    @Inject
    DrinkMapper drinkMapper;

    @Inject
    private JsonService jsonService;

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
                logger.warning("Template not created");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {


        Part jsonPath = req.getPart("jsonFile");
        try {
            String pathToJsonFile = "/jsonFile/" + fileUploadProcessor.uploadJsonFile(jsonPath).getName();
            jsonService.save(pathToJsonFile);
        } catch (JsonNotFound jsonNotFound) {
            logger.severe(jsonNotFound.getMessage());
        }
//        drinkMapper.toEntity(jsonPath);

    }
}