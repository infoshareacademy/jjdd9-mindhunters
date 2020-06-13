package com.infoshareacademy.repository;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.DrinkJson;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Transactional

@Stateless
public class DrinkRepositoryBean {

    @PersistenceContext
    EntityManager entityManager;

    public List<DrinkJson> findAllDrinks() {
        Query query = entityManager.createNamedQuery("Drink.findAll");
        return query.getResultList();
    }

    public List<DrinkJson> findAllDrinksByCategories(List<String> category) {
        Query query = entityManager.createNamedQuery("Drink.findAllByCategories");

        query.setParameter("category", category);
        return query.getResultList();
    }

    public List<DrinkJson> paginationDrinkList(int pageNumber) {
        Query query = entityManager.createQuery("select d from Drink d");

        int pageSize = 4;
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }

    public void save(Drink drink) {
        entityManager.persist(drink);
    }


}
