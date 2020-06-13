package com.infoshareacademy.service.mapper;

import com.infoshareacademy.domain.User;
import com.infoshareacademy.domain.dto.UserView;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class UserMapper {

@Inject
private RoleMapper roleMapper;


    public UserView toView(User user) {
        UserView userView = new UserView();
        userView.setId(user.getId());
        userView.setName(user.getName());
        userView.setEmail(user.getEmail());
        userView.setRole(roleMapper.toView(user.getRole()).getName());
        return userView;
    }
}
