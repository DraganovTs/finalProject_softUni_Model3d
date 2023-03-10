package com.homecode.library.service.impl;

import com.homecode.library.model.CategoryModelEntity;
import com.homecode.library.model.ModelEntity;
import com.homecode.library.model.UserEntity;
import com.homecode.library.model.dto.ModelUploadDTO;
import com.homecode.library.model.view.CustomerProfileModelsView;
import com.homecode.library.repository.ModelRepository;
import com.homecode.library.service.ModelService;
import com.homecode.library.util.ImageUpload;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelServiceImpl implements ModelService {

    private final ModelMapper modelMapper;
    private final ModelRepository modelRepository;
    private final CustomerUserServiceImpl userService;
    private final CategoryModelServiceImpl categoryModelService;
    private ImageUpload imageUpload;

    @Autowired
    public ModelServiceImpl(ModelMapper modelMapper, ModelRepository modelRepository, CustomerUserServiceImpl userService, CategoryModelServiceImpl categoryModelService, ImageUpload imageUpload) {
        this.modelMapper = modelMapper;
        this.modelRepository = modelRepository;
        this.userService = userService;
        this.categoryModelService = categoryModelService;
        this.imageUpload = imageUpload;
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
    public void uploadModel(MultipartFile imageModel, ModelUploadDTO modelUploadDTO, String username) throws IOException {

        try {
        imageUpload.uploadImage(imageModel);

        UserEntity user = this.userService.findUserByUsername(username);
        CategoryModelEntity category = this.categoryModelService.findCategoryByName(modelUploadDTO.getCategory());

        ModelEntity modelToSave = new ModelEntity()
                .setName(modelUploadDTO.getName())
                .setManufacturer(modelUploadDTO.getManufacturer())
                .setCategory(category)
                .setDownloadLink("txt")
                .setImage(Base64.getEncoder().encodeToString(imageModel.getBytes()))
                .setOwner(user);


        this.modelRepository.save(modelToSave);

        }catch (Exception e){
            System.out.println(e.getMessage());

        }


    }
}
