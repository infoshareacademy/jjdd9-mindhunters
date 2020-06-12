package com.infoshareacademy.service;

import com.infoshareacademy.domain.User;
import com.infoshareacademy.repository.UserRepositoryBean;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class UserService {

    @EJB
    private UserRepositoryBean userRepositoryBean;

    public void save(User user) {
        userRepositoryBean.save(user);
    }

    public User findByEmail(String email) {
        return userRepositoryBean.findByEmail(email).orElse(null);
    }

    public List<User> findAll() {
        return userRepositoryBean.findAll();
    }

}