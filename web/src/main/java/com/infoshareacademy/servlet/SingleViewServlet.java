package com.infoshareacademy.servlet;

import com.infoshareacademy.domain.dto.FullDrinkView;
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
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@WebServlet("/single-view")
public class SingleViewServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(SingleViewServlet.class.getName());

    @EJB
    DrinkService drinkService;

    @EJB
    IngredientService ingredientService;

    @Inject
    private TemplateProvider templateProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //test żeby zaprezentować single-view
        resp.setContentType("text/html; charset=UTF-8");
        Template template = templateProvider.getTemplate(getServletContext(), "singleDrinkView.ftlh");
        final PrintWriter writer = resp.getWriter();
        Map<String, Object> dataModel = new HashMap<>();

        Integer randomInt = new Random().nextInt(27) + 1;
        Long randomLong = randomInt.longValue();

        final FullDrinkView foundDrinkById = drinkService.findDrinkById(randomLong);

        dataModel.put("drink", foundDrinkById);
        try {
            template.process(dataModel, resp.getWriter());
        } catch (TemplateException e) {
            logger.error(e.getMessage());

        }

    }
}
