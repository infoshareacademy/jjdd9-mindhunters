package com.infoshareacademy.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonWriter {

    public static <T> void writeJsonToFile(T obj, String jsonFilePath) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(jsonFilePath), obj);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
