package com.homecode.library.service.impl;

import com.homecode.library.model.ModelEntity;
import com.homecode.library.model.UserEntity;
import com.homecode.library.model.UserRoleEntity;
import com.homecode.library.model.dto.ModelUploadDTO;
import com.homecode.library.model.dto.UserAddRolesDto;
import com.homecode.library.model.dto.UserRegisterDTO;
import com.homecode.library.model.enums.UserRoleEnum;
import com.homecode.library.model.view.CustomerCreditsView;
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
    private final ModelServiceImpl modelService;

    @Autowired
    public CustomerUserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RoleServiceImpl roleService, ModelServiceImpl modelService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
        this.modelService = modelService;
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

    @Override
    public void dailyResetCredits() {
        List<UserEntity> users = this.userRepository.findAll();
        users.forEach(user -> user.setCredits(3));
        this.userRepository.saveAll(users);
    }

    @Override
    public boolean likeModel(String username, ModelEntity model) {
        UserEntity user = this.userRepository.findUserEntitiesByEmail(username).get();
        if (!user.getLikedModels().contains(model)){
        this.userRepository.save(user.addLikedModel(model));
        return true;
        }
        return false;
    }

    @Override
    public void userAddModel(String username, ModelUploadDTO modelUploadDTO) {
        try {
            UserEntity user = findUserByUsername(username);
            ModelEntity model = this.modelService.findByModelNameAndManufacturer(modelUploadDTO);
            user.addUploadedModel(model);
            this.userRepository.save(user);

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Override
    public boolean userDownloadModel(String username, Long modelID) {
        UserEntity user = this.userRepository.findUserEntitiesByEmail(username).get();
        if (user.getCredits() < 1) {
            return false;
        }
        ModelEntity model = this.modelService.findById(modelID);
        user.setCredits(user.getCredits() - 1);
        user.addDownloadModel(model);
        this.userRepository.save(user);
        return true;
    }

    @Override
    public CustomerCreditsView getUserCredits(String username) {
        UserEntity user = findUserByUsername(username);
        return this.modelMapper.map(user, CustomerCreditsView.class);
    }

}
