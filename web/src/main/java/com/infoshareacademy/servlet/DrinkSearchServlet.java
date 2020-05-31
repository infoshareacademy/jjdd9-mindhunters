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

import javax.ejb.EJB;
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

@WebServlet("/search-drinks")
public class DrinkSearchServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(DrinkSearchServlet.class.getName());

    @EJB
    private DrinkService drinkService;

    @EJB
    private IngredientService ingredientService;

    @Inject
    private TemplateProvider templateProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //test żeby nie wyswietlala się pusta strona
        resp.setContentType("text/html; charset=UTF-8");
        Template template = templateProvider.getTemplate(getServletContext(), "receipeSearchList.ftlh");
        Map<String, Object> dataModel = new HashMap<>();

        final List<FullDrinkView> foundDrinksByName = drinkService.findDrinksByName("cas");
        dataModel.put("drinkList", foundDrinksByName);

        try {
            template.process(dataModel, resp.getWriter());
        } catch (TemplateException e) {
            logger.error(e.getMessage());
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        Template template = templateProvider.getTemplate(getServletContext(), "receipeSearchList.ftlh");
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