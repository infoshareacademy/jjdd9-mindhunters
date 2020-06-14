package com.infoshareacademy.service;

import com.infoshareacademy.domain.Category;
import com.infoshareacademy.domain.dto.CategoryView;
import com.infoshareacademy.repository.CategoryRepositoryBean;
import com.infoshareacademy.service.mapper.CategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class CategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryService.class.getName());

    @EJB
    private CategoryRepositoryBean categoryRepositoryBean;

    @Inject
    private CategoryMapper categoryMapper;

    public List<CategoryView> findAllCategories() {
        LOGGER.debug("Return all categories names");
        List<Category> categories = categoryRepositoryBean.findAllNames();
        return categoryMapper.toView(categories);
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
