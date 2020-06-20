package com.infoshareacademy.scheduler;


import com.infoshareacademy.email.EmailBuildStrategy;
import com.infoshareacademy.email.EmailSender;
import com.infoshareacademy.service.DrinkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class DrinkReportScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DrinkReportScheduler.class.getName());

    @EJB
    DrinkService drinkService;

    @EJB
    private EmailSender emailSender;

    @EJB
    private EmailBuildStrategy emailBuildStrategy;


    @Schedule(hour = "*", minute = "2/2")
    public void checkRecipesForApproval() {

        //List<SimpleDrinkView> drinksForApproval = drinkService.checkNewDrinksForApproval();


        emailSender.sendEmail(emailBuildStrategy.createContent());
        //drinkService.checkRecipesForApproval();
        LOGGER.info("CheckRecipesForApproval, every 15 min scheduler");
    }





}
