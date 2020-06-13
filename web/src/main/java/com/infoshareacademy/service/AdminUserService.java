package com.infoshareacademy.service;


import com.infoshareacademy.domain.User;
import com.infoshareacademy.domain.dto.UserView;
import com.infoshareacademy.repository.RoleRepositoryBean;
import com.infoshareacademy.repository.UserRepositoryBean;
import com.infoshareacademy.service.mapper.UserMapper;
import com.infoshareacademy.service.validator.UserInputValidator;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class AdminUserService {

    @EJB
    private UserRepositoryBean userRepositoryBean;

    @EJB
    private RoleRepositoryBean roleRepositoryBean;

    @Inject
    private UserMapper userMapper;

    @Inject
    private UserInputValidator userInputValidator;


    public boolean setAdminRole(String userId) {

        final Long longId = userInputValidator.stringToLongConverter(userId);

        if (longId < 0) {
            return false;
        }

        User userById = userRepositoryBean.findUserById(longId).get();

        if("SUPER_ADMIN".equalsIgnoreCase(userById.getRole().getName())){
            return false;
        }
        userById.setRole(roleRepositoryBean.findByRoleName(userById.getRole().getName()).get());
        User adminUser = userRepositoryBean.update(userById);

        return adminUser.getRole().getName().equalsIgnoreCase("ADMIN");
    }

    public List<UserView> showUsers() {

        return userRepositoryBean.findAll()
                .stream()
                .map(user -> userMapper.toView(user))
                .collect(Collectors.toList());
    }


}
