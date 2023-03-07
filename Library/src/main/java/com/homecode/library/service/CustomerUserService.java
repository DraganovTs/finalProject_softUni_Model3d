package com.homecode.library.service;


import com.homecode.library.model.dto.UserRegisterDTO;

public interface CustomerUserService {

    boolean register(UserRegisterDTO userDTO);

    boolean confirmPassword(UserRegisterDTO userDTO);
}
