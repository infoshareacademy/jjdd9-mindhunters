package com.infoshareacademy.service;

import javax.enterprise.context.RequestScoped;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class QueryParamBuilder {

    public String buildIngrQuery(List<String> ingredientNamesFiltered) {
        StringBuilder queryIngrBuilder = new StringBuilder();
        queryIngrBuilder.append("search=ingr&ing=");
        queryIngrBuilder.append(ingredientNamesFiltered
                .stream()
                .collect(Collectors.joining("&ing=")));
        return queryIngrBuilder.toString();
    }

    public String buildNameQuery(String partialDrinkName) {
        StringBuilder queryNameBuilder = new StringBuilder();
        queryNameBuilder.append("search=name&");
        return queryNameBuilder.append("name=" + partialDrinkName).toString();
    }
}
