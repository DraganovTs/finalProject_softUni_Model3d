package com.homecode.library.model.view;

import com.homecode.library.model.ModelEntity;

import java.util.List;

public class CustomerProfileView {

    private String email;
    private String fistName;
    private String lastName;
    private List<ModelEntity> downloadedModels;
    private List<ModelEntity> likedModels;
    private List<ModelEntity> userModels;

    public CustomerProfileView() {
    }

    public String getEmail() {
        return email;
    }

    public CustomerProfileView setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFistName() {
        return fistName;
    }

    public CustomerProfileView setFistName(String fistName) {
        this.fistName = fistName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CustomerProfileView setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public List<ModelEntity> getDownloadedModels() {
        return downloadedModels;
    }

    public CustomerProfileView setDownloadedModels(List<ModelEntity> downloadedModels) {
        this.downloadedModels = downloadedModels;
        return this;
    }

    public List<ModelEntity> getLikedModels() {
        return likedModels;
    }

    public CustomerProfileView setLikedModels(List<ModelEntity> likedModels) {
        this.likedModels = likedModels;
        return this;
    }

    public List<ModelEntity> getUserModels() {
        return userModels;
    }

    public CustomerProfileView setUserModels(List<ModelEntity> userModels) {
        this.userModels = userModels;
        return this;
    }
}
