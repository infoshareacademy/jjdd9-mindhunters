package com.infoshareacademy.service;

import com.infoshareacademy.domain.User;
import com.infoshareacademy.domain.dto.UserGoogleView;
import com.infoshareacademy.domain.dto.UserView;
import com.infoshareacademy.repository.RoleRepositoryBean;
import com.infoshareacademy.repository.UserRepositoryBean;
import com.infoshareacademy.service.mapper.UserMapper;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class UserService {

    @EJB
    private UserRepositoryBean userRepositoryBean;

    @EJB
    private RoleRepositoryBean roleRepositoryBean;

    @Inject
    private UserMapper userMapper;

    public void save(User user) {
        userRepositoryBean.save(user);
    }

    public User findByEmail(String email) {
        return userRepositoryBean.findByEmail(email).orElse(null);
    }

    public UserView getUserById(Long userId) {
        User foundUser = userRepositoryBean.findUserById(userId).get();
        if (foundUser == null) {
            return null;
        }
        return userMapper.toView(foundUser);
    }

    public List<User> findAll() {
        return userRepositoryBean.findAll();
    }

    public User create(UserGoogleView userGoogleView) {
        User user = new User();
        user.setName(userGoogleView.getName());
        user.setEmail(userGoogleView.getEmail());
        user.setRole(roleRepositoryBean.findByRoleName("USER").get());
        save(user);
        return user;
    }

    public UserView login(UserGoogleView userGoogleView) {
        User user = userRepositoryBean.findByEmail(userGoogleView.getEmail()).orElseGet(() -> create(userGoogleView));
        return userMapper.toView(user);
    }

}