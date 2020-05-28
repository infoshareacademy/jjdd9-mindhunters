package com.infoshareacademy.repository;

import com.infoshareacademy.domain.Drink;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class DrinkRepositoryBean implements DrinkRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Drink> findAll() {
        Query query = entityManager.createNamedQuery("Drink.findAll");
        return query.getResultList();
    }
}
