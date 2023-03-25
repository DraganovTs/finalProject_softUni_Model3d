package com.homecode.customer.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ModeratorControllerIT {


    @Autowired
    private MockMvc mockMvc;


    @Test
    @WithMockUser(username = "moderator@example.com")
    void testGetModerator() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/moderator"))
                .andExpect(status().isOk())
                .andExpect(view().name("all-models-moderator"))
                .andExpect(model().attributeExists("modelsNumber"))
                .andExpect(model().attributeExists("allModels"));
    }
    @Test
    void testGetModeratorNoUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/moderator"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));

    }

    @Test
    @WithMockUser(username = "moderator@example.com")
    void testGetModelApprove() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/model-approve/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/moderator"));
    }

    @Test
    void testGetModelApproveNoUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/model-approve/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }
}
