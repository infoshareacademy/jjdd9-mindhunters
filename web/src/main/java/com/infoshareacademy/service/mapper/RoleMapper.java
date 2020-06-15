package com.infoshareacademy.service.mapper;

import com.infoshareacademy.domain.Role;
import com.infoshareacademy.domain.dto.RoleView;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class RoleMapper {

    public RoleView toView(Role role) {

        RoleView roleView = new RoleView();
        roleView.setId(role.getId());
        roleView.setName(role.getName());
        return roleView;
    }


}
