package com.homecode.library.service;

import com.homecode.library.model.UserEntity;
import com.homecode.library.model.dto.UserRegisterDTO;
import com.homecode.library.repository.UserRepository;
import com.homecode.library.service.impl.CustomerUserServiceImpl;
import com.homecode.library.service.impl.ModelServiceImpl;
import com.homecode.library.service.impl.RoleServiceImpl;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerUserServiceImplTest {

    private final String EXISTING_EMAIL = "user@example.com";

    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private ModelMapper mockModelMapper;
    @Mock
    private RoleServiceImpl mockRoleService;
    @Mock
    private ModelServiceImpl mockModelService;

    @Captor
    private ArgumentCaptor<UserEntity> userEntityArgumentCaptor;

    private CustomerUserServiceImpl toTest;

    @BeforeEach
    void setUp() {
        toTest = new CustomerUserServiceImpl(
                mockUserRepository,
                mockModelMapper,
                mockRoleService,
                mockModelService
        );
    }

    @Test
    void testUserRegistration() {
        UserRegisterDTO testUserRegisterDTO = new UserRegisterDTO()
                .setEmail(EXISTING_EMAIL)
                .setFirstName("user")
                .setLastName("userov")
                .setPassword("user")
                .setRepeatPassword("user");

        toTest.register(testUserRegisterDTO);

        Mockito.verify(mockUserRepository).save(any());
    }

    @Test
    void testUserRegistrationWhitCapture() {
        UserRegisterDTO testUserRegisterDTO = new UserRegisterDTO()
                .setEmail(EXISTING_EMAIL)
                .setFirstName("user")
                .setLastName("userov")
                .setPassword("user")
                .setRepeatPassword("user");

        toTest.register(testUserRegisterDTO);

        Mockito.verify(mockUserRepository).save(userEntityArgumentCaptor.capture());

        UserEntity savedUser = userEntityArgumentCaptor.getValue();

        Assertions.assertEquals(savedUser.getFirstName(),testUserRegisterDTO.getFirstName());
        Assertions.assertEquals(savedUser.getLastName(),testUserRegisterDTO.getLastName());
        Assertions.assertEquals(savedUser.getPassword(),testUserRegisterDTO.getPassword());
    }

    @Test
    void testConfirmPassword() {
        UserRegisterDTO testUserRegisterDTO = new UserRegisterDTO()
                .setPassword("user")
                .setRepeatPassword("user");

        toTest.confirmPassword(testUserRegisterDTO);

        Assertions.assertEquals(testUserRegisterDTO.getPassword(),testUserRegisterDTO.getRepeatPassword());
    }

    @Test
    void testFindUserByUsername() {

        UserEntity testUserEntity = new UserEntity()
                .setEmail(EXISTING_EMAIL)
                .setFirstName("user")
                .setLastName("userov");


        when(mockUserRepository.findUserEntitiesByEmail(EXISTING_EMAIL))
                .thenReturn(Optional.of(testUserEntity));

        toTest.findUserByUsername(EXISTING_EMAIL);

        Assertions.assertEquals(testUserEntity.getEmail(),EXISTING_EMAIL);
        Assertions.assertEquals(testUserEntity.getFirstName(),"user");
        Assertions.assertEquals(testUserEntity.getLastName(),"userov");
    }
}
