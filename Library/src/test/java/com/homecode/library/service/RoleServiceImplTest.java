package com.homecode.library.service;

import com.homecode.library.model.UserRoleEntity;
import com.homecode.library.model.enums.UserRoleEnum;
import com.homecode.library.repository.RoleRepository;
import com.homecode.library.service.impl.RoleServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoleServiceImplTest {

    @Mock
    private RoleRepository mockRoleRepository;


    private RoleServiceImpl toTest;

    @BeforeEach
    void setUp(){
        toTest = new RoleServiceImpl(mockRoleRepository);
    }

    @Test
    void testInit(){

        toTest.initRoles();

        Mockito.verify(mockRoleRepository).saveAll(any());
    }


    @Test
    void testFindByRole(){

        UserRoleEntity role = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);

        when(mockRoleRepository.findByRole(UserRoleEnum.ADMIN))
                .thenReturn(role);

        UserRoleEntity roleGet =  toTest.findByRole(UserRoleEnum.ADMIN);

        Assertions.assertEquals(roleGet,role);
    }

}
