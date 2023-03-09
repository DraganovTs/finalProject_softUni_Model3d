package com.homecode.admin.init;

import com.homecode.library.model.AdminEntity;
import com.homecode.library.model.UserEntity;
import com.homecode.library.model.enums.UserRoleEnum;
import com.homecode.library.repository.AdminRepository;
import com.homecode.library.repository.UserRepository;
import com.homecode.library.service.impl.CategoryModelServiceImpl;
import com.homecode.library.service.impl.RoleServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InitService {
    private final RoleServiceImpl roleService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CategoryModelServiceImpl categoryModelService;
    private final AdminRepository adminRepository;

    public InitService(UserRepository userRepository, @Value("${spring.security.user.password}") String defaultPassword, RoleServiceImpl roleService, PasswordEncoder passwordEncoder, CategoryModelServiceImpl categoryModelService, AdminRepository adminRepository) {

        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.categoryModelService = categoryModelService;
        this.adminRepository = adminRepository;
    }

    @PostConstruct
    public void startApp() {
        if (this.roleService.isEmpty()) {
            this.roleService.initRoles();
        }
        if (this.categoryModelService.isEmpty()) {
            this.categoryModelService.initCategories();
        }

        initUsers();
    }


    private void initUsers() {
        if (userRepository.count() == 0) {
           // initAdmin();
           // initModerator();
           // initUserRegular();
        }
    }

    private void initAdmin() {
        var adminUser = new AdminEntity()
                .setFirstName("admin")
                .setLastName("adminov")
                .setEmail("admin@example.com")
                .setPassword(passwordEncoder.encode("admin"))
                .setRoles(roleService.findAll());

        adminRepository.save(adminUser);
    }

    private void initModerator() {
        var moderatorUser = new UserEntity()
                .setFirstName("moderator")
                .setLastName("moderator")
                .setEmail("moderator@example.com")
                .setPassword(passwordEncoder.encode("moderator"))
                .setRoles(List.of(roleService.findByRole(UserRoleEnum.MODERATOR)));

        userRepository.save(moderatorUser);
    }

    private void initUserRegular() {
        var adminUser = new UserEntity()
                .setFirstName("user")
                .setLastName("user")
                .setEmail("user@example.com")
                .setPassword(passwordEncoder.encode("user"));
        userRepository.save(adminUser);
    }
}
