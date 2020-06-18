package com.infoshareacademy.servlet;

import com.infoshareacademy.context.ContextHolder;
import com.infoshareacademy.domain.*;
import com.infoshareacademy.domain.dto.FullDrinkView;
import com.infoshareacademy.domain.dto.IngredientView;
import com.infoshareacademy.exception.JsonNotFound;
import com.infoshareacademy.freemarker.TemplateProvider;
import com.infoshareacademy.imageFileUpload.ImageUploadProcessor;
import com.infoshareacademy.mapper.IngredientMapper;
import com.infoshareacademy.service.CategoryService;
import com.infoshareacademy.service.DrinkService;
import com.infoshareacademy.service.IngredientService;
import com.infoshareacademy.service.MeasureService;
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
import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/drink-management")
@MultipartConfig
public class DrinkManagementServlet extends HttpServlet {

    private static final Logger packageLogger = LoggerFactory.getLogger(DrinkManagementServlet.class.getName());

    @Inject
    ImageUploadProcessor imageUploadProcessor;

    @Inject
    private TemplateProvider templateProvider;

    @EJB
    private DrinkService drinkService;

    @EJB
    private CategoryService categoryService;

    @EJB
    private IngredientService ingredientService;

    @EJB
    private MeasureService measureService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");

        Map<String, Object> dataModel = new HashMap<>();

        dataModel.put("categories", categoryService.findAllCategories());

        ContextHolder contextHolder = new ContextHolder(req.getSession());
        dataModel.put("name", contextHolder.getName());
        dataModel.put("role", contextHolder.getRole());

        Template template = templateProvider.getTemplate(getServletContext(), "addDrinkForm.ftlh");

        try {
            template.process(dataModel, resp.getWriter());
        } catch (
                TemplateException e) {
            packageLogger.error(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Drink drink = new Drink();

        Category category = categoryService.getOrCreate(req.getParameter("category"));


        String[] measures = req.getParameterValues("measure");
        String[] ingredients = req.getParameterValues("ingredient");

        List<Measure> measureList = new ArrayList<>();
        List<Ingredient> ingredientList = new ArrayList<>();


        for (String measure : measures){
            measureList.add(measureService.getOrCreate(measure));
        }
        for (String ingredient : ingredients){
            ingredientList.add(ingredientService.getOrCreate(ingredient));
        }

        List<DrinkIngredient> drinkIngredientsList = new ArrayList<>();

        for (int i=0; i < measureList.size(); i++){
            DrinkIngredient drinkIngredient = new DrinkIngredient();

            drinkIngredient.setMeasure(measureList.get(i));
            drinkIngredient.setIngredient(ingredientList.get(i));
            drinkIngredient.setDrinkId(drink);

            drinkIngredientsList.add(drinkIngredient);
        }

        drink.setDrinkIngredient(drinkIngredientsList);
        drink.setDrinkName(req.getParameter("name"));
        drink.setRecipe(req.getParameter("recipe"));
        drink.setCategory(category);
        drink.setAlcoholStatus(req.getParameter("status"));
        drink.setDate(LocalDateTime.now());

        Part image = req.getPart("image");
        String imageUrl = "";
        try {
            imageUrl = "/pictures/" + imageUploadProcessor
                    .uploadImageFile(image).getName();
        } catch (JsonNotFound userImageNotFound) {
            packageLogger.warn(userImageNotFound.getMessage());
        }

        drink.setImage(imageUrl);


        drinkService.save(drink);

    }



}
