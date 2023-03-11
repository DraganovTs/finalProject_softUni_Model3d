package com.homecode.library.service.impl;

import com.homecode.library.model.CategoryModelEntity;
import com.homecode.library.model.ModelEntity;
import com.homecode.library.model.UserEntity;
import com.homecode.library.model.dto.ModelUploadDTO;
import com.homecode.library.model.view.CustomerProfileModelsView;
import com.homecode.library.model.view.ModelsShowAllView;
import com.homecode.library.repository.ModelRepository;
import com.homecode.library.service.ModelService;
import com.homecode.library.util.ImageUpload;
import com.homecode.library.util.ZipFileUpload;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelServiceImpl implements ModelService {

    private final ModelMapper modelMapper;
    private final ModelRepository modelRepository;
    private final CustomerUserServiceImpl userService;
    private final CategoryModelServiceImpl categoryModelService;
    private final ImageUpload imageUpload;
    private final ZipFileUpload zipFileUpload;

    @Autowired
    public ModelServiceImpl(ModelMapper modelMapper, ModelRepository modelRepository, CustomerUserServiceImpl userService, CategoryModelServiceImpl categoryModelService, ImageUpload imageUpload, ZipFileUpload zipFileUpload) {
        this.modelMapper = modelMapper;
        this.modelRepository = modelRepository;
        this.userService = userService;
        this.categoryModelService = categoryModelService;
        this.imageUpload = imageUpload;
        this.zipFileUpload = zipFileUpload;
    }


    @Override
    public List<CustomerProfileModelsView> getUserModels(List<ModelEntity> userModels) {
        return userModels
                .stream()
                .map(m -> this.modelMapper.map(m, CustomerProfileModelsView.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isExistInDB(ModelUploadDTO modelUploadDTO) {
        if (this.modelRepository.findByName(modelUploadDTO.getName()).isPresent()) {
            return false;
        }
        if (this.modelRepository.findByManufacturer(modelUploadDTO.getManufacturer()).isPresent()) {
            return false;
        }
        return true;
    }

    @Override
    public void uploadModel(MultipartFile imageModel, MultipartFile zipModel, ModelUploadDTO modelUploadDTO, String username) throws IOException {

        try {
            this.imageUpload.uploadImage(imageModel);
           // this.zipFileUpload.uploadZipFile(zipModel);
            UserEntity user = this.userService.findUserByUsername(username);
            CategoryModelEntity category = this.categoryModelService.findCategoryByName(modelUploadDTO.getCategory());

            ModelEntity modelToSave = new ModelEntity()
                    .setName(modelUploadDTO.getName())
                    .setManufacturer(modelUploadDTO.getManufacturer())
                    .setCategory(category)
                    .setDownloadLink("txt")
                   // .setDownloadLink(Base64.getEncoder().encodeToString(zipModel.getBytes()))
                    .setImage(Base64.getEncoder().encodeToString(imageModel.getBytes()))
                    .setOwner(user)
                    .setApproved(true);
            //TODO dont set it to approved
            //TODO fix save downloadURL

            this.modelRepository.save(modelToSave);

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }


    }

    @Override
    public List<ModelsShowAllView> getAllModels() {
       return this.modelRepository.findAll().stream().filter(ModelEntity::isApproved).map(m->this.modelMapper.map(m,ModelsShowAllView.class)).collect(Collectors.toList());
    }
}
