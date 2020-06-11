package com.infoshareacademy.repository;

import com.infoshareacademy.domain.Drink;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public interface UserRepositoryBean {

    public void saveFavourite(Drink drink);


}
