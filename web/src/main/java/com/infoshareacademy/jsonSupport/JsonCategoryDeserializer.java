package com.infoshareacademy.jsonSupport;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class JsonCategoryDeserializer extends JsonDeserializer<CategoryJson> {

    @Override
    public CategoryJson deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        CategoryJson categoryJson = new CategoryJson();

        JsonNode readValueAsTree = jsonParser.readValueAsTree();

        categoryJson.setCategoryName(readValueAsTree.get("strCategory").asText());

        return categoryJson;
    }
}