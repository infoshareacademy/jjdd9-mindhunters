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

    @Override
    public void saveFavourite(User user, Drink drink) {

        List<Drink> drinks = user.getDrinks();

        if (!drinks.contains(drink)){
            drinks.add(drink);
        }

    }


}
