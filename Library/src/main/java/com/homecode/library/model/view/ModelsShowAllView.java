package com.homecode.library.model.view;

public class ModelsShowAllView {

    public Long id;
    public String name;
    public String image;

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

    public String getImage() {
        return image;
    }

    public ModelsShowAllView setImage(String image) {
        this.image = image;
        return this;
    }
}
