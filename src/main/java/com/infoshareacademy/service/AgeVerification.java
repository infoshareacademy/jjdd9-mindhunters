package com.infoshareacademy.service;

import com.infoshareacademy.utilities.PropertiesUtilities;

import java.time.LocalDateTime;

public class AgeVerification {
    public void saveAdultSessionTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        PropertiesUtilities propertiesUtilities = new PropertiesUtilities();
        propertiesUtilities.setProperties("adult.session.timestamp", now.toString());
    }

    public boolean isAdultSessionActive() {
        PropertiesUtilities propertiesUtilities = new PropertiesUtilities();
        String minutesString = propertiesUtilities.getProperty("adult.session.minutes");
        Long minutes = Long.parseLong(minutesString);
        String timestampString = propertiesUtilities.getProperty("adult.session.timestamp");
        if(timestampString == null){
            return false;
        }
        LocalDateTime timestamp = LocalDateTime.parse(timestampString);
        LocalDateTime endOfAdultSession = timestamp.plusMinutes(minutes);
        LocalDateTime now = LocalDateTime.now();
        return endOfAdultSession.isAfter(now);
    }
}
