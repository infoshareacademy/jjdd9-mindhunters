package com.infoshareacademy.repository;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface UserRepository {

    List<User> findAll();

    User findUserById(Long id);

    List<Drink> findFavouritesList(Long id, int startPosition, int endPosition);

    int countPagesFindFavouritesList(Long userId);

}
