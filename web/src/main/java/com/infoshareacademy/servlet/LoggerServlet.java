package com.infoshareacademy.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/loggers")
public class LoggerServlet extends HttpServlet {

    private static final Logger packageLogger = LoggerFactory.getLogger(LoggerServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");

        packageLogger.debug("Mindhunters Debug\n");
        packageLogger.info("Mindhunters Info\n");
        packageLogger.warn("Mindhunters Warn\n");
        packageLogger.error("Mindhunters Error\n");

    }
}
