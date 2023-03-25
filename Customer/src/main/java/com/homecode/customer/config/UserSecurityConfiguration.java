package com.homecode.customer.config;

import com.homecode.library.repository.UserRepository;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.homecode.customer.constants.UrlConstants.STATIC_RESOURCES;
import static com.homecode.customer.constants.UrlConstants.STATIC_URL_PERMIT;

@Configuration
public class UserSecurityConfiguration {

    private final UserRepository userRepository;

    public UserSecurityConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.
                authorizeHttpRequests().
                requestMatchers(STATIC_RESOURCES).permitAll()
                .requestMatchers(STATIC_URL_PERMIT).permitAll().
                anyRequest().permitAll().
                and().
                formLogin()
                .loginPage("/login")
                .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                .defaultSuccessUrl("/")
                .failureUrl("/login?error=true").
                and().
                logout().
                logoutUrl("/logout").
                logoutSuccessUrl("/").
                deleteCookies("JSESSIONID").
                clearAuthentication(true);


        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomerUserDetailsService(userRepository);
    }
}
