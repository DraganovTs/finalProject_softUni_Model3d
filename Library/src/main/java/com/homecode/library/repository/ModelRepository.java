package com.homecode.library.repository;

import com.homecode.library.model.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModelRepository extends JpaRepository<ModelEntity, Long> {


    Optional<ModelEntity> findByNameAndManufacturer(String name, String manufacturer);

    @Query(value = "select * from models m where m.is_approved = true order by m.uploaded_on desc ", nativeQuery = true)
    List<ModelEntity> findAllModels();

    @Query(value = "select * from models m where m.is_approved = false order by m.uploaded_on", nativeQuery = true)
    List<ModelEntity> getAllModelsForModerator();

    @Query("select m from ModelEntity m where m.name like %?1%")
    List<ModelEntity> findAllModelsByKeyword(String keyword);

}
