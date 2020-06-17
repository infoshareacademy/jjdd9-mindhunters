package com.infoshareacademy.servlet;

import com.infoshareacademy.context.ContextHolder;
import com.infoshareacademy.domain.dto.FullDrinkView;
import com.infoshareacademy.freemarker.TemplateProvider;
import com.infoshareacademy.service.DrinkService;
import com.infoshareacademy.service.StatisticsService;
import com.infoshareacademy.service.UserService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/single-view")
public class SingleViewServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(SingleViewServlet.class.getName());

    @EJB
    private DrinkService drinkService;

    @EJB
    private StatisticsService statisticsService;

    @Inject
    private UserInputValidator userInputValidator;


    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private UserService userService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html; charset=UTF-8");
        final String idParam = req.getParameter("drink");
        Long drinkId = userInputValidator.stringToLongConverter(idParam);
        Map<String, Object> dataModel = new HashMap<>();

        ContextHolder contextHolder = new ContextHolder(req.getSession());
        dataModel.put("name", contextHolder.getName());
        dataModel.put("role", contextHolder.getRole());

        String email = contextHolder.getEmail();

        if (drinkId < 0) {
            dataModel.put("errorMessage", "Wrong input.\n");
        } else {
            final FullDrinkView foundDrinkById = drinkService.getDrinkById(drinkId);


            if (foundDrinkById == null) {
                dataModel.put("errorMessage", "Drink not found.\n");
            }

            dataModel.put("drink", foundDrinkById);
            statisticsService.addToStatistics(foundDrinkById);
        }

        if (email != null && !email.isEmpty()){

            List<FullDrinkView> favouritesList = userService.favouritesList(email);

            if (!favouritesList.isEmpty()){
                List<Object>favouritesListModel = favouritesList.stream()
                        .map(FullDrinkView::getId)
                        .map(aLong ->  Integer.parseInt(aLong.toString()))
                        .collect(Collectors.toList());

                dataModel.put("favourites", favouritesListModel);
            }

        }

        Template template = templateProvider.getTemplate(getServletContext(), "singleDrinkView.ftlh");
        try {
            template.process(dataModel, resp.getWriter());
        } catch (TemplateException e) {
            logger.error(e.getMessage());

        }
    }


}
