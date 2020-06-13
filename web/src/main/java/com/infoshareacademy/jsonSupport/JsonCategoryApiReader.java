package com.infoshareacademy.jsonSupport;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.enterprise.context.RequestScoped;
import java.io.IOException;
import java.util.List;
@RequestScoped
public class JsonCategoryApiReader {

    private JsonCategoryApiReader() {
    }

    public static List<CategoryJson> jsonCategoryReader(String jsonCat) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        JsonNode jsonNode = mapper.readTree(jsonCat);


        List<CategoryJson> categoryJsonList = (List<CategoryJson>) mapper.readValue(jsonNode.get("drinks").toString(),
                new TypeReference<List<CategoryJson>>() {
                });

        return categoryJsonList;
    }
}
