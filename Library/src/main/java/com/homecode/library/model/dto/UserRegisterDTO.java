package com.homecode.library.model.dto;

import jakarta.validation.constraints.Size;

import static com.homecode.library.constatnts.Messages.*;

public class UserRegisterDTO {
    @Size(min = 3, max = 20, message = INVALID_USERNAME)
    private String email;
    @Size(min = 3, max = 10, message = INVALID_FIRST_NAME)
    private String firstName;
    @Size(min = 3, max = 10, message = INVALID_LAST_NAME)
    private String lastName;
    @Size(min = 3, max = 10, message = INVALID_PASSWORD)
    private String password;
    @Size(min = 3, max = 10, message = INVALID_PASSWORD)
    private String repeatPassword;
    public UserRegisterDTO() {
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserRegisterDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegisterDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public UserRegisterDTO setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
        return this;
    }
}
