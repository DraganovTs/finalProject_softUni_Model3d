package com.homecode.library.repository;

import com.homecode.library.model.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity,Long> {

    Optional<AdminEntity> findAdminEntityByEmail(String email);
}
