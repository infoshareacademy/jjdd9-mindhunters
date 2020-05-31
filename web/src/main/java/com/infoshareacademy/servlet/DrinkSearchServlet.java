package com.infoshareacademy.servlet;

import com.infoshareacademy.domain.dto.FullDrinkView;
import com.infoshareacademy.domain.dto.IngredientView;
import com.infoshareacademy.freemarker.TemplateProvider;
import com.infoshareacademy.service.DrinkService;
import com.infoshareacademy.service.IngredientService;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/search")
public class DrinkSearchServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(DrinkSearchServlet.class.getName());

    @Inject
    private DrinkService drinkService;

    @Inject
    private IngredientService ingredientService;

    @Inject
    private TemplateProvider templateProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final int currentPage = Integer.parseInt(req.getParameter("page"));

        final int maxPage = drinkService.maxPageNumber();

        final List<FullDrinkView> drinkList = drinkService.findAllDrinks();

        Map<String, Object> dataModel = new HashMap<>();

        dataModel.put("maxPageSize", maxPage);


        String page = req.getParameter("page");
        if (page != null && !page.isEmpty()) {
            final List<FullDrinkView> paginatedDrinkList = drinkService.paginationDrinkList(Integer.parseInt(req.getParameter("page")));
            dataModel.put("drinkList", paginatedDrinkList);
            dataModel.put("currentPage", currentPage);

        } else {

            dataModel.put("drinkList", drinkList);
        }

        Template template = templateProvider.getTemplate(getServletContext(), "recipeSearchList.ftlh");

        try {
            template.process(dataModel, resp.getWriter());
        } catch (TemplateException e) {
            logger.error(e.getMessage());
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        Template template = templateProvider.getTemplate(getServletContext(), "recipeSearchList.ftlh");
        Map<String, Object> dataModel = new HashMap<>();

        final String search = req.getParameter("search-type");
        switch (search) {
            case "name":
                final String partialDrinkName = req.getParameter("drink-name");
                if (partialDrinkName == null || partialDrinkName.isEmpty()) {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    return;
                }
                final List<FullDrinkView> foundDrinksByName = drinkService.findDrinksByName(partialDrinkName.trim());
                dataModel.put("drinkList", foundDrinksByName);
                dataModel.put("search-type", "name");
                logger.info("Drink list found by name sent to ftlh view");
                break;
            case "ingredient":
                final String[] ingredientNames = req.getParameterValues("drink-ingredients");
                if (ingredientNames == null || ingredientNames.length == 0) {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    return;
                }
                final List<String> ingredientNamesFiltered = Arrays.stream(ingredientNames)
                        .filter(i -> !(i.isBlank()))
                        .map(String::trim)
                        .collect(Collectors.toList());

                List<IngredientView> foundIngredientsByName = ingredientService.findIngredientsByName(ingredientNamesFiltered);
                final List<FullDrinkView> foundDrinksByIngredients =
                        drinkService.findDrinkByIngredients(foundIngredientsByName);
                dataModel.put("drinkList", foundDrinksByIngredients);
                dataModel.put("search-type", "ingredient");
                logger.info("Drink list found by ingredient sent to ftlh view");
                break;
        }

        try {
            template.process(dataModel, resp.getWriter());
        } catch (TemplateException e) {
            logger.error(e.getMessage());

        }
    }
}