package com.infoshareacademy.servlet;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.service.DrinkService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet
public class DrinkSearchServlet extends HttpServlet  {

    @EJB
    private DrinkService drinkService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/*        String partialDrinkName = "ada";

        List<Drink> drinks = drinkService.findDrinkByName(partialDrinkName);*/


    }

}
