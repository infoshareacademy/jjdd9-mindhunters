package com.infoshareacademy.repository;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.Ingredient;
import com.infoshareacademy.service.DrinkService;
import org.hibernate.jpa.QueryHints;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class DrinkRepositoryBean implements DrinkRepository {


    private static final Integer LIVE_SEARCH_LIMIT = 10;

    @PersistenceContext
    private EntityManager entityManager;

    @EJB
    private DrinkService drinkService;

    @Override
    public Drink findDrinkById(Long drinkId) {

        return entityManager.find(Drink.class, drinkId);
    }


    @Override
    public List<Drink> findDrinksByName(String partialDrinkName, int startPosition, int endPosition) {
        Query query = entityManager.createNamedQuery("Drink.findDrinkByPartialName");
        query.setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false);

        query.setFirstResult(startPosition);
        query.setMaxResults(endPosition);

        query.setParameter("partialDrinkName", "%" + partialDrinkName + "%");
        return query.getResultList();
    }


    @Override
    public int countPagesByName(String partialDrinkName) {
        Query query = entityManager.createNamedQuery("Drink.countDrinksByPartialName");
        query.setParameter("partialDrinkName", "%" + partialDrinkName + "%");
        String querySize = query.getSingleResult().toString();

        int maxPageNumber = drinkService.getMaxPageNumber(querySize);

        return maxPageNumber;

    }

    @Override
    public List<Drink> liveSearchDrinksByName(String partialDrinkName) {
        Query query = entityManager.createNamedQuery("Drink.findDrinkByPartialName");
        query.setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false);

        query.setMaxResults(LIVE_SEARCH_LIMIT);
        query.setParameter("partialDrinkName", "%" + partialDrinkName + "%");
        return query.getResultList();
    }


    @Override
    public List<Drink> findByIngredients(List<Ingredient> ingredients, int startPosition, int endPosition) {
        Query query = entityManager.createNamedQuery("Drink.findByIngredients");
        query.setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false);

        query.setFirstResult(startPosition);
        query.setMaxResults(endPosition);

        query.setParameter("ingredients", ingredients);
        return query.getResultList();
    }

    @Override
    public int countPagesByIngredients(List<Ingredient> ingredients) {
        Query query = entityManager.createNamedQuery("Drink.countDrinksByIngredients");
        query.setParameter("ingredients", ingredients);
        String querySize = query.getSingleResult().toString();

        int maxPageNumber = drinkService.getMaxPageNumber(querySize);

        return maxPageNumber;
    }



    @Override
    public List<Drink> findAllDrinks(int startPosition, int endPosition) {
        Query query = entityManager.createNamedQuery("Drink.findAll");

        query.setFirstResult(startPosition);
        query.setMaxResults(endPosition);

        return query.getResultList();

    }

    @Override
    public int countPagesFindAll() {
        Query query = entityManager.createNamedQuery("Drink.countFindAll");

        String querySize = query.getSingleResult().toString();
        int maxPageNumber = drinkService.getMaxPageNumber(querySize);
        return maxPageNumber;

    }


}
