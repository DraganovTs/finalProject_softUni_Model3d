package com.homecode.library.service.impl;

import com.homecode.library.model.UserEntity;
import com.homecode.library.model.UserRoleEntity;
import com.homecode.library.model.dto.UserAddRolesDto;
import com.homecode.library.model.dto.UserRegisterDTO;
import com.homecode.library.model.enums.UserRoleEnum;
import com.homecode.library.repository.UserRepository;
import com.homecode.library.service.CustomerUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CustomerUserServiceImpl implements CustomerUserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleServiceImpl roleService;

    @Autowired
    public CustomerUserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RoleServiceImpl roleService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
    }

    @Override
    public boolean register(UserRegisterDTO userDTO) {
        if (this.userRepository.findUserEntitiesByEmail(userDTO.getEmail()).isPresent()) {
            return false;
        }

        var user = new UserEntity()
                .setFirstName(userDTO.getFirstName())
                .setLastName(userDTO.getLastName())
                .setPassword(userDTO.getPassword())
                .setEmail(userDTO.getEmail());

        this.userRepository.save(user);

        return true;
    }

    @Override
    public boolean confirmPassword(UserRegisterDTO userRegisterDTO) {
        return userRegisterDTO.getPassword().equals(userRegisterDTO.getRepeatPassword());
    }

    @Override
    public UserEntity findUserByUsername(String username) {
        return this.userRepository.findUserEntitiesByEmail(username).get();
    }

    @Override
    public boolean findUserByUsername(UserAddRolesDto userAddRolesDto) {
        UserEntity user = this.userRepository.findUserEntitiesByEmail(userAddRolesDto.getEmail()).get();
        return user.getRoles().contains(this.roleService.findByRole(UserRoleEnum.MODERATOR));
    }

    @Override
    public List<UserAddRolesDto> findAllModerators() {
        return this.userRepository.findAll().stream()
                .filter(u -> u.getRoles()
                        .contains(this.roleService.findByRole(UserRoleEnum.MODERATOR)))
                .map(u -> this.modelMapper.map(u, UserAddRolesDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteModeratorRoleById(Long id) {

        UserEntity user = this.userRepository.findById(id).get();
        user.setRoles(new ArrayList<>());

        this.userRepository.save(user);
    }

    @Override
    public void createModerator(UserAddRolesDto userAddRolesDto) {
        UserRoleEntity moderatorRole = this.roleService.findByRole(UserRoleEnum.MODERATOR);

        UserEntity user = this.userRepository.findUserEntitiesByEmail(userAddRolesDto.getEmail()).get();
        user.addRole(moderatorRole);
        this.userRepository.save(user);
    }
}
