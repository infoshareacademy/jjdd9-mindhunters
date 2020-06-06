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

    public List<Drink> findByCategories(List<Long> category, int pageNumber) {
        Query query = entityManager.createNamedQuery("Drink.findDrinksByCategories");

        query.setFirstResult((pageNumber - 1) * PAGE_SIZE);
        query.setMaxResults(PAGE_SIZE);

        query.setParameter("category", category);
        return query.getResultList();
    }

    public int maxPageNumberByCategories(List<Long> category) {
        Query query = entityManager.createNamedQuery("Drink.findDrinksByCategories.count");

        query.setParameter("category", category);
        String querySize = query.getSingleResult().toString();
        int maxPageNumber = (int) Math.ceil((Double.valueOf(querySize) / PAGE_SIZE));
        return maxPageNumber;
    }

    public List<Drink> findAllDrinks(int pageNumber) {
        Query query = entityManager.createNamedQuery("Drink.findAll");
        query.setFirstResult((pageNumber - 1) * PAGE_SIZE);
        query.setMaxResults(PAGE_SIZE);

        return query.getResultList();

    }

    public int maxPageNumberFindAll() {
        Query query = entityManager.createQuery("select count (d) from Drink d");

        String querySize = query.getSingleResult().toString();
        int maxPageNumber = (int) Math.ceil((Double.valueOf(querySize) / PAGE_SIZE));
        return maxPageNumber;

    }

    public List<Drink> findByAlcoholStatus(List<String> alcoholStatus, int pageNumber) {

        Query query = entityManager.createNamedQuery("Drink.findDrinksByAlcoholStatus");

        query.setFirstResult((pageNumber - 1) * PAGE_SIZE);
        query.setMaxResults(PAGE_SIZE);

        query.setParameter("alcoholStatus", alcoholStatus);
        return query.getResultList();


    }

    public int maxPageNumberByAlcoholStatus(List<String> alcoholStatus) {
        Query query = entityManager.createNamedQuery("Drink.findDrinksByAlcoholStatus.count");

        query.setParameter("alcoholStatus", alcoholStatus);
        String querySize = query.getSingleResult().toString();
        int maxPageNumber = (int) Math.ceil((Double.valueOf(querySize) / PAGE_SIZE));
        return maxPageNumber;

    }

    public List<Drink> findByCategoriesAndAlcoholStatus(List<Long> category, List<String> alcoholStatus, int pageNumber) {

        Query categoryQuery = entityManager.createNamedQuery("Drink.findByCategoriesAndAlcoholStatus");

        categoryQuery.setFirstResult((pageNumber - 1) * PAGE_SIZE);
        categoryQuery.setMaxResults(PAGE_SIZE);

        categoryQuery.setParameter("alcoholStatus", alcoholStatus).setParameter("category", category);

        return categoryQuery.getResultList();


    }

    public int maxPageNumberByCategoriesAndAlcoholStatus(List<Long> category, List<String> alcoholStatus) {

        Query query = entityManager.createNamedQuery("Drink.findByCategoriesAndAlcoholStatus.count");

        query.setParameter("alcoholStatus", alcoholStatus).setParameter("category", category);
        String querySize = query.getSingleResult().toString();
        int maxPageNumber = (int) Math.ceil((Double.valueOf(querySize) / PAGE_SIZE));
        return maxPageNumber;

    }


}
