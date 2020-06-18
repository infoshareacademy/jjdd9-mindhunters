package com.infoshareacademy.controller;


import com.infoshareacademy.service.StatisticsService;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/stats")
public class StatisticsController {

    @EJB
    StatisticsService statisticsService;

    @GET
    @Path("/drinks/top-10")
    @Produces(MediaType.APPLICATION_JSON)
    public Response topDrinks() {

        return Response.status(Response.Status.OK)
                .entity(statisticsService.getTopDrinks().entrySet())
                .build();
    }


    @GET
    @Path("/categories")
    @Produces(MediaType.APPLICATION_JSON)
    public Response categoriesStats() {

        return Response.status(Response.Status.OK)
                .entity(statisticsService.getCategoriesStats().entrySet())
                .build();
    }

    @GET
    @Path("/drinks/category")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDrinksPerCategory() {

        return Response.status(Response.Status.OK)
                .entity(statisticsService.getDrinksPerAllCategories().entrySet())
                .build();
    }

}
