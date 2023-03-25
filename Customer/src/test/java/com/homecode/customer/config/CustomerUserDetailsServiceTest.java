package com.homecode.customer.config;

import com.homecode.library.model.UserEntity;
import com.homecode.library.model.UserRoleEntity;
import com.homecode.library.model.enums.UserRoleEnum;
import com.homecode.library.repository.UserRepository;
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
public class CustomerUserDetailsServiceTest {

    private final String EXISTING_EMAIL = "moderator@example.com";
    private final String NOT_EXISTING_EMAIL = "123@123.com";
    private CustomerUserDetailsService toTest;

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp() {
        toTest = new CustomerUserDetailsService(
                mockUserRepository
        );
    }

    @Test
    void testUserFound() {

        UserRoleEntity testModeratorRole = new UserRoleEntity().setRole(UserRoleEnum.MODERATOR);

        UserEntity testUserEntity = new UserEntity()
                .setEmail(EXISTING_EMAIL)
                .setPassword("12345")
                .setRoles(List.of(testModeratorRole));

        when(mockUserRepository.findUserEntitiesByEmail(EXISTING_EMAIL))
                .thenReturn(Optional.of(testUserEntity));

        UserDetails moderatorDetails =
                toTest.loadUserByUsername(EXISTING_EMAIL);

        Assertions.assertNotNull(moderatorDetails);
        Assertions.assertEquals(EXISTING_EMAIL,moderatorDetails.getUsername());
        Assertions.assertEquals(testUserEntity.getPassword(),moderatorDetails.getPassword());
        Assertions.assertEquals(1,moderatorDetails.getAuthorities().size());

    }

    @Test
    void testUserNotFound() {
        assertThrows(UsernameNotFoundException.class,
                () -> {
                    toTest.loadUserByUsername(NOT_EXISTING_EMAIL);
                });
    }
}
