package com.infoshareacademy.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonWriter {

    private JsonWriter() {
    }

    public static <T> void writeAllToJson(T obj, String jsonFilePath) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(jsonFilePath), obj);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}