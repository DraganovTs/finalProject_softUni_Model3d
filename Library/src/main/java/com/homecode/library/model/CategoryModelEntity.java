package com.homecode.library.model;

import jakarta.persistence.*;

@Entity
@Table(name="categories")
public class CategoryModelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private boolean isDeleted = false;

    private boolean isActive = true;

    public CategoryModelEntity() {
    }

    public Long getId() {
        return id;
    }

    public CategoryModelEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CategoryModelEntity setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public CategoryModelEntity setDeleted(boolean deleted) {
        isDeleted = deleted;
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    public CategoryModelEntity setActive(boolean active) {
        isActive = active;
        return this;
    }
}
