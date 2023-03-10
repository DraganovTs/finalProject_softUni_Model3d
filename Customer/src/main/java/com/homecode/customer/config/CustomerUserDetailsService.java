package com.homecode.customer.config;

import com.homecode.library.model.UserEntity;
import com.homecode.library.model.UserRoleEntity;
import com.homecode.library.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.List;


public class CustomerUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomerUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findUserEntitiesByEmail(username)
                .map(this::map)
                .orElseThrow(() -> new UsernameNotFoundException("username not found!"));
    }


    private UserDetails map(UserEntity entity) {
        return new AppUserDetails(
                entity.getEmail(),
                entity.getPassword(),
                extractAuthorities(entity)
        )
                .setFirstName(entity.getFirstName());
    }


    private List<GrantedAuthority> extractAuthorities(UserEntity entity) {
        return entity.getRoles()
                .stream()
                .map(this::mapRole)
                .toList();
    }

    private GrantedAuthority mapRole(UserRoleEntity userRoleEntity) {
        return new SimpleGrantedAuthority("ROLE_" + userRoleEntity.getRole().name());
    }
}
