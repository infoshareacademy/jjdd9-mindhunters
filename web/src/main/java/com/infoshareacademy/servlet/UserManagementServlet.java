package com.infoshareacademy.servlet;

import com.infoshareacademy.context.ContextHolder;
import com.infoshareacademy.freemarker.TemplateProvider;
import com.infoshareacademy.service.AdminUserService;
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
import java.util.Map;

@WebServlet("/admin-users")
public class UserManagementServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserManagementServlet.class.getName());

    @Inject
    private TemplateProvider templateProvider;

    @EJB
    private AdminUserService adminUserService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        Map<String, Object> dataModel = new HashMap<>();

        ContextHolder contextHolder = new ContextHolder(req.getSession());
        dataModel.put("name", contextHolder.getName());
        dataModel.put("role", contextHolder.getRole());

        dataModel.put("users", adminUserService.showUsers());

        LOGGER.debug("List of users sent to admin panel adminUserManagement");

        Template template = templateProvider.getTemplate(getServletContext(), "adminUserManagement.ftlh");
        try {
            template.process(dataModel, resp.getWriter());
        } catch (TemplateException e) {
            LOGGER.error(e.getMessage());

        }
    }
}

