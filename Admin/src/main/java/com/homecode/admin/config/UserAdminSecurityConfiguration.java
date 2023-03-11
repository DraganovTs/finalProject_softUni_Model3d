package com.homecode.admin.config;

import com.homecode.library.repository.AdminRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class UserAdminSecurityConfiguration {

    private final AdminRepository adminRepository;

    public UserAdminSecurityConfiguration(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //TODO change to authenticated

        http.
                authorizeHttpRequests().
                requestMatchers( "/login", "/register", "/error" , "/categories").permitAll().
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
                clearAuthentication(true)
                .and()
                .csrf().disable();

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .requestMatchers("/css/**", "/js/**", "/images/**", "/libs/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserAdminDetailsService(adminRepository);
    }
}
