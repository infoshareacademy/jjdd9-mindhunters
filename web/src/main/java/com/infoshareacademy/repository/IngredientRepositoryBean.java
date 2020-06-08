package com.infoshareacademy.repository;

import com.infoshareacademy.domain.Ingredient;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class IngredientRepositoryBean {

    @PersistenceContext
    EntityManager entityManager;

//    public List<String> findAllNames() {
//        Query query = entityManager.createNamedQuery("Category.findAllNames");
//        return query.getResultList();
//    }

    public Ingredient getByName(String name) {
        // Szukamy po nazwie i wybieramy pierwszą kategorię
        // Jak nie ma to null;
        Query qry = entityManager.createNamedQuery("Ingredient.getByName") ;
        qry.setParameter("name", name);
        List<Ingredient> resultList = qry.getResultList();
        return resultList.size()==0 ? null : resultList.get(0);
    }

    public void save(Ingredient ingredient) {
        entityManager.persist(ingredient);
    }
}
