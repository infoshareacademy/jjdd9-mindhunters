package com.infoshareacademy.repository;

import com.infoshareacademy.domain.Statistics;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class StatisticsRepositoryBean {


    @PersistenceContext
    private EntityManager entityManager;


    public void addToStatistics(Statistics statistics) {

        entityManager.persist(statistics);

    }


}
