package com.infoshareacademy.service;

import com.infoshareacademy.utilities.PropertiesUtilities;

import java.time.LocalDateTime;

public class AgeVerification {
    private static final String SESSION_LENGTH_KEY = "adult.session.minutes";
    private static final String TIMESTAMP_KEY = "adult.session.timestamp";
    private PropertiesUtilities propertiesUtilities = new PropertiesUtilities();

    public void saveAdultSessionTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        this.propertiesUtilities.setProperties(TIMESTAMP_KEY, now.toString());
    }

    public boolean isAdultSessionActive() {
        String minutesString = this.propertiesUtilities.getProperty(SESSION_LENGTH_KEY);
        Long minutes = Long.parseLong(minutesString);
        String timestampString = this.propertiesUtilities.getProperty(TIMESTAMP_KEY);
        if(timestampString == null){
            return false;
        }
        LocalDateTime timestamp = LocalDateTime.parse(timestampString);
        LocalDateTime endOfAdultSession = timestamp.plusMinutes(minutes);
        LocalDateTime now = LocalDateTime.now();
        return endOfAdultSession.isAfter(now);
    }
}
