package com.infoshareacademy.scheduler;


import com.infoshareacademy.email.EmailSender;
import com.infoshareacademy.service.DrinkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class DrinkReportScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DrinkReportScheduler.class.getName());

    @EJB
    DrinkService drinkService;

    @EJB
    private EmailSender emailSender;


    @Schedule(hour = "*", minute = "2/2")
    public void checkRecipesForApproval() {
        emailSender.sendEmail();
        //drinkService.checkRecipesForApproval();
        LOGGER.info("CheckRecipesForApproval, every 15 min scheduler");
    }





}
