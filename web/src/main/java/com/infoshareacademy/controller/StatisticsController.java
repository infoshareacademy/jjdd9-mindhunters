package com.infoshareacademy.controller;


import com.infoshareacademy.service.StatisticsService;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/stats")
public class StatisticsController {

    @EJB
    StatisticsService statisticsService;

    @GET
    @Path("/top-10")
    @Produces(MediaType.APPLICATION_JSON)
    public Response topDrinks() {

        return Response.status(Response.Status.OK)
                .entity(statisticsService.getTopDrinks().entrySet())
                .build();
    }


    @GET
    @Path("/topCategories")
    @Produces(MediaType.APPLICATION_JSON)
    public Response topCategories() {

        return Response.status(Response.Status.OK)
                .entity(statisticsService.getTopDrinks().entrySet())
                .build();
    }

}
