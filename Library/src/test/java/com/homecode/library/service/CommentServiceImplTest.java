package com.homecode.library.service;

import com.homecode.library.model.*;
import com.homecode.library.model.dto.CommentCreatedDTO;
import com.homecode.library.model.view.CommentDisplayView;
import com.homecode.library.repository.CommentRepository;
import com.homecode.library.service.impl.CommentServiceImpl;
import com.homecode.library.service.impl.CustomerUserServiceImpl;
import com.homecode.library.service.impl.ModelServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;


import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommentServiceImplTest {
    @Mock
    private CommentRepository mockCommentRepository;
    @Mock
    private ModelServiceImpl mockModelService;
    @Mock
    private CustomerUserServiceImpl mockCustomerUserService;
    @Captor
    private ArgumentCaptor<CommentEntity> commentEntityArgumentCaptor;

    private CommentServiceImpl toTest;

    @BeforeEach
    void setUp() {
        toTest = new CommentServiceImpl(
                mockCommentRepository,
                mockModelService,
                mockCustomerUserService
        );
    }

    @Test
    void testGetAllComments() {
        ModelEntity model = new ModelEntity()
                .setId(1L)
                .setImageModel(new ImageFileEntity().setId(1L))
                .setApproved(true)
                .setZipModel(new ZipFileEntity().setId(1L))
                .setCategory(new CategoryModelEntity().setName("table"))
                .setManufacturer("Gucci");

        when(mockModelService.findById(1L))
                .thenReturn(model);

        CommentEntity comment1 = new CommentEntity()
                .setApproved(true)
                .setText("test1")
                .setAuthor(new UserEntity())
                .setModel(model)
                .setCreated(LocalDateTime.now())
                .setId(1L);

        CommentEntity comment2 = new CommentEntity()
                .setApproved(true)
                .setText("test2")
                .setAuthor(new UserEntity())
                .setModel(model)
                .setCreated(LocalDateTime.now())
                .setId(2L);

        when(mockCommentRepository.findAllByModel(model)).
        thenReturn(List.of(comment1,comment2));


      List<CommentDisplayView> commentEntities =  toTest.getAllCommentsForModel(1L);

        Assertions.assertEquals(commentEntities.size(),2);
    }

    @Test
    void testCreateComment() {
        CommentCreatedDTO commentCreatedDTO = new CommentCreatedDTO()
                .setMessage("testMessage")
                .setUsername("test@test.com")
                .setModelId(1L);

        UserEntity user = new UserEntity()
                .setId(1L)
                .setFirstName("user")
                .setLastName("userov")
                .setPassword("12345")
                .setRoles(null)
                .setEmail("test@test.com");

        when(mockCustomerUserService.findUserByUsername("test@test.com"))
                .thenReturn(user);

        ModelEntity model = new ModelEntity()
                .setId(1L)
                .setImageModel(new ImageFileEntity().setId(1L))
                .setApproved(true)
                .setZipModel(new ZipFileEntity().setId(1L))
                .setCategory(new CategoryModelEntity().setName("table"))
                .setManufacturer("Gucci");


        when(mockModelService.findById(1L))
                .thenReturn(model);


        toTest.createComment(commentCreatedDTO);

        Mockito.verify(mockCommentRepository).save(commentEntityArgumentCaptor.capture());

        CommentEntity commentSaved = commentEntityArgumentCaptor.getValue();

        Assertions.assertEquals(commentSaved.getModel().getId(),model.getId());
        Assertions.assertEquals(commentSaved.getAuthor().getId(),user.getId());
        Assertions.assertEquals(commentSaved.getText(),commentCreatedDTO.getMessage());
    }

}
