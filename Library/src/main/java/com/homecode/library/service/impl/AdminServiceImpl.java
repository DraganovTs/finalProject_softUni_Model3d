package com.homecode.library.service.impl;

import com.homecode.library.model.AdminEntity;
import com.homecode.library.model.dto.AdminRegisterDTO;
import com.homecode.library.repository.AdminRepository;
import com.homecode.library.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final RoleServiceImpl roleService;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository, RoleServiceImpl roleService) {
        this.adminRepository = adminRepository;
        this.roleService = roleService;
    }

    @Override
    public boolean register(AdminRegisterDTO adminRegisterDTO) {

        if (this.adminRepository.findAdminEntityByEmail(adminRegisterDTO.getEmail()).isPresent()) {
            return false;
        }

        var admin = new AdminEntity()
                .setFirstName(adminRegisterDTO.getFirstName())
                .setLastName(adminRegisterDTO.getLastName())
                .setPassword(adminRegisterDTO.getPassword())
                .setEmail(adminRegisterDTO.getEmail())
                .setRoles(this.roleService.findAll());

        this.adminRepository.save(admin);


        return true;
    }

    @Override
    public boolean confirmPassword(AdminRegisterDTO adminRegisterDTO) {
        return adminRegisterDTO.getPassword().equals(adminRegisterDTO.getRepeatPassword());
    }
}
