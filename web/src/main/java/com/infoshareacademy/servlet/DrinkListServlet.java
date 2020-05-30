package com.infoshareacademy.servlets;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.dto.CategoryView;
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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@WebServlet("/list")
public class DrinkListServlet extends HttpServlet {

    private static final Logger packageLogger = LoggerFactory.getLogger(DrinkListServlet.class.getName());

    @EJB
    private DrinkService drinkService;

    @EJB
    private CategoryService categoryService;

    @Inject
    private TemplateProvider templateProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        final int currentPage = Integer.parseInt(req.getParameter("page"));

        final int maxPage = drinkService.maxPageNumber();

        final List<FullDrinkView> drinkList = drinkService.findAllDrinks();

        final List<String> categoryNameList = categoryService.findAllCategories().stream()
                  .map(categoryView -> categoryView.getName()).collect(Collectors.toList());

        Map<String, Object> dataModel = new HashMap<>();

        dataModel.put("categories", categoryNameList);
        dataModel.put("maxPageSize", maxPage);



        String page = req.getParameter("page");
        if (page != null && !page.isEmpty()){
            final List<FullDrinkView> paginatedDrinkList = drinkService.paginationDrinkList(Integer.parseInt(req.getParameter("page")));
            dataModel.put("drinkList", paginatedDrinkList);
            dataModel.put("currentPage", currentPage);

        } else{

            dataModel.put("drinkList", drinkList);
        }


        Template template = templateProvider.getTemplate(getServletContext(), "receipeList.ftlh");

        try {
            template.process(dataModel, resp.getWriter());
        } catch (TemplateException e) {
            packageLogger.error(e.getMessage());
        }
    }
}
