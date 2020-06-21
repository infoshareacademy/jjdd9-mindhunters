package com.infoshareacademy.controller;


import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.dto.FullDrinkView;
import com.infoshareacademy.repository.DrinkRepository;
import com.infoshareacademy.service.DrinkService;

import javax.ejb.EJB;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/drink-management")
public class DrinkController {

    @EJB
    private DrinkService drinkService;

    @EJB
    private DrinkRepository drinkRepository;


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


        if (drinkService.deleteDrinkById(id)) {
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


        if (drinkService.update(id, updateDrink)) {
            return Response.status(204).build();
        } else {
            return Response.status(404).build();
        }
    }
}

