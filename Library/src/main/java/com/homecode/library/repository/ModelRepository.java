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

    @Query(value = "select * from models m where m.is_approved = true and category_id  order by m.uploaded_on asc " , nativeQuery = true)
    List<ModelEntity> findAllModels();

    @Query("select m from ModelEntity m where m.isApproved = false and m.category.isActive order by m.uploadedOn desc ")
    List<ModelEntity> getAllModelsForModerator();

    @Query("select m from ModelEntity m where  m.name like %?1% or m.manufacturer like %?1% or m.category.name like %?1% order by m.uploadedOn desc ")
    List<ModelEntity> findAllModelsByKeyword(String keyword);


}
