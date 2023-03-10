package com.homecode.library.service;

import com.homecode.library.model.ModelEntity;
import com.homecode.library.model.dto.ModelUploadDTO;
import com.homecode.library.model.view.CustomerProfileModelsView;

import java.util.List;

public interface ModelService {

    List<CustomerProfileModelsView> getUserModels(List<ModelEntity> userModels);

    boolean uploadModel(ModelUploadDTO modelUploadDTO, String username);
}
