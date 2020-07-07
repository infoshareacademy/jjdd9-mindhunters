package com.infoshareacademy.repository;

import com.infoshareacademy.domain.Rating;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Optional;

@Stateless
public class RatingRepositoryBean implements RatingRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Rating> findByDrinkId(Long drinkId) {
        Query query = entityManager.createNamedQuery("Rating.findByDrinkId");

        query.setParameter("drinkId", drinkId);

        return query.getResultList().stream().findFirst();
    }


    @Override
    public Rating updateRating(Long drinkId, Double vote) {

        final Rating rating = findByDrinkId(drinkId).get();

        final Double newSum = rating.getSum() + vote;
        final long newNumberOfRating = rating.getNumberOfRatings() + 1;

        rating.setSum(newSum);
        rating.setNumberOfRatings(newNumberOfRating);

        return entityManager.merge(rating);
    }

    @Override
    public void saveRating(Rating rating) {

        entityManager.persist(rating);
    }

    public void removeRating(Long drinkId) {
        final Rating rating = findByDrinkId(drinkId).get();

        entityManager.remove(rating);
    }
}
