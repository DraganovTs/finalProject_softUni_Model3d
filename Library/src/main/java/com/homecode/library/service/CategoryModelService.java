package com.homecode.library.service;

import com.homecode.library.model.CategoryModelEntity;

import java.util.List;

public interface CategoryModelService {

    boolean isEmpty();

    void initCategories();

    List<CategoryModelEntity> findAll();

    CategoryModelEntity save(CategoryModelEntity categoryModelEntity);

    CategoryModelEntity getById(Long id);

    CategoryModelEntity update(CategoryModelEntity categoryModelEntity);

    void  deleteById(Long id);
    void enableById(Long id);

}
