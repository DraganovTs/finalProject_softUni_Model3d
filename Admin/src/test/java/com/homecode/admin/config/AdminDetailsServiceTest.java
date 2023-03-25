package com.homecode.admin.config;

import com.homecode.library.model.AdminEntity;
import com.homecode.library.model.UserEntity;
import com.homecode.library.model.UserRoleEntity;
import com.homecode.library.model.enums.UserRoleEnum;
import com.homecode.library.repository.AdminRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AdminDetailsServiceTest {

    private final String EXISTING_EMAIL = "admin@example.com";
    private final String NOT_EXISTING_EMAIL = "123@123.com";

    private AdminDetailsService toTest;
    @Mock
    private AdminRepository mockAdminRepository;

    @BeforeEach
    void setUp(){
        toTest = new AdminDetailsService(
                mockAdminRepository
        );
    }

    @Test
    void testUserFound(){
        UserRoleEntity testAdminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);

        AdminEntity testAdminEntity = new AdminEntity()
                .setEmail(EXISTING_EMAIL)
                .setPassword("12345")
                .setRoles(List.of(testAdminRole));

        when(mockAdminRepository.findAdminEntityByEmail(EXISTING_EMAIL))
                .thenReturn(Optional.of(testAdminEntity));

        UserDetails moderatorDetails =
                toTest.loadUserByUsername(EXISTING_EMAIL);

        Assertions.assertNotNull(moderatorDetails);
        Assertions.assertEquals(EXISTING_EMAIL,moderatorDetails.getUsername());
        Assertions.assertEquals(testAdminEntity.getPassword(),moderatorDetails.getPassword());
        Assertions.assertEquals(1,moderatorDetails.getAuthorities().size());

    }

    @Test
    void testUserNotFound(){
        assertThrows(UsernameNotFoundException.class,
                () -> {
                    toTest.loadUserByUsername(NOT_EXISTING_EMAIL);
                });
    }
}
