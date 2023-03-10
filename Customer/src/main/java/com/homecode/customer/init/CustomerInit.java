package com.homecode.customer.init;


import com.homecode.library.model.AdminEntity;
import com.homecode.library.model.CategoryModelEntity;
import com.homecode.library.model.ModelEntity;
import com.homecode.library.model.UserEntity;
import com.homecode.library.model.enums.UserRoleEnum;
import com.homecode.library.repository.AdminRepository;
import com.homecode.library.repository.CategoryRepository;
import com.homecode.library.repository.ModelRepository;
import com.homecode.library.repository.UserRepository;
import com.homecode.library.service.impl.CategoryModelServiceImpl;
import com.homecode.library.service.impl.RoleServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerInit {

    private final RoleServiceImpl roleService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CategoryModelServiceImpl categoryModelService;
    private final AdminRepository adminRepository;

    private final CategoryRepository categoryRepository;
    private final ModelRepository modelRepository;

    public CustomerInit(RoleServiceImpl roleService, UserRepository userRepository, PasswordEncoder passwordEncoder, CategoryModelServiceImpl categoryModelService, AdminRepository adminRepository, CategoryRepository categoryRepository, ModelRepository modelRepository) {
        this.roleService = roleService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.categoryModelService = categoryModelService;
        this.adminRepository = adminRepository;
        this.categoryRepository = categoryRepository;
        this.modelRepository = modelRepository;
    }


    @PostConstruct
    public void startApp() {
        if (this.roleService.isEmpty()) {
            this.roleService.initRoles();
        }
        if (this.categoryModelService.isEmpty()) {
            this.categoryModelService.initCategories();
        }

        if (this.userRepository.count() == 0) {

            initUsers();
        }
        if (this.categoryRepository.count() == 0) {

            initCategories();
        }
        if (this.modelRepository.count() == 0) {


            initModels();
        }
    }


    private void initUsers() {
        if (userRepository.count() == 0) {
            initAdmin();
            initModerator();
            initUserRegular();

        }
    }

    private void initCategories() {
        var category1 = new CategoryModelEntity()
                .setName("Bedroom");
        var category2 = new CategoryModelEntity()
                .setName("LivingRoom");
        var category3 = new CategoryModelEntity()
                .setName("Kitchen");
        var category4 = new CategoryModelEntity()
                .setName("Bathroom");

        List<CategoryModelEntity> categoriesToSave = new ArrayList<>();
        categoriesToSave.add(category1);
        categoriesToSave.add(category2);
        categoriesToSave.add(category3);
        categoriesToSave.add(category4);

        this.categoryRepository.saveAll(categoriesToSave);
    }

    private void initModels() {
        var model01 = new ModelEntity()
                .setName("bed")
                .setManufacturer("fendy")
                .setDownloadLink("dox")
                .setImage("image")
                .setCategory(this.categoryModelService.findCategoryByName("Bedroom"))
                .setOwner(this.userRepository.findUserEntitiesByEmail("moderator@example.com").get());
        var model02 = new ModelEntity()
                .setName("table")
                .setManufacturer("gucci")
                .setDownloadLink("dox")
                .setImage("image")
                .setCategory(this.categoryModelService.findCategoryByName("LivingRoom"))
                .setOwner(this.userRepository.findUserEntitiesByEmail("moderator@example.com").get());
        var model03 = new ModelEntity()
                .setName("chair")
                .setManufacturer("vissionare")
                .setDownloadLink("dox")
                .setImage("image")
                .setCategory(this.categoryModelService.findCategoryByName("Kitchen"))
                .setOwner(this.userRepository.findUserEntitiesByEmail("moderator@example.com").get());
        var model04 = new ModelEntity()
                .setName("mirror")
                .setManufacturer("mirror")
                .setDownloadLink("dox")
                .setImage("image")
                .setCategory(this.categoryModelService.findCategoryByName("Bathroom"))
                .setOwner(this.userRepository.findUserEntitiesByEmail("moderator@example.com").get());

        List<ModelEntity> modelsToSave = new ArrayList<>();
        modelsToSave.add(model01);
        modelsToSave.add(model02);
        modelsToSave.add(model03);
        modelsToSave.add(model04);

        this.modelRepository.saveAll(modelsToSave);


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
