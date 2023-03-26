package com.homecode.library.service;

import com.homecode.library.model.AdminEntity;
import com.homecode.library.model.UserEntity;
import com.homecode.library.model.UserRoleEntity;
import com.homecode.library.model.dto.AdminRegisterDTO;
import com.homecode.library.model.dto.UserAddRolesDto;
import com.homecode.library.model.enums.UserRoleEnum;
import com.homecode.library.repository.AdminRepository;
import com.homecode.library.service.impl.AdminServiceImpl;
import com.homecode.library.service.impl.CustomerUserServiceImpl;
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
public class AdminServiceImplTest {
    private final String EXISTING_EMAIL = "admin@example.com";
    @Mock
    private AdminRepository mockAdminRepository;
    @Mock
    private RoleServiceImpl mockRoleService;
    @Mock
    private ModelMapper mockModelMapper;
    @Mock
    private CustomerUserServiceImpl mockCustomerUserService;

    @Captor
    private ArgumentCaptor<AdminEntity> adminEntityArgumentCaptor;
    private AdminServiceImpl toTest;


    @BeforeEach
    void setUp() {
        toTest = new AdminServiceImpl(
                mockAdminRepository,
                mockRoleService,
                mockModelMapper,
                mockCustomerUserService
        );
    }

    @Test
    void testAdminRegistration() {
        AdminRegisterDTO testAdminRegisterDTO = new AdminRegisterDTO()
                .setEmail("admin@example.com")
                .setFirstName("admin")
                .setLastName("adminov")
                .setPassword("admin")
                .setRepeatPassword("admin");

        toTest.register(testAdminRegisterDTO);

        Mockito.verify(mockAdminRepository).save(any());

    }


    @Test
    void testAdminRegistrationWhitCapture() {
        AdminRegisterDTO testAdminRegisterDTO = new AdminRegisterDTO()
                .setEmail("admin@example.com")
                .setFirstName("admin")
                .setLastName("adminov")
                .setPassword("admin")
                .setRepeatPassword("admin");

        toTest.register(testAdminRegisterDTO);

        Mockito.verify(mockAdminRepository).save(adminEntityArgumentCaptor.capture());

        AdminEntity savedAdmin = adminEntityArgumentCaptor.getValue();
        Assertions.assertEquals(savedAdmin.getEmail(), testAdminRegisterDTO.getEmail());
        Assertions.assertEquals(savedAdmin.getFirstName(), testAdminRegisterDTO.getFirstName());
        Assertions.assertEquals(savedAdmin.getLastName(), testAdminRegisterDTO.getLastName());
        Assertions.assertEquals(savedAdmin.getPassword(), testAdminRegisterDTO.getPassword());

    }

    @Test
    void testConfirmPassword() {
        AdminRegisterDTO testAdminRegisterDTO = new AdminRegisterDTO()
                .setPassword("admin")
                .setRepeatPassword("admin");

        toTest.confirmPassword(testAdminRegisterDTO);

        Assertions.assertEquals(testAdminRegisterDTO.getPassword(), testAdminRegisterDTO.getRepeatPassword());
    }


    @Test
    void testFindAdminEntityByEmail() {


        AdminEntity testAdminEntity = new AdminEntity()
                .setEmail(EXISTING_EMAIL)
                .setFirstName("admin")
                .setLastName("adminov")
                .setPassword("admin")
                .setId(1L);

        when(mockAdminRepository.findAdminEntityByEmail(EXISTING_EMAIL))
                .thenReturn(Optional.of(testAdminEntity));

        UserAddRolesDto testAdminAddRolesDto = new UserAddRolesDto()
                .setEmail(EXISTING_EMAIL)
                .setId(1L)
                .setFirstName("admin")
                .setLastName("adminov");

        toTest.findAdminEntityByEmail(testAdminAddRolesDto);

        Assertions.assertTrue(true);
    }

    @Test
    void testAddAdminRole() {
        UserAddRolesDto userAddRolesDto = new UserAddRolesDto()
                .setEmail("test@test.com")
                .setId(1L)
                .setFirstName("test")
                .setLastName("testov");

        UserEntity user = new UserEntity()
                .setEmail("test@test.com")
                .setId(1L)
                .setFirstName("test")
                .setLastName("testov")
                .setPassword("12345");

        when(mockCustomerUserService.findUserByUsername("test@test.com"))
                .thenReturn(user);

        UserRoleEntity role1 = new UserRoleEntity().setRole(UserRoleEnum.ADMIN).setId(1L);
        UserRoleEntity role2 = new UserRoleEntity().setRole(UserRoleEnum.MODERATOR).setId(2L);

        when(mockRoleService.findAll())
                .thenReturn(List.of(role1,role2));

        toTest.addAdminRole(userAddRolesDto);

        Mockito.verify(mockAdminRepository).save(adminEntityArgumentCaptor.capture());

        AdminEntity savedAdmin = adminEntityArgumentCaptor.getValue();
        Assertions.assertEquals(savedAdmin.getEmail(), userAddRolesDto.getEmail());
        Assertions.assertEquals(savedAdmin.getFirstName(), userAddRolesDto.getFirstName());
        Assertions.assertEquals(savedAdmin.getLastName(), userAddRolesDto.getLastName());

    }

}
