package com.infoshareacademy.service.mapper;

import com.infoshareacademy.domain.Category;
import com.infoshareacademy.domain.dto.CategoryView;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class CategoryMapper {

    public CategoryView toView(Category category) {
        CategoryView categoryView = new CategoryView();
        categoryView.setId(category.getId());
        categoryView.setName(category.getName());
        return categoryView;
    }

}
