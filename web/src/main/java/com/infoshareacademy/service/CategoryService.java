package com.infoshareacademy.service;

import com.infoshareacademy.repository.CategoryRepositoryBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class CategoryService {

    @Inject
    CategoryRepositoryBean categoryRepositoryBean;

    public List<String> findAllNames() {
        return categoryRepositoryBean.findAllNames();
    }


}
