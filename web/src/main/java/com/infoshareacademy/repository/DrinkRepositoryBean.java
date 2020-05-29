package com.infoshareacademy.repository;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.Ingredient;
import org.hibernate.jpa.QueryHints;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class DrinkRepositoryBean implements DrinkRepository {

    @PersistenceContext
    EntityManager entityManager;

    public Drink findDrinkById(Long drinkId) {

        return entityManager.find(Drink.class, drinkId);
    }

    public List<Drink> findDrinksByName(String partialDrinkName) {
        Query drinkQuery = entityManager.createNamedQuery("Drink.findDrinkByPartialName");
        drinkQuery.setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false);
        drinkQuery.setParameter("partialDrinkName", "%" + partialDrinkName + "%");
        return drinkQuery.getResultList();
    }

    public List<Drink> findDrinkByIngredients(List<Ingredient> ingredients) {
        Query drinkQuery = entityManager.createNamedQuery("Drink.findDrinkByIngredients");
        drinkQuery.setParameter("ingredients", ingredients);
        return drinkQuery.getResultList();
    }


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
