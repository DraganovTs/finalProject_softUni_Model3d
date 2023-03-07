package com.homecode.library.service;

import com.homecode.library.model.UserRoleEntity;
import com.homecode.library.model.enums.UserRoleEnum;

import java.util.List;

public interface RoleService {

    boolean isEmpty();

    void initRoles();

    List<UserRoleEntity>findAll();

    UserRoleEntity findByRole(UserRoleEnum roleEnum);
}
