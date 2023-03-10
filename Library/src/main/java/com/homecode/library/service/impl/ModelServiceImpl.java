package com.homecode.library.service.impl;

import com.homecode.library.model.ModelEntity;
import com.homecode.library.model.view.CustomerProfileModelsView;
import com.homecode.library.service.ModelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelServiceImpl implements ModelService {

    private final ModelMapper modelMapper;

    @Autowired
    public ModelServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public List<CustomerProfileModelsView> getUserModels(List<ModelEntity> userModels) {
        return userModels
                .stream()
                .map(m -> this.modelMapper.map(m, CustomerProfileModelsView.class))
                .collect(Collectors.toList());
    }
}
