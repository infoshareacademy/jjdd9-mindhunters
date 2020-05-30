package com.infoshareacademy.repository;

import com.infoshareacademy.domain.Category;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
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
