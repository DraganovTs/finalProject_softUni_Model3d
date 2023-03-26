package com.homecode.library.repository;

import com.homecode.library.model.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModelRepository extends JpaRepository<ModelEntity, Long> {


    Optional<ModelEntity> findByNameAndManufacturer(String name, String manufacturer);

    @Query(value = "select m from ModelEntity m where m.isApproved = true and m.category.isActive order by m.uploadedOn desc ")
    List<ModelEntity> findAllModels();

    @Query(value = "select m from ModelEntity m where m.isApproved = false and m.category.isActive order by m.uploadedOn desc ")
    List<ModelEntity> getAllModelsForModerator();

    @Query("select m from ModelEntity m where m.isApproved =true and m.name like %?1% or m.manufacturer like %?1% or m.category.name like %?1%")
    List<ModelEntity> findAllModelsByKeyword(String keyword);


}
