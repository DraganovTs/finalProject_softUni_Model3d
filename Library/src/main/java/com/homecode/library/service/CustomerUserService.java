package com.homecode.library.service;


import com.homecode.library.model.ModelEntity;
import com.homecode.library.model.UserEntity;
import com.homecode.library.model.dto.ModelUploadDTO;
import com.homecode.library.model.dto.UserAddRolesDto;
import com.homecode.library.model.dto.UserRegisterDTO;
import com.homecode.library.model.view.CustomerCreditsView;

import java.util.List;

public interface CustomerUserService {

    boolean register(UserRegisterDTO userDTO);

    boolean confirmPassword(UserRegisterDTO userDTO);

    UserEntity findUserByUsername(String username);

    boolean findUserByUsername(UserAddRolesDto userAddRolesDto);


    List<UserAddRolesDto> findAllModerators();

    void deleteModeratorRoleById(Long id);

    void createModerator(UserAddRolesDto userAddRolesDto);

    void dailyResetCredits();

    boolean likeModel(String username, ModelEntity model);


    void userAddModel(String name, ModelUploadDTO modelUploadDTO);

    boolean userDownloadModel(String username, Long modelID);

    CustomerCreditsView getUserCredits(String username);


}
