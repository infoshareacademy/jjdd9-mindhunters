package com.infoshareacademy.servlet;

import com.infoshareacademy.freemarker.TemplateProvider;
import com.infoshareacademy.service.SearchTypeService;
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
import java.util.Map;

@WebServlet("/single-view")
public class SingleViewServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(SingleViewServlet.class.getName());

    @EJB
    private SearchTypeService searchTypeService;

    @Inject
    private UserInputValidator userInputValidator;

    @Inject
    private TemplateProvider templateProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html; charset=UTF-8");
        final String idParam = req.getParameter("drink");
        Long drinkId = userInputValidator.stringToLongConverter(idParam);
        Map<String, Object> dataModel;

        dataModel = searchTypeService.singleViewSearchType(drinkId);

        Template template = templateProvider.getTemplate(getServletContext(), "singleDrinkView.ftlh");

        try {
            template.process(dataModel, resp.getWriter());
        } catch (TemplateException e) {
            LOGGER.error(e.getMessage());

        }
    }

}
