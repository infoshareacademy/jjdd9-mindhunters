package com.infoshareacademy.repository;

import com.infoshareacademy.domain.Measure;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class MeasureRepositoryBean {

    @PersistenceContext
    EntityManager entityManager;

    public Measure getByQuantity(String quantity) {
        Query qry = entityManager.createNamedQuery("Measure.getByQuantity");
        qry.setParameter("quantity", quantity);
        List<Measure> resultList = qry.getResultList();
        return resultList.size() == 0 ? null : resultList.get(0);
    }

    public void save(Measure measure) {
        entityManager.persist(measure);
    }
}

