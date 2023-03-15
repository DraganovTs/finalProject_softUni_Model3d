package com.homecode.library.model.view;

import org.springframework.web.multipart.MultipartFile;

public class ModelsShowAllView {

    public Long id;
    public String name;
    public MultipartFile image;

    public Long getId() {
        return id;
    }

    public ModelsShowAllView() {
    }

    public ModelsShowAllView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ModelsShowAllView setName(String name) {
        this.name = name;
        return this;
    }

    public MultipartFile getImage() {
        return image;
    }

    public ModelsShowAllView setImage(MultipartFile image) {
        this.image = image;
        return this;
    }
}
