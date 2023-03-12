package com.homecode.library.model;

import com.homecode.library.model.enums.UserRoleEnum;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String firstName;
    private String lastName;
    @Column(nullable = false)
    private String password;

    @Column
    private String description;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<UserRoleEntity> roles = new ArrayList<>();
    @ManyToMany
    private List<ModelEntity> userUploadedModels = new ArrayList<>();
    @ManyToMany
    private List<ModelEntity> purchasedModels = new ArrayList<>();
    @ManyToMany
    private List<ModelEntity> likedModels = new ArrayList<>();


    public UserEntity() {
    }

    public UserEntity addRole(UserRoleEntity roleEntity) {
        this.roles.add(roleEntity);
        return this;
    }

    public Long getId() {
        return id;
    }

    public UserEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public List<UserRoleEntity> getRoles() {
        return roles;
    }

    public UserEntity setRoles(List<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    public List<ModelEntity> getPurchasedModels() {
        return purchasedModels;
    }

    public UserEntity setPurchasedModels(List<ModelEntity> purchasedModels) {
        this.purchasedModels = purchasedModels;
        return this;
    }

    public List<ModelEntity> getLikedModels() {
        return likedModels;
    }

    public UserEntity setLikedModels(List<ModelEntity> likedModels) {
        this.likedModels = likedModels;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UserEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<ModelEntity> getUserUploadedModels() {
        return userUploadedModels;
    }

    public UserEntity setUserUploadedModels(List<ModelEntity> userUploadedModels) {
        this.userUploadedModels = userUploadedModels;
        return this;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
