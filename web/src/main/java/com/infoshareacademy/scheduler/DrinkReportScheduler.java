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
import java.io.IOException;
import java.util.*;

@Singleton
@Startup
public class DrinkReportScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DrinkReportScheduler.class.getName());

    private Long scheduleCounter = 0L;
    private List<FullDrinkView> drinks1st = new ArrayList<>();
    private List<FullDrinkView> drinks3rd = new ArrayList<>();
    private final String dest = getEmailProperties("dest");


    @EJB
    private DrinkService drinkService;

    @EJB
    private EmailSender emailSender;

    @EJB
    private EmailBuildStrategy emailBuildStrategy;


    @Schedule(hour = "*", minute = "2/15")
    public void checkRecipesForApproval() {

        if (scheduleCounter % 2 == 0) {

            drinks1st = drinkService.findDrinksToApprove();

            if (recipeAwaiting3scheduleCalls()) {

                emailSender.sendEmail(emailBuildStrategy.createContent(drinks1st), dest);
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

    private String getEmailProperties(String key) {
        Properties properties = new Properties();
        try {
            properties.load(Objects.requireNonNull(Thread.currentThread()
                    .getContextClassLoader().getResource("email.properties"))
                    .openStream());
        } catch (IOException e) {
            LOGGER.error("Error during loading email properties, {}", e.getMessage());
        }
        return properties.getProperty(key);
    }

}
