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
    private EntityManager entityManager;

    public List<Category> findAllNames() {
        Query query = entityManager.createNamedQuery("Category.findAllCategories");
        return query.getResultList();
    }
}
