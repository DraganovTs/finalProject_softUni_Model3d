package com.homecode.customer.init;

import com.homecode.library.model.UserEntity;
import com.homecode.library.model.UserRoleEntity;
import com.homecode.library.model.UserRoleEnum;
import com.homecode.library.repository.RoleRepository;
import com.homecode.library.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InitService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public InitService(RoleRepository roleRepository, UserRepository userRepository, @Value("${spring.security.user.password}") String defaultPassword, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }

    @PostConstruct
    public void startApp(){
        initRoles();
        initUsers();

    }


    private void initRoles(){
        if (this.roleRepository.count()==0){
            var moderatorRole = new UserRoleEntity().setRole(UserRoleEnum.MODERATOR);
            var adminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);

            roleRepository.save(adminRole);
            roleRepository.save(moderatorRole);
        }
    }


    private void initUsers(){
        if (userRepository.count()==0){
            initAdmin();
            initModerator();
            initUserRegular();
        }
    }

    private void initAdmin() {
        var adminUser = new UserEntity()
                .setFirstName("admin")
                .setLastName("adminov")
                .setEmail("admin@example.com")
                .setPassword(passwordEncoder.encode("admin"))
                .setRoles(roleRepository.findAll());

        userRepository.save(adminUser);
    }

    private void initModerator() {
        var moderatorUser = new UserEntity()
                .setFirstName("moderator")
                .setLastName("moderator")
                .setEmail("moderator@example.com")
                .setPassword(passwordEncoder.encode("moderator"))
                .setRoles(List.of(roleRepository.findByRole(UserRoleEnum.MODERATOR)));

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
