package com.homecode.library.service;

import com.homecode.library.model.CategoryModelEntity;
import com.homecode.library.model.ModelEntity;
import com.homecode.library.model.UserEntity;
import com.homecode.library.model.UserRoleEntity;
import com.homecode.library.model.dto.UserAddRolesDto;
import com.homecode.library.model.dto.UserRegisterDTO;
import com.homecode.library.model.enums.UserRoleEnum;
import com.homecode.library.model.view.CustomerCreditsView;
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


import java.util.List;
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
    void testUserRegistrationUsernameExist() {
        UserRegisterDTO testUserRegisterDTO = new UserRegisterDTO()
                .setEmail(EXISTING_EMAIL);

        UserEntity testUserEntity = new UserEntity()
                .setEmail(EXISTING_EMAIL);

        when(mockUserRepository.findUserEntitiesByEmail(EXISTING_EMAIL))
                .thenReturn(Optional.of(testUserEntity));

        toTest.register(testUserRegisterDTO);

        Assertions.assertFalse(toTest.register(testUserRegisterDTO));
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

    @Test
    void testDeleteModeratorRoleById(){
        UserEntity testUserEntity = new UserEntity()
                .setId(1L)
                .setRoles(List.of(new UserRoleEntity(),new UserRoleEntity()));

        when(mockUserRepository.findById(1L))
                .thenReturn(Optional.of(testUserEntity));

        toTest.deleteModeratorRoleById(1L);

        Mockito.verify(mockUserRepository).save(userEntityArgumentCaptor.capture());

        UserEntity userSaved = userEntityArgumentCaptor.getValue();

        Assertions.assertEquals(userSaved.getRoles().size(),0);
    }


    @Test
    void testCreateModerator(){
        UserAddRolesDto userAddRolesDto = new UserAddRolesDto().setEmail("test@test.com");

        UserEntity testUserEntity = new UserEntity()
                .setEmail("test@test.com");

        when(mockUserRepository.findUserEntitiesByEmail("test@test.com"))
                .thenReturn(Optional.of(testUserEntity));

        when(mockRoleService.findByRole(UserRoleEnum.MODERATOR))
                .thenReturn(new UserRoleEntity());

        toTest.createModerator(userAddRolesDto);

        Mockito.verify(mockUserRepository).save(userEntityArgumentCaptor.capture());

        UserEntity userSaved = userEntityArgumentCaptor.getValue();

        Assertions.assertEquals(userSaved.getRoles().size(),1);
    }

    @Test
    void testDailyResetCredits(){
        UserEntity testUserEntity = new UserEntity()
                .setCredits(1);

        UserEntity testUserEntity1 = new UserEntity()
                .setCredits(2);

        when(mockUserRepository.findAll())
                .thenReturn(List.of(testUserEntity,testUserEntity1));

        toTest.dailyResetCredits();

        Mockito.verify(mockUserRepository).saveAll(any());


    }

    @Test
    void testUserDownloadModel(){
        UserEntity testUserEntity = new UserEntity()
                .setEmail("test@test.com")
                .setCredits(1);

        when(mockUserRepository.findUserEntitiesByEmail("test@test.com"))
                .thenReturn(Optional.of(testUserEntity));

        when(mockModelService.findById(1L))
                .thenReturn(new ModelEntity());

        toTest.userDownloadModel("test@test.com",1L);

        Mockito.verify(mockUserRepository).save(userEntityArgumentCaptor.capture());

        UserEntity userSaved = userEntityArgumentCaptor.getValue();

        Assertions.assertEquals(userSaved.getDownloadedModels().size(),1);
        Assertions.assertEquals(userSaved.getCredits(),0);

    }

    @Test
    void testUserGetCredits(){
        UserEntity testUserEntity = new UserEntity()
                .setEmail("test@test.com")
                .setCredits(1);

        when(mockUserRepository.findUserEntitiesByEmail("test@test.com"))
                .thenReturn(Optional.of(testUserEntity));

        when(mockModelMapper.map(testUserEntity,CustomerCreditsView.class))
                .thenReturn(new CustomerCreditsView().setCredits(testUserEntity.getCredits()));

        CustomerCreditsView customerCreditsView = toTest.getUserCredits("test@test.com");

        Assertions.assertEquals(customerCreditsView.getCredits(),1);
    }

}
