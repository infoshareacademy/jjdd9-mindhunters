package com.infoshareacademy.service.mapper;

import com.infoshareacademy.domain.User;
import com.infoshareacademy.domain.dto.UserView;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class UserMapper {


    public UserView mapper(User user) {
        UserView userView = new UserView();
        userView.setId(user.getId());
        userView.setName(user.getName());
        userView.setEmail(user.getEmail());
        userView.setRole(user.getRole());
        return userView;
    }
}
