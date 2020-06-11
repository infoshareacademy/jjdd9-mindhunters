package com.infoshareacademy.repository;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class UserRepositoryBean implements UserRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findUserById(Long id) {

        return entityManager.find(User.class, id);
    }


}
