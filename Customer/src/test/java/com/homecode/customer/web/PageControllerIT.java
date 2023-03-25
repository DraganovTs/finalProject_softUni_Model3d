package com.homecode.customer.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@SpringBootTest
@AutoConfigureMockMvc
public class PageControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetHome() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("allModels"));
    }

    @Test
    void testGetAboutUs() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/about-us"))
                .andExpect(status().isOk())
                .andExpect(view().name("about-us"));
    }

    @Test
    @WithMockUser(username = "user@example.com")
    void testGetUserAccount() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user-acount"))
                .andExpect(status().isOk())
                .andExpect(view().name("/user-acount"))
                .andExpect(model().attributeExists("userProfile"))
                .andExpect(model().attributeExists("userModels"))
                .andExpect(model().attributeExists("userDownloadedModels"))
                .andExpect(model().attributeExists("userLikedModels"));
    }

    @Test
    void testPostSubscribe() throws Exception {
        mockMvc.perform(post("/subscribe")
                .param("email","test@test.com")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

}
