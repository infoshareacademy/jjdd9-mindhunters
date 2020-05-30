package com.infoshareacademy.servlet;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.dto.FullDrinkView;
import com.infoshareacademy.freemarker.TemplateProvider;
import com.infoshareacademy.service.CategoryService;
import com.infoshareacademy.service.DrinkService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/search")
public class DrinkSearchServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(DrinkSearchServlet.class.getName());

    @Inject
    private DrinkService drinkService;

    @Inject
    private TemplateProvider templateProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        Template template = templateProvider.getTemplate(getServletContext(), "receipeSearchList.ftlh");
        Map<String, Object> dataModel = new HashMap<>();
        final String drinkName = req.getParameter("drinkName");
        //final Map<String, String[]> ingredientNames = req.getParameterMap();

        //test findDrinksByName
/*        if (drinkName == null || drinkName.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }*/
        final List<FullDrinkView> foundDrinksByName = drinkService.findDrinksByName("cas");
        dataModel.put("drinkList", foundDrinksByName);
        dataModel.put("categories", List.of("testooooooooooooooooooo", "test", "test", "test" ));

        try {
            template.process(dataModel, resp.getWriter());
        } catch (TemplateException e) {
            logger.error(e.getMessage());
        }
    }
}
