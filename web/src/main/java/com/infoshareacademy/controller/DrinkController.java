package com.infoshareacademy.controller;


import com.infoshareacademy.repository.DrinkRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/drink-management")
public class DrinkController {

    @Inject
    private DrinkRepository drinkRepository;

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response get(@PathParam("id") Long id) {

        return drinkRepository.findAllDrinks().stream().filter(e -> e.getId().equals(id)).findFirst()
                .map(element -> Response.ok(element, MediaType.APPLICATION_JSON).build())
                .orElseGet(() -> Response.status(404).build());
    }
}
