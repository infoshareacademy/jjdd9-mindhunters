package com.infoshareacademy.service;

import com.infoshareacademy.domain.dto.FullDrinkView;
import com.infoshareacademy.domain.dto.IngredientView;
import com.infoshareacademy.service.validator.UserInputValidator;
import com.infoshareacademy.servlet.SingleViewServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Stateless
public class SearchTypeService {


    private static final Logger LOGGER = LoggerFactory.getLogger(SearchTypeService.class.getName());

    @EJB
    private DrinkService drinkService;

    @EJB
    private IngredientService ingredientService;

    @Inject
    private UserInputValidator userInputValidator;

    @Inject
    private QueryParamBuilder queryParamBuilder;

    public Map<String, Object> singleViewSearchType(Long drinkId){
        Map<String, Object> dataModel = new HashMap<>();

        if (drinkId < 0) {
            dataModel.put("errorMessage", "Wrong input.\n");
            LOGGER.debug("Negative drink id");
        } else {
            final FullDrinkView foundDrinkById = drinkService.findDrinkById(drinkId);

            if (foundDrinkById == null) {
                dataModel.put("errorMessage", "Drink not found.\n");
                LOGGER.debug("Drink id not found");
            }

            dataModel.put("drink", foundDrinkById);
        }
        return dataModel;
    }


    public  Map<String, Object> listViewSearchType(HttpServletRequest req){
        final String searchType = req.getParameter("search");
        final String pageNumberReq = req.getParameter("page");

        Map<String, Object> dataModel = new HashMap<>();
        int maxPage;
        int currentPage = userInputValidator.getFirstPageWhenWrongPageInput(pageNumberReq);

        if (searchType == null || searchType.length() == 0) {

            dataModel = createDmForDrinkList(currentPage);

        } else {

            switch (searchType) {

                case "name":
                    dataModel = createDmForSearchByName(req, currentPage);
                    break;

                case "ingr":
                    dataModel = createDmForSearchByIngredients(req, currentPage);
                    break;

                default:
                    dataModel = createDmForDrinkList(currentPage);
                    break;

            }

        }
        return dataModel;
    }


    public  Map<String, Object> createDmForDrinkList(int currentPage) {
        Map<String, Object> dataModel = new HashMap<>();

        int maxPage  = drinkService.maxPageNumberDrinkList();
        currentPage = userInputValidator.compareCurrentPageWithMaxPage(currentPage, maxPage);

        final List<FullDrinkView> paginatedDrinkList = drinkService.paginationDrinkList(currentPage);

        dataModel.put("drinkList", paginatedDrinkList);
        dataModel.put("maxPageSize", maxPage);
        dataModel.put("currentPage", currentPage);

        LOGGER.debug("No search method chosen - display all drinks list");
        return dataModel;
    }


    public  Map<String, Object> createDmForSearchByName(HttpServletRequest req, int currentPage) {
        Map<String, Object> dataModel = new HashMap<>();
        int maxPage;

        LOGGER.debug("Searching drinks by name");

        String partialDrinkName = req.getParameter("name");

        if (partialDrinkName == null || partialDrinkName.isEmpty() || !userInputValidator.validateSpecialChars(partialDrinkName.trim())) {
            dataModel.put("errorMessage", "Name not found.\n");
            return dataModel;
        }

        partialDrinkName = userInputValidator.removeExtraSpaces(partialDrinkName);
        maxPage = drinkService.maxPageNumberDrinksByName(partialDrinkName);

        currentPage = userInputValidator.compareCurrentPageWithMaxPage(currentPage, maxPage);


        final List<FullDrinkView> foundDrinksByName =
                drinkService.findDrinksByName(partialDrinkName.trim(), currentPage);

        if (foundDrinksByName == null || foundDrinksByName.isEmpty()) {
            dataModel.put("errorMessage", "Drink not found.\n");
            return dataModel;
        }

        dataModel.put("drinkList", foundDrinksByName);
        dataModel.put("queryName", queryParamBuilder.buildNameQuery(partialDrinkName));
        dataModel.put("maxPageSize", maxPage);
        dataModel.put("currentPage", currentPage);

        LOGGER.info("Drink list found by name sent to ftlh view");

        return dataModel;
    }


    public  Map<String, Object> createDmForSearchByIngredients(HttpServletRequest req, int currentPage) {
        LOGGER.debug("Searching drinks by ingredients");
        Map<String, Object> dataModel = new HashMap<>();
        int maxPage;

        final String[] ingredientParams = req.getParameterValues("ing");

        if (ingredientParams == null || ingredientParams.length == 0) {
            dataModel.put("errorMessage", "Ingredients not found.\n");
            return dataModel;
        }

        final List<String> ingredientNamesFiltered = filterIngrNames(ingredientParams);

        List<IngredientView> foundIngredientsByName = ingredientService.findIngredientsByName(ingredientNamesFiltered);

        if (foundIngredientsByName == null || foundIngredientsByName.size() == 0) {
            dataModel.put("errorMessage", "Ingredients not found.\n");
            LOGGER.info("Ingredients not found in database.");
            return dataModel;
        }

        maxPage = drinkService.maxPageNumberDrinksByIngredients(foundIngredientsByName);
        currentPage = userInputValidator.compareCurrentPageWithMaxPage(currentPage, maxPage);

        final List<FullDrinkView> foundDrinksByIngredients =
                drinkService.findDrinkByIngredients(foundIngredientsByName, currentPage);

        if (foundDrinksByIngredients == null || foundDrinksByIngredients.size() == 0) {
            dataModel.put("errorMessage", "Drinks not found.\n");
            LOGGER.info("Ingredients not found in database.");
            return dataModel;
        }

        dataModel.put("drinkList", foundDrinksByIngredients);
        dataModel.put("queryName", queryParamBuilder.buildIngrQuery(ingredientNamesFiltered));
        dataModel.put("maxPageSize", maxPage);
        dataModel.put("currentPage", currentPage);

        LOGGER.info("Drink list found by ingredient sent to ftlh view.");
        return dataModel;
    }


    public  List<String> filterIngrNames(String[] ingredientParams) {

        final Set<String> ingredientDistinctNamesFiltered = Arrays.stream(ingredientParams)
                .filter(i -> !(i.isBlank()))
                .filter(s -> userInputValidator.validateSpecialChars(s))
                .map(String::trim)
                .map(s1 -> userInputValidator.removeExtraSpaces(s1))
                .collect(Collectors.toSet());

        final List<String> ingredientNamesFiltered = new ArrayList<>(ingredientDistinctNamesFiltered);

        return ingredientNamesFiltered;
    }


}
