package com.homecode.library.model;

import jakarta.persistence.*;


import java.time.LocalDateTime;


@Entity
@Table(name = "models")
public class ModelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
    @Column(nullable = false)
    private String manufacturer;
    @Column(nullable = false)
    private String downloadLink;
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;
    @Column
    private int likes = 0;
    private int sold = 0;
    @ManyToOne(fetch = FetchType.EAGER)
    private CategoryModelEntity category;
    private boolean isApproved = false;
    @ManyToOne
    private UserEntity owner;
    private LocalDateTime uploadedOn = LocalDateTime.now();


    public ModelEntity() {
    }

    public Long getId() {
        return id;
    }

    public ModelEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public ModelEntity setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public ModelEntity setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
        return this;
    }

    public int getLikes() {
        return likes;
    }

    public ModelEntity setLikes(int likes) {
        this.likes = likes;
        return this;
    }

    public int getSold() {
        return sold;
    }

    public ModelEntity setSold(int sold) {
        this.sold = sold;
        return this;
    }

    public CategoryModelEntity getCategory() {
        return category;
    }

    public ModelEntity setCategory(CategoryModelEntity category) {
        this.category = category;
        return this;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public ModelEntity setApproved(boolean approved) {
        isApproved = approved;
        return this;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public ModelEntity setOwner(UserEntity owner) {
        this.owner = owner;
        return this;
    }

    public LocalDateTime getUploadedOn() {
        return uploadedOn;
    }

    public ModelEntity setUploadedOn(LocalDateTime uploadedOn) {
        this.uploadedOn = uploadedOn;
        return this;
    }

    public String getName() {
        return name;
    }

    public ModelEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getImage() {
        return image;
    }

    public ModelEntity setImage(String image) {
        this.image = image;
        return this;
    }
}
