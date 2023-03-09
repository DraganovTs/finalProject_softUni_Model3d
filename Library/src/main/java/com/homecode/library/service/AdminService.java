package com.homecode.library.service;

import com.homecode.library.model.dto.AdminRegisterDTO;

public interface AdminService {

    boolean register(AdminRegisterDTO adminRegisterDTO);

    boolean confirmPassword(AdminRegisterDTO adminRegisterDTO);
}
