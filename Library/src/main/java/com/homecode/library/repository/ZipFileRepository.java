package com.homecode.library.repository;

import com.homecode.library.model.ZipFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZipFileRepository extends JpaRepository<ZipFileEntity, Long> {
}
