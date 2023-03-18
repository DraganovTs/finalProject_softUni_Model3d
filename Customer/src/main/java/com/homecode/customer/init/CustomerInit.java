package com.homecode.customer.init;


import com.homecode.library.model.*;
import com.homecode.library.model.enums.UserRoleEnum;
import com.homecode.library.repository.AdminRepository;
import com.homecode.library.repository.CategoryRepository;
import com.homecode.library.repository.ModelRepository;
import com.homecode.library.repository.UserRepository;
import com.homecode.library.service.impl.CategoryModelServiceImpl;
import com.homecode.library.service.impl.FileServiceImpl;
import com.homecode.library.service.impl.RoleServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

    private final FileServiceImpl fileService;


    public CustomerInit(RoleServiceImpl roleService, UserRepository userRepository, PasswordEncoder passwordEncoder, CategoryModelServiceImpl categoryModelService, AdminRepository adminRepository, CategoryRepository categoryRepository, ModelRepository modelRepository, FileServiceImpl fileService) {
        this.roleService = roleService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.categoryModelService = categoryModelService;
        this.adminRepository = adminRepository;
        this.categoryRepository = categoryRepository;
        this.modelRepository = modelRepository;

        this.fileService = fileService;
    }


    @PostConstruct
    public void startApp() throws IOException {
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
                .setName("Side Table");
        var category2 = new CategoryModelEntity()
                .setName("Fireplace");
        var category3 = new CategoryModelEntity()
                .setName("Table Lamp");
        var category4 = new CategoryModelEntity()
                .setName("Ottoman");
        var category5 = new CategoryModelEntity()
                .setName("Armchair");
        var category6 = new CategoryModelEntity()
                .setName("Cushion");
        var category7 = new CategoryModelEntity()
                .setName("Coffee Table");
        var category8 = new CategoryModelEntity()
                .setName("Shelf");
        var category9 = new CategoryModelEntity()
                .setName("Sofa");
        var category10 = new CategoryModelEntity()
                .setName("Dressing Table");
        var category11 = new CategoryModelEntity()
                .setName("Window Curtain");
        var category12 = new CategoryModelEntity()
                .setName("Bath Curtain");
        var category13 = new CategoryModelEntity()
                .setName("Chandelier");
        var category14 = new CategoryModelEntity()
                .setName("Ceiling Fan");
        var category15 = new CategoryModelEntity()
                .setName("Wardrobe");
        var category16 = new CategoryModelEntity()
                .setName("Floor Lamp");
        var category17 = new CategoryModelEntity()
                .setName("Vase Flower");
        var category18 = new CategoryModelEntity()
                .setName("Bed");

        List<CategoryModelEntity> categoriesToSave = new ArrayList<>();
        categoriesToSave.add(category1);
        categoriesToSave.add(category2);
        categoriesToSave.add(category3);
        categoriesToSave.add(category4);
        categoriesToSave.add(category5);
        categoriesToSave.add(category6);
        categoriesToSave.add(category7);
        categoriesToSave.add(category8);
        categoriesToSave.add(category9);
        categoriesToSave.add(category10);
        categoriesToSave.add(category11);
        categoriesToSave.add(category12);
        categoriesToSave.add(category13);
        categoriesToSave.add(category14);
        categoriesToSave.add(category15);
        categoriesToSave.add(category16);
        categoriesToSave.add(category17);
        categoriesToSave.add(category18);

        this.categoryRepository.saveAll(categoriesToSave);
    }

    private void initModels() throws IOException {
        ZipFileEntity zip1 = this.fileService.saveZipFile("application/x-zip-compressed", "8.zip", this.getClass().getClassLoader().getResourceAsStream("static/images/product/8.zip").readAllBytes());
        ImageFileEntity image1 = this.fileService.saveImageFile("image/jpeg", "8.jpg", this.getClass().getClassLoader().getResourceAsStream("static/images/product/8.jpg").readAllBytes());

        var model01 = new ModelEntity()
                .setName("Rounded")
                .setManufacturer("Fendy")
                .setCategory(this.categoryModelService.findCategoryByName("Coffee Table"))
                .setDescription("Wooden round coffee table")
                .setZipModel(zip1)
                .setImageModel(image1)
                .setApproved(true)
                .setOwner(this.userRepository.findUserEntitiesByEmail("moderator@example.com").get());

        ZipFileEntity zip2 = this.fileService.saveZipFile("application/x-zip-compressed", "12.zip", this.getClass().getClassLoader().getResourceAsStream("static/images/product/12.zip").readAllBytes());
        ImageFileEntity image2 = this.fileService.saveImageFile("image/jpeg", "12.jpg", this.getClass().getClassLoader().getResourceAsStream("static/images/product/12.jpg").readAllBytes());

        var model02 = new ModelEntity()
                .setName("Fluffy")
                .setManufacturer("Gucci")
                .setCategory(this.categoryModelService.findCategoryByName("Armchair"))
                .setDescription("fluffy red armchair")
                .setZipModel(zip2)
                .setImageModel(image2)
                .setApproved(true)
                .setOwner(this.userRepository.findUserEntitiesByEmail("moderator@example.com").get());

        ZipFileEntity zip3 = this.fileService.saveZipFile("application/x-zip-compressed", "22.zip", this.getClass().getClassLoader().getResourceAsStream("static/images/product/22.zip").readAllBytes());
        ImageFileEntity image3 = this.fileService.saveImageFile("image/jpeg", "22.jpg", this.getClass().getClassLoader().getResourceAsStream("static/images/product/22.jpg").readAllBytes());

        var model03 = new ModelEntity()
                .setName("Rose ottoman")
                .setManufacturer("Gucci")
                .setCategory(this.categoryModelService.findCategoryByName("Ottoman"))
                .setDescription("perfect ottoman for you")
                .setZipModel(zip3)
                .setImageModel(image3)
                .setApproved(true)
                .setOwner(this.userRepository.findUserEntitiesByEmail("moderator@example.com").get());


        ZipFileEntity zip4 = this.fileService.saveZipFile("application/x-zip-compressed", "20.zip", this.getClass().getClassLoader().getResourceAsStream("static/images/product/20.zip").readAllBytes());
        ImageFileEntity image4 = this.fileService.saveImageFile("image/jpeg", "20.jpg", this.getClass().getClassLoader().getResourceAsStream("static/images/product/20.jpg").readAllBytes());

        var model04 = new ModelEntity()
                .setName("Pinky")
                .setManufacturer("Fendy")
                .setCategory(this.categoryModelService.findCategoryByName("Armchair"))
                .setDescription("perfect pink armchair")
                .setZipModel(zip4)
                .setImageModel(image4)
                .setApproved(true)
                .setOwner(this.userRepository.findUserEntitiesByEmail("moderator@example.com").get());


        ZipFileEntity zip5 = this.fileService.saveZipFile("application/x-zip-compressed", "29.zip", this.getClass().getClassLoader().getResourceAsStream("static/images/product/29.zip").readAllBytes());
        ImageFileEntity image5 = this.fileService.saveImageFile("image/jpeg", "29.jpg", this.getClass().getClassLoader().getResourceAsStream("static/images/product/29.jpg").readAllBytes());

        var model05 = new ModelEntity()
                .setName("Small Table")
                .setManufacturer("Fendy")
                .setCategory(this.categoryModelService.findCategoryByName("Coffee Table"))
                .setDescription("small table for your coffee")
                .setZipModel(zip5)
                .setImageModel(image5)
                .setApproved(true)
                .setOwner(this.userRepository.findUserEntitiesByEmail("moderator@example.com").get());

        ZipFileEntity zip6 = this.fileService.saveZipFile("application/x-zip-compressed", "26.zip", this.getClass().getClassLoader().getResourceAsStream("static/images/product/26.zip").readAllBytes());
        ImageFileEntity image6 = this.fileService.saveImageFile("image/jpeg", "26.jpg", this.getClass().getClassLoader().getResourceAsStream("static/images/product/26.jpg").readAllBytes());

        var model06 = new ModelEntity()
                .setName("Blue Cushion")
                .setManufacturer("Lux Decco")
                .setCategory(this.categoryModelService.findCategoryByName("Cushion"))
                .setDescription("small blue cushion for your soffa")
                .setZipModel(zip6)
                .setImageModel(image6)
                .setApproved(true)
                .setOwner(this.userRepository.findUserEntitiesByEmail("user@example.com").get());

        ZipFileEntity zip7 = this.fileService.saveZipFile("application/x-zip-compressed", "15.zip", this.getClass().getClassLoader().getResourceAsStream("static/images/product/15.zip").readAllBytes());
        ImageFileEntity image7 = this.fileService.saveImageFile("image/jpeg", "15.jpg", this.getClass().getClassLoader().getResourceAsStream("static/images/product/15.jpg").readAllBytes());

        var model07 = new ModelEntity()
                .setName("Blue Printed Cushion")
                .setManufacturer("Lux Decco")
                .setCategory(this.categoryModelService.findCategoryByName("Cushion"))
                .setDescription("small blue cushion whit stylish print for your soffa")
                .setZipModel(zip7)
                .setImageModel(image7)
                .setApproved(true)
                .setOwner(this.userRepository.findUserEntitiesByEmail("user@example.com").get());

        ZipFileEntity zip8 = this.fileService.saveZipFile("application/x-zip-compressed", "33.zip", this.getClass().getClassLoader().getResourceAsStream("static/images/product/33.zip").readAllBytes());
        ImageFileEntity image8 = this.fileService.saveImageFile("image/jpeg", "33.jpg", this.getClass().getClassLoader().getResourceAsStream("static/images/product/33.jpg").readAllBytes());

        var model08 = new ModelEntity()
                .setName("Blue Classic")
                .setManufacturer("Trussardi")
                .setCategory(this.categoryModelService.findCategoryByName("Sofa"))
                .setDescription("Blue classic 2 seater sofa")
                .setZipModel(zip8)
                .setImageModel(image8)
                .setApproved(true)
                .setOwner(this.userRepository.findUserEntitiesByEmail("user@example.com").get());

        ZipFileEntity zip9 = this.fileService.saveZipFile("application/x-zip-compressed", "32.zip", this.getClass().getClassLoader().getResourceAsStream("static/images/product/32.zip").readAllBytes());
        ImageFileEntity image9 = this.fileService.saveImageFile("image/jpeg", "32.jpg", this.getClass().getClassLoader().getResourceAsStream("static/images/product/32.jpg").readAllBytes());

        var model09 = new ModelEntity()
                .setName("Luxy")
                .setManufacturer("Trussardi")
                .setCategory(this.categoryModelService.findCategoryByName("Table Lamp"))
                .setDescription("Classic style table lamp")
                .setZipModel(zip9)
                .setImageModel(image9)
                .setApproved(true)
                .setOwner(this.userRepository.findUserEntitiesByEmail("user@example.com").get());

        ZipFileEntity zip10 = this.fileService.saveZipFile("application/x-zip-compressed", "14.zip", this.getClass().getClassLoader().getResourceAsStream("static/images/product/14.zip").readAllBytes());
        ImageFileEntity image10 = this.fileService.saveImageFile("image/jpeg", "14.jpg", this.getClass().getClassLoader().getResourceAsStream("static/images/product/14.jpg").readAllBytes());

        var model10 = new ModelEntity()
                .setName("Cristiano from Roma")
                .setManufacturer("Fendy")
                .setCategory(this.categoryModelService.findCategoryByName("Vase Flower"))
                .setDescription("Big red vase is perfect accessory for yor table")
                .setZipModel(zip10)
                .setImageModel(image10)
                .setApproved(true)
                .setOwner(this.userRepository.findUserEntitiesByEmail("user@example.com").get());


        List<ModelEntity> modelsToSave = new ArrayList<>();
        modelsToSave.add(model01);
        modelsToSave.add(model02);
        modelsToSave.add(model03);
        modelsToSave.add(model04);
        modelsToSave.add(model05);
        modelsToSave.add(model06);
        modelsToSave.add(model07);
        modelsToSave.add(model08);
        modelsToSave.add(model09);
        modelsToSave.add(model10);



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
