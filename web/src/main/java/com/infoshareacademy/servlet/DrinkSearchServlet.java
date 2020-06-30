package com.infoshareacademy.servlet;

import com.infoshareacademy.context.ContextHolder;
import com.infoshareacademy.freemarker.TemplateProvider;
import com.infoshareacademy.service.SearchTypeService;
import com.infoshareacademy.service.UserService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/search")
public class DrinkSearchServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(DrinkSearchServlet.class.getName());

    @EJB
    private SearchTypeService searchTypeService;

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        Map<String, Object> dataModel;

        dataModel = searchTypeService.listViewSearchType(req);

        ContextHolder contextHolder = new ContextHolder(req.getSession());
        dataModel.put("name", contextHolder.getName());
        dataModel.put("role", contextHolder.getRole());


        verifyAge18(req, resp, contextHolder);

        setAdultFromCookies(req, contextHolder);


        if (contextHolder.getADULT() != null) {
            dataModel.put("adult", contextHolder.getADULT());
        }

        Template template = templateProvider.getTemplate(getServletContext(), "receipeSearchList.ftlh");
        try {
            template.process(dataModel, resp.getWriter());
        } catch (TemplateException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void verifyAge18(HttpServletRequest req, HttpServletResponse resp, ContextHolder contextHolder) {
        String adult = req.getParameter("adult");
        String age18 = req.getParameter("age18");

        if (adult != null) {

            switch (adult) {

                case "true":
                    contextHolder.setADULT(adult);

                    if (age18 != null) {

                        createAdultCookie(resp, "true");

                    }
                    break;

                case "false":

                    contextHolder.setADULT(adult);

                    if (age18 != null) {

                        createAdultCookie(resp, "false");

                    }

                    break;
            }
        }
    }

    private void createAdultCookie(HttpServletResponse resp, String value) {
        Cookie cookie = new Cookie("age18", value);
        cookie.setMaxAge(60 * 60 * 24);
        resp.addCookie(cookie);
    }

    private void setAdultFromCookies(HttpServletRequest req, ContextHolder contextHolder) {
        Cookie[] c = req.getCookies();
        if (c != null) {

            final List<Cookie> age18s =
                    Arrays.stream(c).filter(e -> e.getName().equalsIgnoreCase("age18")).collect(Collectors.toList());

            if (!age18s.isEmpty()) {
                contextHolder.setADULT(age18s.get(0).getValue());
            }
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String drinkId = req.getParameter("drinkId");

        ContextHolder contextHolder = new ContextHolder(req.getSession());
        String email = contextHolder.getEmail();

        if (email != null && !email.isEmpty()) {

            userService.saveOrDeleteFavourite(email, drinkId);

        }
    }

}