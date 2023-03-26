package com.homecode.library.service;

import com.homecode.library.model.CategoryModelEntity;
import com.homecode.library.model.UserEntity;
import com.homecode.library.model.dto.CategoryDTO;
import com.homecode.library.repository.CategoryRepository;
import com.homecode.library.service.impl.CategoryModelServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryModelServiceImplTest {
    @Mock
    private  CategoryRepository mockCategoryRepository;
    @Mock
    private  ModelMapper mockModelMapper;
    @Captor
    private ArgumentCaptor<CategoryModelEntity> categoryModelEntityArgumentCaptor;

    private CategoryModelServiceImpl toTest;

    @BeforeEach
    void setUp(){
        toTest = new CategoryModelServiceImpl(
                mockCategoryRepository,
                mockModelMapper
        );
    }

    @Test
    void testUpdate(){
        CategoryDTO categoryDTO = new CategoryDTO()
                .setName("test")
                .setId(1L)
                .setActive(false)
                .setDeleted(true);

        CategoryModelEntity categoryModel = new CategoryModelEntity()
                .setName("noTest")
                .setId(1L)
                .setActive(true)
                .setDeleted(false);

        when(mockCategoryRepository.findById(1L))
                .thenReturn(Optional.of(categoryModel));

        toTest.update(categoryDTO);

        Mockito.verify(mockCategoryRepository).save(categoryModelEntityArgumentCaptor.capture());

        CategoryModelEntity categoryModelSaved = categoryModelEntityArgumentCaptor.getValue();

        Assertions.assertEquals(categoryModelSaved.getName(),categoryDTO.getName());
        Assertions.assertEquals(categoryModelSaved.isActive(),categoryDTO.isActive());
        Assertions.assertEquals(categoryModelSaved.isDeleted(),categoryDTO.isDeleted());

    }

    @Test
    void testDeleteById(){
        CategoryModelEntity categoryModel = new CategoryModelEntity()
                .setName("noTest")
                .setId(1L)
                .setActive(true)
                .setDeleted(false);

        when(mockCategoryRepository.findById(1L))
                .thenReturn(Optional.of(categoryModel));

        toTest.enableById(1L);

        Mockito.verify(mockCategoryRepository).save(categoryModelEntityArgumentCaptor.capture());

        CategoryModelEntity categoryModelSaved = categoryModelEntityArgumentCaptor.getValue();

        Assertions.assertEquals(categoryModelSaved.getName(),"noTest");
        Assertions.assertTrue(categoryModelSaved.isActive());
        Assertions.assertFalse(categoryModelSaved.isDeleted());
    }

    @Test
    void testEnableById(){
        CategoryModelEntity categoryModel = new CategoryModelEntity()
                .setName("noTest")
                .setId(1L)
                .setActive(false)
                .setDeleted(true);

        when(mockCategoryRepository.findById(1L))
                .thenReturn(Optional.of(categoryModel));

        toTest.enableById(1L);

        Mockito.verify(mockCategoryRepository).save(categoryModelEntityArgumentCaptor.capture());

        CategoryModelEntity categoryModelSaved = categoryModelEntityArgumentCaptor.getValue();

        Assertions.assertEquals(categoryModelSaved.getName(),"noTest");
        Assertions.assertTrue(categoryModelSaved.isActive());
        Assertions.assertFalse(categoryModelSaved.isDeleted());
    }
}
