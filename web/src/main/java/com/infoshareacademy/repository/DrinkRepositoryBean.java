package com.infoshareacademy.repository;

import com.infoshareacademy.domain.Drink;

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

    public List<Drink> findAllDrinks() {
        Query query = entityManager.createNamedQuery("Drink.findAll");
        return query.getResultList();
    }

    public List<Drink> findAllDrinksByCategories(List<String> category) {
        Query query = entityManager.createNamedQuery("Drink.findAllByCategories");

        query.setParameter("category", category);
        return query.getResultList();
    }

    public List<Drink> paginationDrinkList(int pageNumber) {
        Query query = entityManager.createQuery("select d from Drink d");

        int pageSize = 4;
        query.setFirstResult((pageNumber-1) * pageSize);
        query.setMaxResults(pageSize);

       return  query.getResultList();

    }


}
