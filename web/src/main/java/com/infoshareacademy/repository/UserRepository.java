package com.infoshareacademy.repository;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.User;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

@Local
public interface UserRepository {


    Optional<User> findUserById(Long id);

    void save(User user);

    User update(User user);

    Optional<User> findByEmail(String email);

    List<User> findAll();

    List<Drink> findFavouritesList(String email, int startPosition, int endPosition);

    int countPagesFindFavouritesList(String email);

}
