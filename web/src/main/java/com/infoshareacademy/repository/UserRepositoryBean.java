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
import java.util.Optional;

@Stateless
public class UserRepositoryBean implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @EJB
    private UserService userService;


    @Override
    public Optional<User> findUserById(Long userId) {

        return Optional.ofNullable(entityManager.find(User.class, userId));
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public User update(User user) {

        return entityManager.merge(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Query query = entityManager.createNamedQuery("User.findByEmail");
        query.setParameter("email", email);
        return query.getResultList().stream().findFirst();
    }

    @Override
    public List<User> findAll() {
        Query query = entityManager.createNamedQuery("User.findAll");
        return query.getResultList();
    }

    @Override
    public List<Drink> findFavouritesList(String email, int startPosition, int endPosition) {
        Query query = entityManager.createNamedQuery("User.findFavouritesList");
        query.setParameter("email", email);

        query.setFirstResult(startPosition);
        query.setMaxResults(endPosition);

        return query.getResultList();
    }


    @Override
    public int countPagesFindFavouritesList(String email) {
        Query query = entityManager.createNamedQuery("User.countFindFavouritesList");
        query.setParameter("email", email);
        String querySize = query.getSingleResult().toString();

        int maxPageNumber = userService.getMaxPageNumber(querySize);
        return maxPageNumber;

    }
}