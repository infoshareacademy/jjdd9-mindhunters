package com.infoshareacademy.domain.dto;

import com.infoshareacademy.domain.Drink;

import java.util.ArrayList;
import java.util.List;

public class UserView {

    private Long id;

    private String name;

    private String email;

    private List<Drink> drinks = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }
}