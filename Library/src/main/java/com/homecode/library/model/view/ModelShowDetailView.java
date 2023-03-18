package com.homecode.library.model.view;


import com.homecode.library.model.CategoryModelEntity;
import com.homecode.library.model.ImageFileEntity;
import com.homecode.library.model.UserEntity;
import com.homecode.library.model.ZipFileEntity;

import java.time.LocalDateTime;

public class ModelShowDetailView {


    private Long id;
    private String name;

    private String manufacturer;

    private String description;

    private ZipFileEntity zipModel;

    private ImageFileEntity imageModel;

    private int likes;
    private int downloaded;

    private CategoryModelEntity category;
    private boolean isApproved;

    private UserEntity owner;
    private LocalDateTime uploadedOn;

    public ModelShowDetailView() {
    }


    public Long getId() {
        return id;
    }

    public ModelShowDetailView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ModelShowDetailView setName(String name) {
        this.name = name;
        return this;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public ModelShowDetailView setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ModelShowDetailView setDescription(String description) {
        this.description = description;
        return this;
    }

    public ZipFileEntity getZipModel() {
        return zipModel;
    }

    public ModelShowDetailView setZipModel(ZipFileEntity zipModel) {
        this.zipModel = zipModel;
        return this;
    }

    public ImageFileEntity getImageModel() {
        return imageModel;
    }

    public ModelShowDetailView setImageModel(ImageFileEntity imageModel) {
        this.imageModel = imageModel;
        return this;
    }

    public int getLikes() {
        return likes;
    }

    public ModelShowDetailView setLikes(int likes) {
        this.likes = likes;
        return this;
    }

    public int getDownloaded() {
        return downloaded;
    }

    public ModelShowDetailView setDownloaded(int downloaded) {
        this.downloaded = downloaded;
        return this;
    }

    public CategoryModelEntity getCategory() {
        return category;
    }

    public ModelShowDetailView setCategory(CategoryModelEntity category) {
        this.category = category;
        return this;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public ModelShowDetailView setApproved(boolean approved) {
        isApproved = approved;
        return this;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public ModelShowDetailView setOwner(UserEntity owner) {
        this.owner = owner;
        return this;
    }

    public LocalDateTime getUploadedOn() {
        return uploadedOn;
    }

    public ModelShowDetailView setUploadedOn(LocalDateTime uploadedOn) {
        this.uploadedOn = uploadedOn;
        return this;
    }
}
