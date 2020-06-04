package com.infoshareacademy.repository;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.Ingredient;
import org.hibernate.jpa.QueryHints;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Array;
import java.util.List;

@Stateless
public class DrinkRepositoryBean implements DrinkRepository {

    @PersistenceContext
    EntityManager entityManager;

    public final static Integer PAGE_SIZE = 5;

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

    public List<Drink> paginatedDrinksByCategories(List<Long> category, int pageNumber) {
        Query query = entityManager.createNamedQuery("Drink.findDrinksByCategories");

        query.setFirstResult((pageNumber - 1) * PAGE_SIZE);
        query.setMaxResults(PAGE_SIZE);

        query.setParameter("category", category);
        return query.getResultList();
    }

    public int maxPageNumberDrinksByCategories(List<Long> category) {
        Query query = entityManager.createNamedQuery("Drink.findDrinksByCategories.count");



        query.setParameter("category", category);
        String querySize = query.getSingleResult().toString();
        int maxPageNumber = (int) Math.ceil((Double.valueOf(querySize) / PAGE_SIZE));
        return maxPageNumber;
    }

    public List paginatedDrinksList(int pageNumber) {
        Query query = entityManager.createNamedQuery("Drink.findAll");
        query.setFirstResult((pageNumber - 1) * PAGE_SIZE);
        query.setMaxResults(PAGE_SIZE);

        return query.getResultList();

    }

    public int maxPageNumberDrinkList() {
        Query query = entityManager.createQuery("select count (d) from Drink d");
        String querySize = query.getSingleResult().toString();
        int maxPageNumber = (int) Math.ceil((Double.valueOf(querySize) / PAGE_SIZE));
        return maxPageNumber;

    }


}
