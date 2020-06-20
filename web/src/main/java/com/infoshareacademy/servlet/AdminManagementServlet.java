package com.infoshareacademy.servlet;

import com.infoshareacademy.context.ContextHolder;
import com.infoshareacademy.domain.dto.FullDrinkView;
import com.infoshareacademy.freemarker.TemplateProvider;
import com.infoshareacademy.service.CategoryService;
import com.infoshareacademy.service.DrinkService;
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

@WebServlet("/admin/to-approve-list")
public class AdminManagementServlet extends HttpServlet {

    private static final Logger packageLogger = LoggerFactory.getLogger(DrinkManagementServlet.class.getName());

    @EJB
    private DrinkService drinkService;

    @EJB
    private CategoryService categoryService;

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private UserInputValidator userInputValidator;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ContextHolder contextHolder = new ContextHolder(req.getSession());

        String role = contextHolder.getRole();
        Map<String, Object> dataModel = new HashMap<>();

        dataModel.put("name", contextHolder.getName());
        dataModel.put("role", contextHolder.getRole());

        if (role != null && (role.equalsIgnoreCase("SUPER_ADMIN") || role.equalsIgnoreCase("ADMIN"))){

            List<FullDrinkView> toApproveList = drinkService.findDrinksToApprove();

            if (!toApproveList.isEmpty()){
                List<Object>toApproveListModel = toApproveList.stream()
                        .map(FullDrinkView::getId)
                        .map(aLong ->  Integer.parseInt(aLong.toString()))
                        .collect(Collectors.toList());

                dataModel.put("drinkList", toApproveList);
            }

        }


        String servletPath = req.getServletPath();

        dataModel.put("servletPath",servletPath);

        Template template = templateProvider.getTemplate(getServletContext(), "receipeToApproveList.ftlh");

        try {
            template.process(dataModel, resp.getWriter());
        } catch (
                TemplateException e) {
            packageLogger.error(e.getMessage());
        }
    }
}