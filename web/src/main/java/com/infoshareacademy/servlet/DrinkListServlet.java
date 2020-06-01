package com.infoshareacademy.servlet;

import com.infoshareacademy.domain.dto.FullDrinkView;
import com.infoshareacademy.freemarker.TemplateProvider;
import com.infoshareacademy.service.CategoryService;
import com.infoshareacademy.service.DrinkService;
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


@WebServlet("/list")
public class DrinkListServlet extends HttpServlet {

    private static final Logger packageLogger = LoggerFactory.getLogger(LoggerServlet.class.getName());

    @EJB
    private DrinkService drinkService;

    @EJB
    private CategoryService categoryService;

    @Inject
    private TemplateProvider templateProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        final int currentPage = Integer.parseInt(req.getParameter("page"));

        final int maxPage ;

        final List<FullDrinkView> allDrinks = drinkService.findAllDrinks();

        final List<String> categoryNameList = categoryService.findAllCategories().stream()
                  .map(categoryView -> categoryView.getName()).collect(Collectors.toList());


        Map<String, Object> dataModel = new HashMap<>();

        dataModel.put("categories", categoryNameList);

        String categories = req.getParameter("category");

        if (categories != null && !categories.isEmpty()){

            String[] query = req.getParameterValues("category");

            List<String> searchingCategory = Arrays.stream(query)
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

}
