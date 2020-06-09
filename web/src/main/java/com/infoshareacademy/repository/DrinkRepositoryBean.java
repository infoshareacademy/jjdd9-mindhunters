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
    private EntityManager entityManager;

    public final static Integer PAGE_SIZE = 5;
    public final static Integer LIVE_SEARCH_LIMIT = 10;

    public Drink findDrinkById(Long drinkId) {

        return entityManager.find(Drink.class, drinkId);
    }

    @Override
    public List<Drink> paginatedFindDrinksByName(String partialDrinkName, int pageNumber) {
        Query drinkQuery = entityManager.createNamedQuery("Drink.findDrinkByPartialName");
        drinkQuery.setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false);

        drinkQuery.setFirstResult((pageNumber - 1) * PAGE_SIZE);
        drinkQuery.setMaxResults(PAGE_SIZE);

        drinkQuery.setParameter("partialDrinkName", "%" + partialDrinkName + "%");
        return drinkQuery.getResultList();
    }

    @Override
    public List<Drink> liveSearchDrinksByName(String partialDrinkName) {
        Query drinkQuery = entityManager.createNamedQuery("Drink.findDrinkByPartialName");
        drinkQuery.setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false);
        drinkQuery.setMaxResults(LIVE_SEARCH_LIMIT);
        drinkQuery.setParameter("partialDrinkName", "%" + partialDrinkName + "%");
        return drinkQuery.getResultList();
    }


    @Override
    public int maxPageNumberDrinksByName(String partialDrinkName) {
        Query drinkQuery = entityManager.createNamedQuery("Drink.countDrinksByPartialName");
        drinkQuery.setParameter("partialDrinkName", "%" + partialDrinkName + "%");
        Long querySize = (Long) drinkQuery.getSingleResult();
        int maxPageNumber = (int) Math.ceil((Double.valueOf(querySize) / PAGE_SIZE));
        return maxPageNumber;

    }

    public List<Drink> paginatedFindDrinkByIngredients(List<Ingredient> ingredients, int pageNumber) {
        Query drinkQuery = entityManager.createNamedQuery("Drink.findDrinkByIngredients");
        drinkQuery.setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false);

        drinkQuery.setFirstResult((pageNumber - 1) * PAGE_SIZE);
        drinkQuery.setMaxResults(PAGE_SIZE);

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

    @Override
    public int maxPageNumberDrinksByIngredients(List<Ingredient> ingredients) {
        Query query = entityManager.createNamedQuery("Drink.countDrinksByIngredients");


        query.setParameter("ingredients", ingredients);
        Long querySize = (Long) query.getSingleResult();
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
