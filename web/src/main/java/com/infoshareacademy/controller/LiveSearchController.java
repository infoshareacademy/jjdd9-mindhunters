package com.infoshareacademy.controller;

import com.infoshareacademy.service.DrinkRestService;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/search")
@Produces(MediaType.APPLICATION_JSON)
public class LiveSearchController {

    @EJB
    private DrinkRestService drinkRestService;

    @GET
    @Path("/drink/{partialName}")
    public Response drink(@PathParam("partialName") String partialName) {
        return Response.status(Response.Status.OK)
                .entity(drinkRestService.findByNameLiveSearch(partialName))
                .build();
    }


    @GET
    @Path("/ingredient/{partialName}")
    public Response ingredient(@PathParam("partialName") String partialName) {
        return Response.status(Response.Status.OK)
                .entity(drinkRestService.findByNameLiveSearch(partialName))
                .build();
    }


}