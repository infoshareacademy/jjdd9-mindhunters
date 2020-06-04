package com.infoshareacademy.service.mapper;

import com.infoshareacademy.domain.Category;
import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.dto.CategoryView;
import com.infoshareacademy.domain.dto.FullDrinkView;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class CategoryMapper {


    public CategoryView toView(Category category) {
        CategoryView categoryView = new CategoryView();
        categoryView.setId(category.getId());
        categoryView.setName(category.getName());
        return categoryView;
    }

    public List<CategoryView> toView(List<Category> categories) {
        List<CategoryView> categoryViews = new ArrayList<>();
        for (Category category : categories) {
            categoryViews.add(toView(category));
        }
        return categoryViews;
    }


}
