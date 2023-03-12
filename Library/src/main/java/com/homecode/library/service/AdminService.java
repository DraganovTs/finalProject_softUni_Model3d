package com.homecode.library.service;

import com.homecode.library.model.dto.UserAddRolesDto;
import com.homecode.library.model.dto.AdminRegisterDTO;

import java.util.List;

public interface AdminService {

    boolean register(AdminRegisterDTO adminRegisterDTO);

    boolean confirmPassword(AdminRegisterDTO adminRegisterDTO);

    List<UserAddRolesDto> findAllAdmins();

    boolean findAdminEntityByEmail(UserAddRolesDto adminAddRolesDto);

    void deleteAdminRole(Long id);
    void addAdminRole(UserAddRolesDto adminAddRolesDto);



}
