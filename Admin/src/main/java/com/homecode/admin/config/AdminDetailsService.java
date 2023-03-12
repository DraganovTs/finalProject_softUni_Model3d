package com.homecode.admin.config;

import com.homecode.library.model.AdminEntity;
import com.homecode.library.model.UserRoleEntity;
import com.homecode.library.repository.AdminRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
public class AdminDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;

    public AdminDetailsService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return adminRepository.findAdminEntityByEmail(username)
                .map(this::map)
                .orElseThrow(() -> new UsernameNotFoundException("not found"));
    }

    private UserDetails map(AdminEntity entity) {
        return new User(
                entity.getEmail(),
                entity.getPassword(),
                extractAuthorities(entity)
        );
    }

    private List<GrantedAuthority> extractAuthorities(AdminEntity entity) {
        return entity.getRoles()
                .stream()
                .map(this::mapRole)
                .toList();
    }

    private GrantedAuthority mapRole(UserRoleEntity userRoleEntity) {
        return new SimpleGrantedAuthority("ROLE_" + userRoleEntity.getRole().name());
    }


}
