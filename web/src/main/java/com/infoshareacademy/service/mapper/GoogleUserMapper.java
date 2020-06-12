package com.infoshareacademy.service.mapper;


import com.google.api.services.oauth2.model.Userinfoplus;
import com.infoshareacademy.domain.dto.UserGoogleView;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class GoogleUserMapper {


    public UserGoogleView mapGoogleResponseToUserRequest(Userinfoplus info) {
        UserGoogleView userView = new UserGoogleView();
        userView.setEmail(info.getEmail());
        userView.setName(info.getName());
        return userView;
    }

}
