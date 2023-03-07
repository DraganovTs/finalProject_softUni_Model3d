package com.homecode.library.repository;


import com.homecode.library.model.UserRoleEntity;
import com.homecode.library.model.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository  extends JpaRepository<UserRoleEntity,Long> {

    UserRoleEntity findByRole(UserRoleEnum roleEnum);
}
