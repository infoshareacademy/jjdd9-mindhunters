package com.infoshareacademy.service;


import com.infoshareacademy.domain.User;
import com.infoshareacademy.domain.dto.UserView;
import com.infoshareacademy.repository.UserRepositoryBean;
import com.infoshareacademy.service.mapper.UserMapper;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class AdminUserService {

    @EJB
    private UserRepositoryBean userRepositoryBean;

    @Inject
    private UserMapper userMapper;


    public boolean setAdminRole(User user) {
        String role = "ADMIN";
        if("SUPER_ADMIN".equalsIgnoreCase(user.getRole())){
            return false;
        }
        user.setRole(role);
        User adminUser = userRepositoryBean.update(user);

        return adminUser.getRole().equalsIgnoreCase("ADMIN");
    }

    public List<UserView> showUsers() {

        return userRepositoryBean.findAll()
                .stream()
                .map(user -> userMapper.toView(user))
                .collect(Collectors.toList());
    }


}
