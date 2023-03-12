package com.homecode.library.model.dto;

public class UserAddRolesDto {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;

    public UserAddRolesDto() {
    }

    public Long getId() {
        return id;
    }

    public UserAddRolesDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserAddRolesDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserAddRolesDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserAddRolesDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
