package com.homecode.library.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public class ModelUploadDTO {
    @Size(min = 2, max = 20, message = "name must be between 2 and 20 characters")
    @NotBlank
    private String name;
    @Size(min = 2, max = 20, message = "name must be between 2 and 20 characters")
    @NotBlank
    private String manufacturer;

    @Size(min = 5, max = 50,message = "description must be between 5 and 50 characters")
    private String description;
    @NotNull
    private String category;

    private MultipartFile imageFile;
    private MultipartFile zipFile;

    public ModelUploadDTO() {
    }

    public String getName() {
        return name;
    }

    public ModelUploadDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public ModelUploadDTO setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public ModelUploadDTO setCategory(String category) {
        this.category = category;
        return this;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public ModelUploadDTO setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
        return this;
    }

    public MultipartFile getZipFile() {
        return zipFile;
    }

    public ModelUploadDTO setZipFile(MultipartFile zipFile) {
        this.zipFile = zipFile;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ModelUploadDTO setDescription(String description) {
        this.description = description;
        return this;
    }
}
