package com.infoshareacademy.servlet;

import com.infoshareacademy.context.ContextHolder;
import com.infoshareacademy.domain.dto.FullDrinkView;
import com.infoshareacademy.freemarker.TemplateProvider;
import com.infoshareacademy.service.UserService;
import com.infoshareacademy.service.validator.UserInputValidator;
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
import java.util.stream.Collectors;

@WebServlet("/favourites")
public class FavouriteDrinkListServlet extends HttpServlet {

    private static final Logger packageLogger = LoggerFactory.getLogger(FavouriteDrinkListServlet.class.getName());


    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private UserInputValidator userInputValidator;

    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String pageNumberReq = req.getParameter("page");

        int currentPage;

        if (!userInputValidator.validatePageNumber(pageNumberReq)) {
            currentPage = 1;
        } else {
            currentPage = Integer.valueOf(pageNumberReq);
        }

        Map<String, Object> dataModel = new HashMap<>();

        ContextHolder contextHolder = new ContextHolder(req.getSession());
        dataModel.put("name", contextHolder.getName());
        dataModel.put("role", contextHolder.getRole());

        String email = contextHolder.getEmail();


        List<FullDrinkView> drinkViewList = userService.favouritesList(email, currentPage);

        int maxPage = userService.countPagesFavouritesList(email);

        dataModel.put("drinkList", drinkViewList);

        List<Integer> favouritesId = null;

        if (!drinkViewList.isEmpty()) {
            favouritesId = drinkViewList.stream()
                    .map(FullDrinkView::getId)
                    .map(aLong -> Integer.parseInt(aLong.toString()))
                    .collect(Collectors.toList());

            dataModel.put("favourites", favouritesId);
        }

        String servletPath = req.getServletPath();

        dataModel.put("servletPath", servletPath);
        dataModel.put("favourites", favouritesId);
        dataModel.put("maxPageSize", maxPage);
        dataModel.put("currentPage", currentPage);

        Template template = templateProvider.getTemplate(getServletContext(), "favorites.ftlh");

        try {
            template.process(dataModel, resp.getWriter());
        } catch (
                TemplateException e) {
            packageLogger.error(e.getMessage());
        }
    }

}

