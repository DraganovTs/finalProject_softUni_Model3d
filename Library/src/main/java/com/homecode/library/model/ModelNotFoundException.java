package com.homecode.library.model;



public class ModelNotFoundException extends RuntimeException  {

    private Long modelId;

    public ModelNotFoundException(Long modelId) {

        super("Object with ID " + modelId + " not found!");

        this.modelId = modelId;
    }

    public Long getModelId() {
        return modelId;
    }
}
