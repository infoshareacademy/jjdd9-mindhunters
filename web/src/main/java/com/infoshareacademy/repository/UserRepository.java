package com.infoshareacademy.repository;

import com.infoshareacademy.domain.Drink;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UserRepository implements UserRepositoryBean{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void saveFavourite(Drink drink) {

        List<String> favouriteDrinks = new ArrayList<>();

        entityManager.persist(favouriteDrinks.add(drink.getDrinkId()));
    }




}
