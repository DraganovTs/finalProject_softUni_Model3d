package com.homecode.library.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "models", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "manufacturer"}))
public class ModelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String manufacturer;
    @Column
    private String description;
    @OneToOne
    private ZipFileEntity zipModel;
    @OneToOne(fetch = FetchType.EAGER)
    private ImageFileEntity imageModel;
    @Column
    private int likes = 0;
    private int downloaded = 0;
    @ManyToOne(fetch = FetchType.EAGER)
    private CategoryModelEntity category;
    private boolean isApproved = false;
    @ManyToOne
    private UserEntity owner;
    private LocalDateTime uploadedOn = LocalDateTime.now();

    @OneToMany(targetEntity = CommentEntity.class, mappedBy = "model", cascade = CascadeType.ALL)
    private Set<CommentEntity> comments = new HashSet<>();

    public ModelEntity() {
    }

    public Long getId() {
        return id;
    }

    public ModelEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ModelEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public ModelEntity setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public ZipFileEntity getZipModel() {
        return zipModel;
    }

    public ModelEntity setZipModel(ZipFileEntity zipModel) {
        this.zipModel = zipModel;
        return this;
    }

    public ImageFileEntity getImageModel() {
        return imageModel;
    }

    public ModelEntity setImageModel(ImageFileEntity imageModel) {
        this.imageModel = imageModel;
        return this;
    }

    public int getLikes() {
        return likes;
    }

    public ModelEntity setLikes(int likes) {
        this.likes = likes;
        return this;
    }

    public int getDownloaded() {
        return downloaded;
    }

    public ModelEntity setDownloaded(int sold) {
        this.downloaded = sold;
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

    public String getDescription() {
        return description;
    }

    public ModelEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public Set<CommentEntity> getComments() {
        return comments;
    }

    public ModelEntity setComments(Set<CommentEntity> comments) {
        this.comments = comments;
        return this;
    }
}
