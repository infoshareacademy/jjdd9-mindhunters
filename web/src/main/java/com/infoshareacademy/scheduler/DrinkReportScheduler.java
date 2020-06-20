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

    private Long counter = 0L;
    private List<FullDrinkView> drinks1st = new ArrayList<>();
    private List<FullDrinkView> drinks3rd = new ArrayList<>();


    @EJB
    private DrinkService drinkService;

    @EJB
    private EmailSender emailSender;

    @EJB
    private EmailBuildStrategy emailBuildStrategy;


    @Schedule(hour = "*", minute = "2/1")
    public void checkRecipesForApproval() {
        LOGGER.info("Counter {}", counter);

        if (counter % 2 == 0) {

            drinks1st = drinkService.findDrinksByName("casino", 1);

            if (recipeAwaiting3scheduleCalls()) {

                emailSender.sendEmail(emailBuildStrategy.createContent(drinks1st));
                LOGGER.info("CheckRecipesForApproval - email send");
                counter = 0L;
                drinks3rd = Collections.emptyList();
                return;
            }

            drinks3rd = drinks1st;

        }
        counter++;
        LOGGER.info("CheckRecipesForApproval, every 15 min scheduler");
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
