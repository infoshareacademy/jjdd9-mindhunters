package com.infoshareacademy.service;

import com.infoshareacademy.domain.Category;
import com.infoshareacademy.repository.CategoryRepositoryBean;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class CategoryService {

    @Inject
    CategoryRepositoryBean categoryRepositoryBean;

    public List<String> findAllNames() {
        return categoryRepositoryBean.findAllNames();
    }

    public Category getOrCreate(String name) {
        Category category = categoryRepositoryBean.getByName(name);
        if (category == null) {
            category = new Category();
            category.setName(name);
            categoryRepositoryBean.save(category);
        }
        return category;
    }
}
