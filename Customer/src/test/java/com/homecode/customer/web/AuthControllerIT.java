package com.homecode.customer.web;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testRegisterCorrect() throws Exception {
        mockMvc.perform(post("/register")
                .param("email","test@example.com")
                .param("firstName","test")
                .param("lastName","testov")
                .param("password","12345")
                .param("repeatPassword","12345")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    void testRegisterNotCorrect() throws Exception {
        mockMvc.perform(post("/register")
                        .param("email","1")
                        .param("firstName","test")
                        .param("lastName","testov")
                        .param("password","12345")
                        .param("repeatPassword","12345")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/register"));
    }

    @Test
    void testRegisterNotCorrectDifferentPasswords() throws Exception {
        mockMvc.perform(post("/register")
                        .param("email","test@example.com")
                        .param("firstName","test")
                        .param("lastName","testov")
                        .param("password","12345")
                        .param("repeatPassword","54321")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/register"));
    }

    @Test
    void testGetRegister() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("user-register"));
    }

    @Test
    void testLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("user-login"));
    }

    @Test
    void testPostLogin() throws Exception {
        mockMvc.perform(post("/login-error")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }



}
