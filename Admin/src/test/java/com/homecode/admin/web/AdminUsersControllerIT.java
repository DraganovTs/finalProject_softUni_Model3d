package com.homecode.admin.web;
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

@SpringBootTest
@AutoConfigureMockMvc
public class AdminUsersControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "admin@example.com")
    void testGetUserRolesAdmin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user-roles-admin"))
                .andExpect(status().isOk())
                .andExpect(view().name("user-roles-admin"))
                .andExpect(model().attributeExists("userAdmins"));
    }

    @Test
    void testGetUserRolesAdminNoUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user-roles-admin"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithMockUser(username = "admin@example.com")
    void testPostUserRolesAdmin() throws Exception {
        mockMvc.perform(post("/user-roles-admin")
                        .param("id","1")
                        .param("email","test@test.com")
                        .param("firstName","test")
                        .param("lastName","testov")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user-roles-admin"));
    }

    @Test
    @WithMockUser(username = "admin@example.com")
    void testGetDeleteAdminFromList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/delete-admin-from-list/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user-roles-admin"));
    }

    @Test
    @WithMockUser(username = "admin@example.com")
    void testGetUserRolesModerator() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user-roles-moderator"))
                .andExpect(status().isOk())
                .andExpect(view().name("user-roles-moderator"))
                .andExpect(model().attributeExists("usersModerators"));
    }

    @Test
    @WithMockUser(username = "admin@example.com")
    void testPostUserRolesModerator() throws Exception {
        mockMvc.perform(post("/user-roles-moderator")
                        .param("id","1")
                        .param("email","test@test.com")
                        .param("firstName","test")
                        .param("lastName","testov")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user-roles-moderator"));
    }


}
