package com.infoshareacademy.repository;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.User;
import com.infoshareacademy.service.UserService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class UserRepositoryBean implements UserRepository {

    @PersistenceContext
    EntityManager entityManager;

    @EJB
    private UserService userService;

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findUserById(Long id) {

        return entityManager.find(User.class, id);
    }

    @Override
    public List<Drink> findFavouritesList(Long id, int startPosition, int endPosition) {
        Query query = entityManager.createNamedQuery("User.findFavouritesList");
        query.setParameter("id", id);

        query.setFirstResult(startPosition);
        query.setMaxResults(endPosition);

        return query.getResultList();
    }


    @Override
    public int countPagesFindFavouritesList(Long userId) {
        Query query = entityManager.createNamedQuery("User.countFindFavouritesList");
        query.setParameter("id", userId);
        String querySize = query.getSingleResult().toString();

        int maxPageNumber = userService.getMaxPageNumber(querySize);
        return maxPageNumber;

    }
}
