package com.infoshareacademy.servlet;

import com.infoshareacademy.domain.dto.CategoryView;
import com.infoshareacademy.domain.dto.FullDrinkView;
import com.infoshareacademy.domain.dto.IngredientView;
import com.infoshareacademy.freemarker.TemplateProvider;
import com.infoshareacademy.service.DrinkService;
import com.infoshareacademy.service.IngredientService;
import com.infoshareacademy.service.validator.UserInputValidator;
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
import java.util.*;
import java.util.stream.Collectors;

@WebServlet("/search")
public class DrinkSearchServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(DrinkSearchServlet.class.getName());

    @EJB
    private DrinkService drinkService;

    @EJB
    private IngredientService ingredientService;

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private UserInputValidator userInputValidator;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final int currentPage = Integer.parseInt(req.getParameter("page"));

        final int maxPage ;

        final List<FullDrinkView> allDrinks = drinkService.findAllDrinks();

        final List<CategoryView> categories = categoryService.findAllCategories();

        Map<String, Object> dataModel = new HashMap<>();

        dataModel.put("categories", categories);

        String categoriesParam = req.getParameter("category");

        if (categoriesParam != null && !categoriesParam.isEmpty()){

            String[] query = req.getParameterValues("category");

            List<Long> searchingCategory = Arrays.stream(query).map(s -> Long.valueOf(s))
                    .collect(Collectors.toList());


            final List<FullDrinkView> drinksByCategories = drinkService.findAllDrinksByCategories(searchingCategory,currentPage);
            dataModel.put("drinkList", drinksByCategories);


            String queryName = "category=" + Arrays.stream(query).collect(Collectors.joining("&&category="));


            maxPage = drinkService.maxPageNumberDrinksByCategories(searchingCategory);

            dataModel.put("maxPageSize", maxPage);
            dataModel.put("queryName", queryName);


        } else{

            final List<FullDrinkView> paginatedDrinkList = drinkService.paginationDrinkList(currentPage);

            dataModel.put("drinkList", paginatedDrinkList);

            maxPage = drinkService.maxPageNumberDrinkList();
            dataModel.put("maxPageSize", maxPage);

        }
        dataModel.put("currentPage", currentPage);



        Template template = templateProvider.getTemplate(getServletContext(), "receipeList.ftlh");

        try {
            template.process(dataModel, resp.getWriter());
        } catch (TemplateException e) {
            packageLogger.error(e.getMessage());
        }
    }
















        /*//3 losowe drinki
        resp.setContentType("text/html; charset=UTF-8");
        Template template = templateProvider.getTemplate(getServletContext(), "receipeSearchList.ftlh");
        Map<String, Object> dataModel = new HashMap<>();


        Long allDrinks = drinkService.findTotalDrinksAmount();
        List<FullDrinkView> foundDrinksById = new ArrayList<>();

        for (int i = 0; i < 3 ; i++) {
            long v = (long) (Math.random() * allDrinks + 1);
            foundDrinksById.add(drinkService.findDrinkById(v));
        }

        dataModel.put("drinkList", foundDrinksById);

        try {
            template.process(dataModel, resp.getWriter());
        } catch (TemplateException e) {
            logger.error(e.getMessage());
        }*/
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

                if (partialDrinkName == null || partialDrinkName.isEmpty() || !userInputValidator.validateSpecialChars(partialDrinkName.trim())) {
                    dataModel.put("errorMessage", "Name not found.\n");
                    break;
                }

                final List<FullDrinkView> foundDrinksByName = drinkService.findDrinksByName(partialDrinkName.trim());
                dataModel.put("drinkList", foundDrinksByName);
                logger.info("Drink list found by name sent to ftlh view");
                break;

            case "ingredient":
                final String[] ingredientNames = req.getParameterValues("drink-ingredients");

                if (ingredientNames == null || ingredientNames.length == 0 ) {
                    dataModel.put("errorMessage", "Wrong input.\n");
                    break;
                }

                final List<String> ingredientNamesFiltered = Arrays.stream(ingredientNames)
                        .filter(i -> !(i.isBlank()))
                        .filter(s->userInputValidator.validateSpecialChars(s))
                        .map(String::trim)
                        .collect(Collectors.toList());

                List<IngredientView> foundIngredientsByName = ingredientService.findIngredientsByName(ingredientNamesFiltered);

                if (foundIngredientsByName == null || foundIngredientsByName.size() == 0){
                    dataModel.put("errorMessage", "Ingredients not found.\n");
                    logger.info("Ingredients input not found in database.");
                    break;
                }

                final List<FullDrinkView> foundDrinksByIngredients =
                        drinkService.findDrinkByIngredients(foundIngredientsByName);
                dataModel.put("drinkList", foundDrinksByIngredients);
                logger.info("Drink list found by ingredient sent to ftlh view.");
                break;
        }

        try {
            template.process(dataModel, resp.getWriter());
        } catch (TemplateException e) {
            logger.error(e.getMessage());

        }
    }
}