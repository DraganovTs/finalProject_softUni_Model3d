package com.homecode.library.service.impl;

import com.homecode.library.model.AdminEntity;
import com.homecode.library.model.UserEntity;
import com.homecode.library.model.dto.UserAddRolesDto;
import com.homecode.library.model.dto.AdminRegisterDTO;
import com.homecode.library.repository.AdminRepository;
import com.homecode.library.service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final RoleServiceImpl roleService;
    private final ModelMapper modelMapper;

    private final CustomerUserServiceImpl customerUserService;


    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository, RoleServiceImpl roleService, ModelMapper modelMapper, CustomerUserServiceImpl customerUserService) {
        this.adminRepository = adminRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.customerUserService = customerUserService;
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

    @Override
    public List<UserAddRolesDto> findAllAdmins() {
        return this.adminRepository.findAll()
                .stream()
                .map(a->this.modelMapper.map(a, UserAddRolesDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean findAdminEntityByEmail(UserAddRolesDto adminAddRolesDto) {
        return this.adminRepository.findAdminEntityByEmail(adminAddRolesDto.getEmail()).isPresent();
    }

    @Override
    public void deleteAdminRole(Long id) {
        this.adminRepository.deleteById(id);
    }

    @Override
    public void addAdminRole(UserAddRolesDto adminAddRolesDto) {
        UserEntity user = this.customerUserService.findUserByUsername(adminAddRolesDto.getEmail());
        AdminEntity admin = new AdminEntity()
                .setEmail(user.getEmail())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setPassword(user.getPassword())
                .setRoles(roleService.findAll());

        this.adminRepository.save(admin);
    }


}
