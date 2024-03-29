package com.homecode.library.model.dto;



import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import static com.homecode.library.constatnts.Messages.INVALID_CATEGORY_NAME;


public class CategoryDTO {

    private Long id;
    @NotNull
    @Size(min = 3,max = 20 , message = INVALID_CATEGORY_NAME )
    private String name;

    private boolean isDeleted ;

    private boolean isActive;

    public Long getId() {
        return id;
    }

    public CategoryDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CategoryDTO setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public CategoryDTO setDeleted(boolean deleted) {
        isDeleted = deleted;
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    public CategoryDTO setActive(boolean active) {
        isActive = active;
        return this;
    }
}
