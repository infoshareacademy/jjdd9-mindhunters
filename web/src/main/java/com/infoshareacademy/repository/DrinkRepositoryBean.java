package com.infoshareacademy.repository;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.Ingredient;
import com.infoshareacademy.service.DrinkService;
import org.hibernate.jpa.QueryHints;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class DrinkRepositoryBean implements DrinkRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    DrinkService drinkService;

    public final static Integer PAGE_SIZE = 5;

    public Drink findDrinkById(Long drinkId) {

        return entityManager.find(Drink.class, drinkId);
    }

    public List<Drink> findAllDrinks(int startPosition, int endPosition) {
        Query query = entityManager.createNamedQuery("Drink.findAll");

        query.setFirstResult(startPosition);
        query.setMaxResults(endPosition);

        return query.getResultList();

    }

    public int countPagesFindAll() {
        Query query = entityManager.createNamedQuery("Drink.countFindAll");

        String querySize = query.getSingleResult().toString();
        int maxPageNumber = drinkService.getMaxPageNumber(querySize);
        return maxPageNumber;

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


    public List<Drink> findByCategories(List<Long> category, int startPosition, int endPosition) {
        Query query = entityManager.createNamedQuery("Drink.findDrinksByCategories");

        query.setFirstResult(startPosition);
        query.setMaxResults(endPosition);

        query.setParameter("category", category);
        return query.getResultList();
    }

    public int countPagesByCategories(List<Long> category) {
        Query query = entityManager.createNamedQuery("Drink.CountDrinksByCategories");

        query.setParameter("category", category);
        String querySize = query.getSingleResult().toString();
        int maxPageNumber = drinkService.getMaxPageNumber(querySize);
        return maxPageNumber;
    }

    public List<Drink> findByAlcoholStatus(List<String> alcoholStatus, int startPosition, int endPosition) {
        Query query = entityManager.createNamedQuery("Drink.findDrinksByAlcoholStatus");

        query.setFirstResult(startPosition);
        query.setMaxResults(endPosition);

        query.setParameter("alcoholStatus", alcoholStatus);
        return query.getResultList();

    }

    public int countPagesByAlcoholStatus(List<String> alcoholStatus) {
        Query query = entityManager.createNamedQuery("Drink.countDrinksByAlcoholStatus");

        query.setParameter("alcoholStatus", alcoholStatus);
        String querySize = query.getSingleResult().toString();

        int maxPageNumber = drinkService.getMaxPageNumber(querySize);
        return maxPageNumber;

    }



    public List<Drink> findByCategoriesAndAlcoholStatus(List<Long> category, List<String> alcoholStatus,
                                                        int startPosition, int endPosition) {
        Query query = entityManager.createNamedQuery("Drink.findByCategoriesAndAlcoholStatus");

        query.setFirstResult(startPosition);
        query.setMaxResults(endPosition);

        query.setParameter("alcoholStatus", alcoholStatus).setParameter("category", category);

        return query.getResultList();

    }

    public int countPagesByCategoriesAndAlcoholStatus(List<Long> category, List<String> alcoholStatus) {
        Query query = entityManager.createNamedQuery("Drink.countDrinksByCategoriesAndAlcoholStatus");

        query.setParameter("alcoholStatus", alcoholStatus).setParameter("category", category);
        String querySize = query.getSingleResult().toString();

        int maxPageNumber = drinkService.getMaxPageNumber(querySize);
        return maxPageNumber;

    }




}
