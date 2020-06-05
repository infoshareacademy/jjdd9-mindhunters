package com.infoshareacademy.servlet;

import com.infoshareacademy.domain.dto.CategoryView;
import com.infoshareacademy.domain.dto.FullDrinkView;
import com.infoshareacademy.freemarker.TemplateProvider;
import com.infoshareacademy.service.CategoryService;
import com.infoshareacademy.service.DrinkService;
import com.infoshareacademy.service.SearchType;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet("/list")
public class DrinkListServlet extends HttpServlet {

    private static final Logger packageLogger = LoggerFactory.getLogger(DrinkListServlet.class.getName());

    @EJB
    private DrinkService drinkService;

    @EJB
    private CategoryService categoryService;

//    @EJB
//    private SearchType searchType;

    @Inject
    private TemplateProvider templateProvider;



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String pageNumberReq = req.getParameter("page");
        int currentPage;

        if (pageNumberReq.matches("0|[1-9]\\d*")) {
            currentPage = Integer.parseInt(req.getParameter("page"));

        } else {
            currentPage = 1;
        }

        final List<CategoryView> categories = categoryService.findAllCategories();

        Map<String, Object> dataModel = new HashMap<>();

        Map<String, String[]> searchParam = req.getParameterMap();

        SearchType searchType = drinkService.checkingSearchingCase(searchParam, currentPage);

        int maxPage = searchType.getMaxPage();

        List<FullDrinkView> drinkViewList = searchType.getDrinkViewList();

        String queryName = searchType.getQueryName();

        dataModel.put("categories", categories);
        dataModel.put("maxPageSize", maxPage);
        dataModel.put("queryName", queryName);
        dataModel.put("drinkList", drinkViewList);
        dataModel.put("currentPage", currentPage);


        Template template = templateProvider.getTemplate(getServletContext(), "receipeList.ftlh");

        try {
            template.process(dataModel, resp.getWriter());
        } catch (
                TemplateException e) {
            packageLogger.error(e.getMessage());
        }
    }

}
