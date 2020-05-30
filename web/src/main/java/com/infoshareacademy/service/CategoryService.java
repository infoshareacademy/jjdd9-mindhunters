package com.infoshareacademy.service;

import com.infoshareacademy.domain.Category;
import com.infoshareacademy.domain.dto.CategoryView;
import com.infoshareacademy.repository.CategoryRepositoryBean;
import com.infoshareacademy.service.mapper.CategoryMapper;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class CategoryService {

    @EJB
    CategoryRepositoryBean categoryRepositoryBean;

    @Inject
    private CategoryMapper categoryMapper;

    public List<CategoryView> findAllCategories() {
        List<Category> categories = categoryRepositoryBean.findAllNames();
        return categoryMapper.toView(categories);
    }


}
