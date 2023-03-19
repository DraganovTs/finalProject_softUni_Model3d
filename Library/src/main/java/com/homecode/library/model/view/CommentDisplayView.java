package com.homecode.library.model.view;

public class CommentDisplayView {
    private Long id;
    private String firstName;
    private String message;

    public CommentDisplayView() {
    }

    public Long getId() {
        return id;
    }

    public CommentDisplayView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public CommentDisplayView setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CommentDisplayView setMessage(String message) {
        this.message = message;
        return this;
    }
}
