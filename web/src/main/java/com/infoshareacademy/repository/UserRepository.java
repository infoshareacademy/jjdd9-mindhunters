package com.infoshareacademy.repository;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.User;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public interface UserRepository {

    List<User> findAll();

    User findUserById(Long id);

    void saveFavourite(User user, Drink drink);


}
