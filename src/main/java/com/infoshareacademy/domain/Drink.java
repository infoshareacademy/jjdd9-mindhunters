package com.infoshareacademy.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.infoshareacademy.service.JsonDrinkDeserializer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties({"strDrinkAlternate", "strDrinkES", "strDrinkDE", "strDrinkFR",
        "strDrinkZH-HANS", "strDrinkZH-HANT", "strTags", "strVideo", "strIBA",
        "strInstructionsES", "strInstructionsDE", "strInstructionsFR", "strInstructionsZH-HANS",
        "strInstructionsZH-HANT", "strDrinkThumb", "strCreativeCommonsConfirmed"})

@JsonDeserialize(using = JsonDrinkDeserializer.class)
public class Drink {

    @JsonProperty("idDrink")
    private String drinkId;
    @JsonProperty("strDrink")
    private String drinkName;
    @JsonProperty("strCategory")
    private String categoryName;
    @JsonProperty("strAlcoholic")
    private String alcoholStatus;
    @JsonProperty("strInstructions")
    private String recipe;
    @JsonProperty("strDrinkThumb")
    private String imageUrl;
    private List<Ingredient> ingredients;
    @JsonProperty("dateModified")
    private LocalDateTime modifiedDate;

    public String getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(String drinkId) {
        this.drinkId = drinkId;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getAlcoholStatus() {
        return alcoholStatus;
    }

    public void setAlcoholStatus(String alcoholStatus) {
        this.alcoholStatus = alcoholStatus;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Override
    public String toString() {
        return "Drink{" +
                "drinkId='" + drinkId + '\'' +
                ", drinkName='" + drinkName + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", alcoholStatus='" + alcoholStatus + '\'' +
                ", recipe='" + recipe + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", ingredients=" + ingredients +
                ", modifiedDate='" + modifiedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) +
                "'}";
    }

    public List<String> getIngridientsNamesList() {
        List<String> ingridientsNames = new ArrayList<>();
        for (Ingredient ingredient : ingredients){
            ingridientsNames.add(ingredient.getName());
        }
        return ingridientsNames;
    }
}

