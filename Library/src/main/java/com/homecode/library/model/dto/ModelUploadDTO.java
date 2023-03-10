package com.homecode.library.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ModelUploadDTO {
    @Size(min = 2,max = 20,message = "name must be between 2 and 20 characters")
    @NotBlank
    public String name;
    @Size(min = 2,max = 20,message = "name must be between 2 and 20 characters")
    @NotBlank
    public String manufacturer;
    @NotNull
    public String category;

    public String downloadLink;

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



    public String getDownloadLink() {
        return downloadLink;
    }

    public ModelUploadDTO setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
        return this;
    }
}
