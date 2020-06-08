package com.infoshareacademy.mapper;

import com.infoshareacademy.domain.Category;
import com.infoshareacademy.jsonSupport.CategoryJson;
import com.infoshareacademy.service.CategoryService;

import javax.ejb.EJB;
import javax.ejb.Stateless;

//zmiana z stateless
@Stateless
public class CategoryMapper {

    @EJB
    private CategoryService categoryService;

    public Category toEntity(CategoryJson categoryJson) {
        Category category = categoryService.getOrCreate(categoryJson.getCategoryName());
        return category;
    }
}
