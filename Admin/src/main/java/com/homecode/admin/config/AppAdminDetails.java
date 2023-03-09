package com.homecode.admin.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class AppAdminDetails extends User {

    private String fullName;

    public AppAdminDetails(String username, String password,
                          Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }


    public String getFullName() {
        return fullName;
    }

    public AppAdminDetails setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }
}
