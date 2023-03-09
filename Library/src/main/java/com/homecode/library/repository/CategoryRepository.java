package com.homecode.library.repository;

import com.homecode.library.model.CategoryModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModelEntity,Long> {

    Optional<CategoryModelEntity>findByName(String name);

}
