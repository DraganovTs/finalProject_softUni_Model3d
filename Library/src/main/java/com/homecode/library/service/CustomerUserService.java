package com.homecode.library.service;


import com.homecode.library.model.UserEntity;
import com.homecode.library.model.dto.UserAddRolesDto;
import com.homecode.library.model.dto.UserRegisterDTO;

import java.util.List;

public interface CustomerUserService {

    boolean register(UserRegisterDTO userDTO);

    boolean confirmPassword(UserRegisterDTO userDTO);

    UserEntity findUserByUsername(String username);
    boolean findUserByUsername(UserAddRolesDto userAddRolesDto);


    List<UserAddRolesDto> findAllModerators();

    void deleteModeratorRoleById(Long id);

    void createModerator(UserAddRolesDto userAddRolesDto);
}
