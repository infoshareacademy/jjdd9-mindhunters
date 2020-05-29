package com.infoshareacademy.repository;

import com.infoshareacademy.domain.Ingredient;
import org.hibernate.jpa.QueryHints;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class IngredientRepositoryBean implements IngredientRepository {

    @PersistenceContext
    EntityManager entityManager;

    public List<Ingredient> findIngredientsByName(String partialIngredientName) {
        Query ingredientQuery = entityManager.createNamedQuery("Ingredient.findIngredientsByPartialName");
        ingredientQuery.setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false);
        ingredientQuery.setParameter("partialIngredientName", "%" + partialIngredientName + "%");
        return ingredientQuery.getResultList();
    }


}
