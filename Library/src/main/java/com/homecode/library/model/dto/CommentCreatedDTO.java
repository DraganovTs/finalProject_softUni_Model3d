package com.homecode.library.model.dto;

public class CommentCreatedDTO {

    private String username;
    private Long modelId;
    private String message;

    public CommentCreatedDTO() {
    }

    public String getUsername() {
        return username;
    }

    public CommentCreatedDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public Long getModelId() {
        return modelId;
    }

    public CommentCreatedDTO setModelId(Long modelId) {
        this.modelId = modelId;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CommentCreatedDTO setMessage(String message) {
        this.message = message;
        return this;
    }
}
