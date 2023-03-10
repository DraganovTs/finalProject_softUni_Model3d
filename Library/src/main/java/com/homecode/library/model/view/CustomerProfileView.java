package com.homecode.library.model.view;

import com.homecode.library.model.ModelEntity;

import java.util.List;

public class CustomerProfileView {

    private String email;
    private String fistName;
    private String lastName;
    private List<ModelEntity> purchaseModels;
    private List<ModelEntity> likedModels;

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

    public List<ModelEntity> getPurchaseModels() {
        return purchaseModels;
    }

    public CustomerProfileView setPurchaseModels(List<ModelEntity> purchaseModels) {
        this.purchaseModels = purchaseModels;
        return this;
    }

    public List<ModelEntity> getLikedModels() {
        return likedModels;
    }

    public CustomerProfileView setLikedModels(List<ModelEntity> likedModels) {
        this.likedModels = likedModels;
        return this;
    }
}
