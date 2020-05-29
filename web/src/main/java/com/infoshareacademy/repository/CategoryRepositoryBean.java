package com.infoshareacademy.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class CategoryRepositoryBean {

    @PersistenceContext
    EntityManager entityManager;

    public List<String> findAllNames() {
        Query query = entityManager.createNamedQuery("Category.findAllNames");
        return query.getResultList();
    }
}
