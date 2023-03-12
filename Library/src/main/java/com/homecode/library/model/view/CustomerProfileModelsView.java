package com.homecode.library.model.view;

public class CustomerProfileModelsView {
    public String name;
    public String manufacturer;
    private String image;
    private int likes;
    private int sold;

    public CustomerProfileModelsView() {
    }

    public String getName() {
        return name;
    }

    public CustomerProfileModelsView setName(String name) {
        this.name = name;
        return this;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public CustomerProfileModelsView setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public String getImage() {
        return image;
    }

    public CustomerProfileModelsView setImage(String image) {
        this.image = image;
        return this;
    }

    public int getLikes() {
        return likes;
    }

    public CustomerProfileModelsView setLikes(int likes) {
        this.likes = likes;
        return this;
    }

    public int getSold() {
        return sold;
    }

    public CustomerProfileModelsView setSold(int sold) {
        this.sold = sold;
        return this;
    }
}