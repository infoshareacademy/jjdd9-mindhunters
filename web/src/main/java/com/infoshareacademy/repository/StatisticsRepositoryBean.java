package com.infoshareacademy.repository;

import com.infoshareacademy.domain.Statistics;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class StatisticsRepositoryBean {


    @PersistenceContext
    private EntityManager entityManager;


    public void addToStatistics(Statistics statistics) {

        entityManager.persist(statistics);

    }

    public List getTopDrinks(){
        Query query = entityManager.createNamedQuery("Statistics.getTop10Drinks");
        query.setMaxResults(10);
        return query.getResultList();
    }


    public List getCategoriesStats() {
        Query query = entityManager.createNamedQuery("Statistics.getCategoriesStats");
        List test = query.getResultList();
        return test;
    }
}
