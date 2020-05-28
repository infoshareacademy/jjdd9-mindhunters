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


        //test findDrinkById
        final Drink foundDrinkById = drinkService.findDrinkById(3L);
        writer.println("Find by Id:<br><br>");
        writer.println(foundDrinkById.getDrinkName());
        writer.println("<br>");
        writer.println(foundDrinkById.getAlcoholStatus());
        writer.println("<br>");
        writer.println(foundDrinkById.getCategory().getName());
        writer.println("<br>");
        writer.println("Ingredients: ");
        foundDrinkById.getDrinkIngredients()
                .forEach( d -> writer.println(d.getIngredient().getName() + " | "));
        writer.println("<br><br><br><br>");


        //test findDrinkByIngredients
        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);
        ingredient.setName("Light rum");
        final List<Drink> foundDrinksByIngredients = drinkService.findDrinkByIngredients(List.of(ingredient.getName()));
        writer.println("Found drinks by Ingredients: <br><br>");
        foundDrinksByIngredients
                .forEach( d-> writer.println(d.getDrinkName() + "<br>"));
        writer.println("<br><br><br><br>");


        //test findDrinkByName
        final Drink foundDrinkByName = drinkService.findDrinkByName("cream soda");
        writer.println("Find by Name:<br><br>");
        writer.println(foundDrinkByName.getDrinkName());
        writer.println("<br>");
        writer.println(foundDrinkByName.getAlcoholStatus());
        writer.println("<br>");
        writer.println(foundDrinkByName.getCategory().getName());
        writer.println("<br>");
        writer.println("Ingredients: ");
        foundDrinkByName.getDrinkIngredients()
                .forEach( d -> writer.println(d.getIngredient().getName() + " | "));
        writer.println("<br><br>");
    }
}
