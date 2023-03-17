package com.homecode.library.service.impl;

import com.homecode.library.model.*;
import com.homecode.library.model.dto.ModelUploadDTO;
import com.homecode.library.model.view.CustomerProfileModelsView;
import com.homecode.library.model.view.ModelsShowAllView;
import com.homecode.library.repository.ModelRepository;
import com.homecode.library.service.FileService;
import com.homecode.library.service.ModelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelServiceImpl implements ModelService {

    private final ModelMapper modelMapper;
    private final ModelRepository modelRepository;

    private final CategoryModelServiceImpl categoryModelService;
    private final FileService fileService;


    @Autowired
    public ModelServiceImpl(ModelMapper modelMapper, ModelRepository modelRepository, CategoryModelServiceImpl categoryModelService, FileService fileService) {
        this.modelMapper = modelMapper;
        this.modelRepository = modelRepository;
        this.categoryModelService = categoryModelService;
        this.fileService = fileService;
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
        if (this.modelRepository.findByNameAndManufacturer(modelUploadDTO.getName(), modelUploadDTO.getManufacturer()).isPresent()) {
            return false;
        }
        return true;
    }

    @Override
    public ModelEntity uploadModel(MultipartFile imageModel, MultipartFile zipModel, ModelUploadDTO modelUploadDTO, UserEntity user) throws IOException {

        try {


            CategoryModelEntity category = this.categoryModelService.findCategoryByName(modelUploadDTO.getCategory());
            ImageFileEntity image = this.fileService.saveImageFile(imageModel);
            ZipFileEntity zip = this.fileService.saveZipFile(zipModel);

            ModelEntity modelToSave = new ModelEntity()
                    .setName(modelUploadDTO.getName())
                    .setManufacturer(modelUploadDTO.getManufacturer())
                    .setCategory(category)
                    .setDescription(modelUploadDTO.getDescription())
                    .setZipModel(zip)
                    .setImageModel(image)
                    .setOwner(user)
                    .setApproved(true);
            //TODO dont set it to approved


            this.modelRepository.save(modelToSave);

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

        return this.modelRepository.findByNameAndManufacturer(modelUploadDTO.getName(), modelUploadDTO.getManufacturer()).get();
    }

    @Override
    public List<ModelsShowAllView> getAllModels() {
        return this.modelRepository.findAll().stream().filter(ModelEntity::isApproved).map(m -> this.modelMapper.map(m, ModelsShowAllView.class)).collect(Collectors.toList());
    }


    @Override
    public ModelEntity findById(Long id) {
        return this.modelRepository.findById(id).orElseThrow();
    }

    @Override
    public void likeModel(ModelEntity model) {
        this.modelRepository.save(model.setLikes(model.getLikes() + 1));
    }

    @Override
    public ModelEntity findByModelNameAndManufacturer(ModelUploadDTO modelUploadDTO) {
        return this.modelRepository.findByNameAndManufacturer(modelUploadDTO.getName(), modelUploadDTO.getManufacturer()).orElseThrow();
    }
}
