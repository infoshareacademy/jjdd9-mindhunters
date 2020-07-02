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
}
