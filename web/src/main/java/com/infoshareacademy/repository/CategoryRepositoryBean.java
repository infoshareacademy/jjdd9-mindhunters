package com.infoshareacademy.repository;

import com.infoshareacademy.domain.Category;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class CategoryRepositoryBean {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Category> findAllNames() {
        Query query = entityManager.createNamedQuery("Category.findAll");
        return query.getResultList();
    }

    public Category getByName(String name) {
        Query qry = entityManager.createNamedQuery("Category.getByName");
        qry.setParameter("name", name);
        List<Category> resultList = qry.getResultList();
        return resultList.size() == 0 ? null : resultList.get(0);
    }

    public void save(Category category) {
        entityManager.persist(category);
    }
}
