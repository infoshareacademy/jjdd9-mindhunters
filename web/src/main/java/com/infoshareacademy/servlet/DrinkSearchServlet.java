package com.infoshareacademy.servlet;

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
        resp.setContentType("text/html; charset=UTF-8");

        Map<String, Object> dataModel = new HashMap<>();
        int maxPage;
        int currentPage = 0;

        final String searchType = req.getParameter("search");
        String pageNumberReq = req.getParameter("page");

        currentPage = getFirstPageWhenWrongPageNumber(req, pageNumberReq);

        if (searchType == null || searchType.length() == 0) {

            displayAllDrinks(dataModel, currentPage);

        } else {

            switch (searchType) {

                case "name":


                    String partialDrinkName = req.getParameter("name");

                    if (partialDrinkName == null || partialDrinkName.isEmpty() || !userInputValidator.validateSpecialChars(partialDrinkName.trim())) {
                        dataModel.put("errorMessage", "Name not found.\n");
                        break;
                    }

                    partialDrinkName = userInputValidator.removeExtraSpaces(partialDrinkName);

                    final List<FullDrinkView> foundDrinksByName =
                            drinkService.findDrinksByName(partialDrinkName.trim(), currentPage);

                    if (foundDrinksByName == null || foundDrinksByName.isEmpty()) {
                        dataModel.put("errorMessage", "Drink not found.\n");
                        break;
                    }

                    maxPage = drinkService.maxPageNumberDrinksByName(partialDrinkName);

                    dataModel.put("drinkList", foundDrinksByName);
                    dataModel.put("queryName", buildNameQuery(partialDrinkName));
                    dataModel.put("maxPageSize", maxPage);

                    logger.info("Drink list found by name sent to ftlh view");
                    break;

                case "ingr":

                    final String[] ingredientParams = req.getParameterValues("ing");

                    if (ingredientParams == null || ingredientParams.length == 0) {
                        dataModel.put("errorMessage", "Ingredients not found.\n");
                        break;
                    }

                    final Set<String> ingredientDistinctNamesFiltered = Arrays.stream(ingredientParams)
                            .filter(i -> !(i.isBlank()))
                            .filter(s -> userInputValidator.validateSpecialChars(s))
                            .map(String::trim)
                            .map(s1 -> userInputValidator.removeExtraSpaces(s1))
                            .collect(Collectors.toSet());

                    final List<String> ingredientNamesFiltered = new ArrayList<>(ingredientDistinctNamesFiltered);

                    List<IngredientView> foundIngredientsByName = ingredientService.findIngredientsByName(ingredientNamesFiltered);

                    if (foundIngredientsByName == null || foundIngredientsByName.size() == 0) {
                        dataModel.put("errorMessage", "Ingredients not found.\n");
                        logger.info("Ingredients not found in database.");
                        break;
                    }


                    final List<FullDrinkView> foundDrinksByIngredients =
                            drinkService.findDrinkByIngredients(foundIngredientsByName, currentPage);

                    if (foundDrinksByIngredients == null || foundDrinksByIngredients.size() == 0) {
                        dataModel.put("errorMessage", "Drinks not found.\n");
                        logger.info("Ingredients not found in database.");
                        break;
                    }

                    maxPage = drinkService.maxPageNumberDrinksByIngredients(foundIngredientsByName);

                    dataModel.put("drinkList", foundDrinksByIngredients);
                    dataModel.put("queryName", buildIngrQuery(ingredientNamesFiltered));
                    dataModel.put("maxPageSize", maxPage);

                    logger.info("Drink list found by ingredient sent to ftlh view.");
                    break;

                default:

                    displayAllDrinks(dataModel, currentPage);
                    break;
            }

        }

        dataModel.put("currentPage", currentPage);

        Template template = templateProvider.getTemplate(getServletContext(), "receipeSearchList.ftlh");
        try {
            template.process(dataModel, resp.getWriter());
        } catch (TemplateException e) {
            logger.error(e.getMessage());
        }
    }

    private int getFirstPageWhenWrongPageNumber(HttpServletRequest req, String pageNumberReq) {

        if (pageNumberReq  == null || pageNumberReq.trim().isEmpty() || !userInputValidator.validatePageNumber(pageNumberReq)){
            return 1;
        }

        return Integer.parseInt(req.getParameter("page"));
    }

    private void displayAllDrinks(Map<String, Object> dataModel, int currentPage) {
        int maxPage;
        final List<FullDrinkView> paginatedDrinkList = drinkService.paginationDrinkList(currentPage);

        dataModel.put("drinkList", paginatedDrinkList);

        maxPage = drinkService.maxPageNumberDrinkList();

        dataModel.put("maxPageSize", maxPage);
    }

    private String buildIngrQuery(List<String> ingredientNamesFiltered) {
        StringBuilder queryIngrBuilder = new StringBuilder();
        queryIngrBuilder.append("search=ingr&ing=");
        queryIngrBuilder.append(ingredientNamesFiltered
                .stream()
                .collect(Collectors.joining("&ing=")));
        return queryIngrBuilder.toString();
    }

    private String buildNameQuery(String partialDrinkName) {
        StringBuilder queryNameBuilder = new StringBuilder();
        queryNameBuilder.append("search=name&");
        return queryNameBuilder.append("name=" + partialDrinkName).toString();
    }

}