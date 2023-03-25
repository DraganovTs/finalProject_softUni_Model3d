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
public class AdminIpControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "admin@example.com")
    void testGetUserBan() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user-ban"))
                .andExpect(status().isOk())
                .andExpect(view().name("user-ban"))
                .andExpect(model().attributeExists("addresses"));
    }
    @Test
    void testGetUserBanNoUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user-ban"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithMockUser(username = "admin@example.com")
    void testPostUserBan() throws Exception {
        mockMvc.perform(post("/user-ban")
                        .param("id","1")
                        .param("ipAddress","142.25.21.23")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user-ban"));
    }

}
