package com.infoshareacademy.repository;

import com.infoshareacademy.domain.DrinkIngredient;
import com.infoshareacademy.domain.Ingredient;
import com.infoshareacademy.domain.Measure;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class DrinkIngredientRepositoryBean {

    @PersistenceContext
    EntityManager entityManager;

    public Ingredient getIngredientByName(String name) {
        // Szukamy po nazwie i wybieramy pierwszą kategorię
        // Jak nie ma to null;
        Query qry = entityManager.createNamedQuery("Ingredient.getByName") ;
        qry.setParameter("name", name);
        List<Ingredient> resultList = qry.getResultList();
        return resultList.size()==0 ? null : resultList.get(0);
    }

    public Measure getMeasureByName(String quantity) {
        // Szukamy po nazwie i wybieramy pierwszą kategorię
        // Jak nie ma to null;
        Query qry = entityManager.createNamedQuery("Measure.getByQuantity") ;
        qry.setParameter("quantity", quantity);
        List<Measure> resultList = qry.getResultList();
        return resultList.size()==0 ? null : resultList.get(0);
    }

    public void save(DrinkIngredient drinkIngredient) {
        entityManager.persist(drinkIngredient);
    }
}
