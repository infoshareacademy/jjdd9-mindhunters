package com.infoshareacademy.controller;


import com.infoshareacademy.context.ContextHolder;
import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.repository.DrinkRepository;
import com.infoshareacademy.service.AdminManagementRecipeService;
import com.infoshareacademy.service.DrinkService;
import org.hibernate.Session;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/drink-management")
public class DrinkController {

    @EJB
    private DrinkService drinkService;

    @EJB
    private AdminManagementRecipeService adminManagementRecipeService;

    @Context
    private HttpServletRequest request;

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response get(@PathParam("id") Long id) {

        return Response.status(Response.Status.OK)
                .entity(drinkService.getFullDrinkViewById(id))
                .build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        ContextHolder contextHolder = new ContextHolder(request.getSession());
        String email = contextHolder.getEmail();

        if (adminManagementRecipeService.proposeDeleteDrink(id, email)) {
            return Response.status(204).build();
        } else {
            return Response.status(404).build();
        }
    }


    @POST
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, Drink updateDrink) {
        ContextHolder contextHolder = new ContextHolder(request.getSession());
        String email = contextHolder.getEmail();

        updateDrink.setConfirmUserEmail(email);
        if (email != null && drinkService.addOrUpdate(id, updateDrink, contextHolder)) {
            return Response.status(204).build();
        } else {
            return Response.status(404).build();
        }
    }


    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Drink updateDrink) {

        ContextHolder contextHolder = new ContextHolder(request.getSession());
        String email = contextHolder.getEmail();

        updateDrink.setConfirmUserEmail(email);

        if (email != null && drinkService.addOrUpdate(0L, updateDrink,contextHolder)) {
            return Response.status(204).build();
        } else {
            return Response.status(404).build();
        }
    }
}

