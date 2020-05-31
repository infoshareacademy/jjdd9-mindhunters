package com.infoshareacademy.servlet;

import com.infoshareacademy.domain.dto.FullDrinkView;
import com.infoshareacademy.domain.dto.IngredientView;
import com.infoshareacademy.service.DrinkService;
import com.infoshareacademy.service.IngredientService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

@WebServlet("/search-drink")
public class SearchDrinkServlet extends HttpServlet {

    @EJB
    DrinkService drinkService;

    @EJB
    IngredientService ingredientService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        final PrintWriter writer = resp.getWriter();

        Integer randomInt = new Random().nextInt(27) + 1;
        Long randomLong = randomInt.longValue();
        //test findDrinkById
        final FullDrinkView foundDrinkById = drinkService.findDrinkById(randomLong);
        writer.println("Find by Id:<br><br>");
        writer.println(foundDrinkById.getDrinkName());
        writer.println("<br>");
        writer.println(foundDrinkById.getAlcoholStatus());
        writer.println("<br>");
        writer.println(foundDrinkById.getCategoryView().getName());
        writer.println("<br>");
        writer.println("Ingredients: ");
        foundDrinkById.getDrinkIngredientViews()
                .forEach(d -> writer.println(d.getName() + " | " + d.getMeasure() + "<br>"));
        writer.println("<br><br><br><br>");


        //test findDrinkByIngredients
        IngredientView ingredientView = new IngredientView();
        ingredientView.setId(1L);
        ingredientView.setName("ru");
        List<IngredientView> foundIngredientsByName = ingredientService.findIngredientsByName(ingredientView.getName());
        writer.println("Found drinks by Ingredients: <br><br>");

        drinkService.findDrinkByIngredients(foundIngredientsByName)
                .forEach(d -> writer.println(d.getDrinkName() + "<br>"));
        writer.println("<br><br><br><br>");


        //test findDrinksByName
        final List<FullDrinkView> foundDrinksByName = drinkService.findDrinksByName("ca");
        writer.println("Found drinks by Name: <br><br>");
        foundDrinksByName
                .forEach(d -> writer.println(d.getDrinkName() + "<br>" + d.getImage() + "<br>" + d.getCategoryView().getName() + "<br><br>"));
        writer.println("<br><br><br><br>");
    }
}
