package com.homecode.library.service.impl;

import com.homecode.library.model.enums.CategoryEnum;
import com.homecode.library.model.CategoryModelEntity;
import com.homecode.library.repository.CategoryRepository;
import com.homecode.library.service.categoryModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryModelServiceImpl implements categoryModelService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryModelServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public boolean isEmpty() {
        return this.categoryRepository.count()==0;
    }

    @Override
    public void initCategories() {
        List<CategoryModelEntity> categoriesToSave = Arrays.stream(CategoryEnum.values())
                .map(categoryEnum -> new CategoryModelEntity()
                        .setName(categoryEnum)).collect(Collectors.toList());

        this.categoryRepository.saveAll(categoriesToSave);
    }
}
