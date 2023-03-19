package com.homecode.library.repository;

import com.homecode.library.model.CommentEntity;
import com.homecode.library.model.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    List<CommentEntity> findAllByModel(ModelEntity model);
}
