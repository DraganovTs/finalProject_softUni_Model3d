package com.homecode.library.model;

import com.homecode.library.model.enums.CategoryEnum;
import jakarta.persistence.*;

@Entity
@Table(name="categories")
public class CategoryModelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private CategoryEnum name;

    public CategoryModelEntity() {
    }

    public Long getId() {
        return id;
    }

    public CategoryModelEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public CategoryEnum getName() {
        return name;
    }

    public CategoryModelEntity setName(CategoryEnum name) {
        this.name = name;
        return this;
    }
}
