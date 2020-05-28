package com.infoshareacademy.servlets;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.Ingredient;
import com.infoshareacademy.service.DrinkService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/search-drink")
public class SearchDrinkServlet extends HttpServlet {

    @EJB
    DrinkService drinkService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        final PrintWriter writer = resp.getWriter();

        final Drink foundDrink = drinkService.findDrinkByName(3L);
        writer.println(foundDrink.getDrinkName() +
                foundDrink.getDrinkIngredients() +
                foundDrink.getAlcoholStatus());

        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);
        ingredient.setName("Light rum");

        final List<Drink> foundDrinks = drinkService.findDrinkByIngredients(List.of(ingredient));

        writer.println(foundDrinks);

    }
}
