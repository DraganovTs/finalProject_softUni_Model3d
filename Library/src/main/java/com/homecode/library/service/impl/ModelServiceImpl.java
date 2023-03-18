package com.homecode.library.service.impl;

import com.homecode.library.model.*;
import com.homecode.library.model.dto.ModelUploadDTO;
import com.homecode.library.model.view.CustomerProfileModelsView;
import com.homecode.library.model.view.ModelShowDetailView;
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
        return this.modelRepository.findByNameAndManufacturer(modelUploadDTO.getName(), modelUploadDTO.getManufacturer()).isEmpty();
    }

    @Override
    public void uploadModel(MultipartFile imageModel, MultipartFile zipModel, ModelUploadDTO modelUploadDTO, UserEntity user) throws IOException {

        try {


            CategoryModelEntity category = this.categoryModelService.findCategoryByName(modelUploadDTO.getCategory());
            ImageFileEntity image = this.fileService.saveImageFile(imageModel.getContentType(),imageModel.getOriginalFilename(), imageModel.getBytes());
            ZipFileEntity zip = this.fileService.saveZipFile(zipModel.getContentType(),zipModel.getOriginalFilename(), zipModel.getBytes());

            ModelEntity modelToSave = new ModelEntity()
                    .setName(modelUploadDTO.getName())
                    .setManufacturer(modelUploadDTO.getManufacturer())
                    .setCategory(category)
                    .setDescription(modelUploadDTO.getDescription())
                    .setZipModel(zip)
                    .setImageModel(image)
                    .setOwner(user);



            this.modelRepository.save(modelToSave);

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }

    @Override
    public List<ModelsShowAllView> getAllModels() {
        return this.modelRepository.findAllModels().stream().map(m -> this.modelMapper.map(m, ModelsShowAllView.class)).collect(Collectors.toList());
    }

    @Override
    public List<ModelsShowAllView> getAllModelsByKeyword(String keyword) {
        return this.modelRepository.findAllModelsByKeyword(keyword).stream().map(m -> this.modelMapper.map(m, ModelsShowAllView.class)).collect(Collectors.toList());
    }

    @Override
    public List<ModelsShowAllView> getAllModelsForModerator() {
        return this.modelRepository.getAllModelsForModerator().stream().map(m -> this.modelMapper.map(m, ModelsShowAllView.class)).collect(Collectors.toList());

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

    @Override
    public ModelShowDetailView showModelDetailById(Long id) {
        ModelEntity model = this.modelRepository.findById(id).get();
        return this.modelMapper.map(model, ModelShowDetailView.class);
    }

    @Override
    public void approveModelById(Long id) {
        ModelEntity model = this.modelRepository.findById(id).get();
        model.setApproved(true);
        this.modelRepository.save(model);
    }

    @Override
    public void modelDownloaded(Long id) {
        ModelEntity model = findById(id);
        model.setDownloaded(model.getDownloaded() + 1);
        this.modelRepository.save(model);
    }


}
