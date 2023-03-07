package com.homecode.library.service.impl;

import com.homecode.library.model.UserEntity;
import com.homecode.library.model.dto.UserRegisterDTO;
import com.homecode.library.repository.UserRepository;
import com.homecode.library.service.CustomerUserService;
import org.springframework.stereotype.Service;


@Service
public class CustomerUserServiceImpl implements CustomerUserService {

    private final UserRepository userRepository;

    public CustomerUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
}
