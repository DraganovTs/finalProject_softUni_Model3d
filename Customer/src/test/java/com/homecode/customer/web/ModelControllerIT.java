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
public class ModelControllerIT {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @WithMockUser(username = "user@example.com")
    void testGetAddModel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/add-model"))
                .andExpect(status().isOk())
                .andExpect(view().name("model-add"))
                .andExpect(model().attributeExists("categories"));
    }

//    @Test
//    void testPostAddModel() throws Exception {
//        mockMvc.perform(post("/add-model")
//                .param("name","table")
//                .param("manufacturer","gucci")
//                .param("description","perfect")
//                .param("category","Side Table" )
//                .param("imageFile","1.jpg")
//                .param("zipFile","1.zip")
//                .with(csrf()))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/add-model"));
//    }


    @Test
    void testGetAllModels() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/models-all"))
                .andExpect(status().isOk())
                .andExpect(view().name("model-all"))
                .andExpect(model().attributeExists("modelsNumber"))
                .andExpect(model().attributeExists("allModels"));
    }

    @Test
    void testGetModelDetail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/model-detail/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("model-detail"))
                .andExpect(model().attributeExists("model"));

    }

    @Test
    @WithMockUser(username = "user@example.com")
    void testGetLikeModelWhitUser() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders
                        .get("/model-like/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/models-all"));
    }

    @Test
    void testGetLikeModelNoUser() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders
                        .get("/model-like/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithMockUser(username = "user@example.com")
    void testGetDownloadModelWhitUser() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders
                        .get("/download-model-user/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/download-model/1"));
    }

    @Test
    void testGetDownloadModelWhitNoUser() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders
                        .get("/download-model-user/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }
}
