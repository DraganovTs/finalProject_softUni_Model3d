package com.homecode.library.service;

import com.homecode.library.model.ModelEntity;
import com.homecode.library.model.dto.ModelUploadDTO;
import com.homecode.library.model.view.CustomerProfileModelsView;
import com.homecode.library.model.view.ModelsShowAllView;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ModelService {

    List<CustomerProfileModelsView> getUserModels(List<ModelEntity> userModels);

    boolean isExistInDB(ModelUploadDTO modelUploadDTO);

    void uploadModel(MultipartFile imageModel,MultipartFile zipModel, ModelUploadDTO modelUploadDTO, String username) throws IOException;

    List<ModelsShowAllView> getAllModels();
}