package com.homecode.library.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class UserRegisterDTO {
    @Size(min = 3, max = 20, message = "invalid username!(Must be between 3 and 20 characters")
    private String email;
    @Size(min = 3, max = 10, message = "invalid first name!(Must be between 3 and 10 characters")
    private String firstName;
    @Size(min = 3, max = 10, message = "invalid last name!(Must be between 3 and 10 characters")
    private String lastName;
    @Size(min = 3, max = 10, message = "invalid password(Must be between 5 and 14 characters")
    private String password;
    @Size(min = 3, max = 10, message = "invalid password(Must be between 5 and 14 characters")
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
