package com.infoshareacademy.servlet;

import com.infoshareacademy.domain.dto.FullDrinkView;
import com.infoshareacademy.freemarker.TemplateProvider;
import com.infoshareacademy.service.DrinkService;
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

@WebServlet("/favourites")
public class FavouriteDrinkListServlet extends HttpServlet {

    private static final Logger packageLogger = LoggerFactory.getLogger(FavouriteDrinkListServlet.class.getName());

    private final String userId = "1"; // mock user

    @EJB
    private DrinkService drinkService;

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private UserInputValidator userInputValidator;

    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pageNumberReq = req.getParameter("page");

        int currentPage;

        if (!userInputValidator.validatePageNumber(pageNumberReq)) {
            currentPage = 1;
        } else {
            currentPage = Integer.valueOf(pageNumberReq);
        }

        Map<String, Object> dataModel = new HashMap<>();

        int maxPage = userService.countPagesFavouritesList(userId);

        List<FullDrinkView> drinkViewList = userService.favouritesList(userId, currentPage);

//        List<FullDrinkView> favouritesList = userService.favouritesList(userId);
//
//        if (!favouritesList.isEmpty()) {
//            List<Object> favouritesListModel = favouritesList.stream()
//                    .map(FullDrinkView::getId)
//                    .map(aLong -> Integer.parseInt(aLong.toString()))
//                    .collect(Collectors.toList());
//
//        }
        dataModel.put("drinkList", drinkViewList);

        dataModel.put("maxPageSize", maxPage);

        dataModel.put("currentPage", currentPage);

        dataModel.put("favourites", drinkViewList);

        dataModel.put("queryName", maxPage);


        Template template = templateProvider.getTemplate(getServletContext(), "receipeList.ftlh");

        try {
            template.process(dataModel, resp.getWriter());
        } catch (
                TemplateException e) {
            packageLogger.error(e.getMessage());
        }
    }

}

