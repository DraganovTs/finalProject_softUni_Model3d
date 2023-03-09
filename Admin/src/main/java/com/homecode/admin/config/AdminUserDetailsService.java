package com.homecode.admin.config;


import com.homecode.library.model.AdminEntity;
import com.homecode.library.model.UserRoleEntity;
import com.homecode.library.repository.AdminRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;


public class AdminUserDetailsService implements UserDetailsService {
    private final AdminRepository adminRepository;

    public AdminUserDetailsService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return
                adminRepository.
                        findAdminEntityByEmail(username).
                        map(this::map).
                        orElseThrow(() -> new UsernameNotFoundException("User with name " + username + " not found!"));
    }

    private UserDetails map(AdminEntity adminEntity) {
        return new AppAdminDetails(
                adminEntity.getEmail(),
                adminEntity.getPassword(),
                extractAuthorities(adminEntity)
        ).

                setFullName(adminEntity.getFirstName() + " " + adminEntity.getLastName());
    }

    private List<GrantedAuthority> extractAuthorities(AdminEntity adminEntity) {
        return adminEntity.
                getRoles().
                stream().
                map(this::mapRole).
                toList();
    }

    private GrantedAuthority mapRole(UserRoleEntity userRoleEntity) {
        return new SimpleGrantedAuthority("ROLE_" + userRoleEntity.getRole().name());
    }
}
