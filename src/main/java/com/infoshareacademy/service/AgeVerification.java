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
        return false;
    }
}

