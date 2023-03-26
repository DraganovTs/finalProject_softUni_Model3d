package com.homecode.library.service.impl;

import com.homecode.library.model.UserRoleEntity;
import com.homecode.library.model.enums.UserRoleEnum;
import com.homecode.library.repository.RoleRepository;
import com.homecode.library.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public boolean isEmpty() {
        return this.roleRepository.count()==0;
    }

    @Override
    public void initRoles() {
            var moderatorRole = new UserRoleEntity().setRole(UserRoleEnum.MODERATOR);
            var adminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);

            List<UserRoleEntity> rolesToSave = new ArrayList<>();
            rolesToSave.add(moderatorRole);
            rolesToSave.add(adminRole);

            roleRepository.saveAll(rolesToSave);
            ;

    }

    @Override
    public List<UserRoleEntity> findAll() {
        return this.roleRepository.findAll();
    }

    @Override
    public UserRoleEntity findByRole(UserRoleEnum roleEnum) {
        return this.roleRepository.findByRole(roleEnum);
    }
}
