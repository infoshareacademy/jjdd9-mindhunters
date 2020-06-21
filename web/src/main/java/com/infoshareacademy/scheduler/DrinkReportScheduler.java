package com.infoshareacademy.scheduler;


import com.infoshareacademy.domain.dto.FullDrinkView;
import com.infoshareacademy.email.EmailBuildStrategy;
import com.infoshareacademy.email.EmailSender;
import com.infoshareacademy.service.DrinkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Singleton
@Startup
public class DrinkReportScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DrinkReportScheduler.class.getName());

    private Long scheduleCounter = 0L;
    private List<FullDrinkView> drinks1st = new ArrayList<>();
    private List<FullDrinkView> drinks3rd = new ArrayList<>();


    @EJB
    private DrinkService drinkService;

    @EJB
    private EmailSender emailSender;

    @EJB(beanName = "admin")
    private EmailBuildStrategy emailBuildStrategy;


    @Schedule(hour = "*", minute = "2/15")
    public void checkRecipesForApproval() {
        LOGGER.debug("Counter {}", scheduleCounter);

        if (scheduleCounter % 2 == 0) {

//TODO dummy search method - replace with method searching drinks scheduled for approval
            //drinks1st = drinkService.findDrinksByName("auto", 1);

            if (recipeAwaiting3scheduleCalls()) {

                emailSender.sendEmail(emailBuildStrategy.createContent(drinks1st));
                LOGGER.info("CheckRecipesForApproval - email send");
                scheduleCounter = 0L;
                drinks3rd = Collections.emptyList();
                return;
            }

            drinks3rd = drinks1st;

        }
        scheduleCounter++;
        LOGGER.info("CheckRecipesForApproval, every 15 min scheduler, no action required");
    }


    private boolean recipeAwaiting3scheduleCalls() {

        return drinks1st.stream().anyMatch(a -> {
            for (FullDrinkView b : drinks3rd) {
                if (a.getId().equals(b.getId())) {
                    return true;
                }
            }
            return false;
        });
    }


}
