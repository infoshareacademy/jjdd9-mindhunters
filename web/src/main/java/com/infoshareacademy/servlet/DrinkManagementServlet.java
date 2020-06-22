package com.infoshareacademy.servlet;

import com.infoshareacademy.context.ContextHolder;
import com.infoshareacademy.domain.*;
import com.infoshareacademy.domain.dto.FullDrinkView;
import com.infoshareacademy.exception.JsonNotFound;
import com.infoshareacademy.freemarker.TemplateProvider;
import com.infoshareacademy.imageFileUpload.ImageUploadProcessor;
import com.infoshareacademy.service.CategoryService;
import com.infoshareacademy.service.DrinkService;
import com.infoshareacademy.service.IngredientService;
import com.infoshareacademy.service.MeasureService;
import com.infoshareacademy.service.validator.UserInputValidator;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/drink-management")
@MultipartConfig
public class DrinkManagementServlet extends HttpServlet {

    private static final Logger packageLogger = LoggerFactory.getLogger(DrinkManagementServlet.class.getName());

    @Inject
    private TemplateProvider templateProvider;

    @EJB
    private DrinkService drinkService;

    @EJB
    private CategoryService categoryService;

    @Inject
    private UserInputValidator userInputValidator;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if (action == null || action.isBlank()) {
            action = "";
        }

        String idParam = req.getParameter("id");
        Long drinkId = userInputValidator.stringToLongConverter(idParam);

        resp.setContentType("text/html; charset=UTF-8");

        Map<String, Object> dataModel = new HashMap<>();
        Template template = null;

        ContextHolder contextHolder = new ContextHolder(req.getSession());
        dataModel.put("name", contextHolder.getName());
        dataModel.put("role", contextHolder.getRole());

        FullDrinkView drinkView = drinkService.getFullDrinkViewById(drinkId);

        dataModel.put("drink", drinkView);
        dataModel.put("categories", categoryService.findAllCategories());
        template = templateProvider.getTemplate(getServletContext(), "editDrinkForm.ftlh");

        try {
            template.process(dataModel, resp.getWriter());
        } catch (
                TemplateException e) {
            packageLogger.error(e.getMessage());
        }
    }

}
