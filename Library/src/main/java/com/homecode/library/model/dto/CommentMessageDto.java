package com.homecode.library.model.dto;

public class CommentMessageDto {
    private String message;

    public CommentMessageDto(String message) {
        this.message = message;
    }

    public CommentMessageDto() {
    }

    public String getMessage() {
        return message;
    }

    public CommentMessageDto setMessage(String message) {
        this.message = message;
        return this;
    }
}
