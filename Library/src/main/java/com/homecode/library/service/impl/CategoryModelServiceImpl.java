package com.homecode.library.service.impl;

import com.homecode.library.model.CategoryModelEntity;
import com.homecode.library.model.dto.CategoryDTO;
import com.homecode.library.repository.CategoryRepository;
import com.homecode.library.service.CategoryModelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryModelServiceImpl implements CategoryModelService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryModelServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean isEmpty() {
        return this.categoryRepository.count() == 0;
    }

    @Override
    public void initCategories() {



    }

    @Override
    public List<CategoryDTO> findAll() {


        return this.categoryRepository
                .findAll()
                .stream()
                .map(c -> this.modelMapper.map(c, CategoryDTO.class))
                .collect(Collectors.toList());
        }


    @Override
    public CategoryModelEntity save(CategoryDTO categoryDTO) {
        CategoryModelEntity categoryToSave = this.modelMapper.map(categoryDTO,CategoryModelEntity.class);
        return this.categoryRepository.save(categoryToSave);
    }

    @Override
    public CategoryModelEntity getById(Long id) {
        return this.categoryRepository.findById(id).orElseThrow();
    }

    @Override
    public CategoryModelEntity update(CategoryModelEntity categoryModelEntity) {
        CategoryModelEntity categoryUpdated = new CategoryModelEntity()
                .setName(categoryModelEntity.getName())
                .setActive(categoryModelEntity.isActive())
                .setDeleted(categoryModelEntity.isDeleted());
        return this.categoryRepository.save(categoryUpdated);
    }

    @Override
    public void deleteById(Long id) {
        CategoryModelEntity categoryModel = getById(id);
        categoryModel.setDeleted(true);
        categoryModel.setActive(false);
        this.categoryRepository.save(categoryModel);
    }

    @Override
    public void enableById(Long id) {
        CategoryModelEntity categoryModel = getById(id);
        categoryModel.setDeleted(false);
        categoryModel.setActive(true);
        this.categoryRepository.save(categoryModel);
    }

    @Override
    public boolean findCategoryByName(CategoryDTO categoryDTO) {
        return this.categoryRepository.findByName(categoryDTO.getName()).isEmpty();
    }
}
