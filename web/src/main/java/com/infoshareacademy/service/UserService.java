package com.infoshareacademy.service;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.User;
import com.infoshareacademy.repository.DrinkRepository;
import com.infoshareacademy.repository.UserRepository;
import com.infoshareacademy.service.mapper.FullDrinkMapper;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;


@Stateless
public class UserService {

    private final Long userId1 = 1L;

    @EJB
    private UserRepository userRepository;

    @EJB
    private DrinkRepository drinkRepository;

    @Inject
    private FullDrinkMapper drinkMapper;


    public void saveFavourite(String userId,String drinkId) {

        Drink drink = drinkRepository.findDrinkById(Long.parseLong(drinkId));
        User user = userRepository.findUserById(Long.parseLong(userId));

        userRepository.saveFavourite(user, drink);

    }


}
