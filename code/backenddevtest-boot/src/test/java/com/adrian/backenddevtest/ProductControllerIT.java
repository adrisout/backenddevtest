package com.adrian.backenddevtest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith({MockitoExtension.class, SpringExtension.class})
@AutoConfigureMockMvc
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ProductControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getProductSimilar_whenRequestOk_return200OK() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/product/1/similar")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.[?(@.id=='2' && @.name=='Dress' && @.price==19.99 && @.availability==true)]").exists())
                .andExpect(jsonPath("$.[?(@.id=='4' && @.name=='Boots' && @.price==39.99 && @.availability==true)]").exists())
                .andExpect(jsonPath("$.[?(@.id=='3' && @.name=='Blazer' && @.price==29.99 && @.availability==false)]").exists());
    }

    @Test
    void getProductSimilar_whenRequestKo_return404NotFound() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/product/null/similar")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void getProductSimilar_whenRequestKo_return405NotAllowed() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/product/1/similar")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isMethodNotAllowed());
    }

}