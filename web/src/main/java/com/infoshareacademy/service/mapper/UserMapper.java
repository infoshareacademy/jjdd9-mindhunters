package com.infoshareacademy.service.mapper;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.User;
import com.infoshareacademy.domain.dto.FullDrinkView;
import com.infoshareacademy.domain.dto.UserView;

import javax.enterprise.context.RequestScoped;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class UserMapper {

    public UserView toView(User user) {
        UserView userView = new UserView();

        userView.setName(user.getName());
        userView.setId(user.getId());
        userView.setDrinks(user.getDrinks());
        userView.setEmail(user.getEmail());

        return userView;

    }

    public List<UserView> toView(List<User> users) {
        List<UserView> userViews = new ArrayList<>();
        for (User user : users) {
            userViews.add(toView(user));
        }
        return userViews;
    }

}
