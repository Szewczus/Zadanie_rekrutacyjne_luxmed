package com.example.demo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
class DogControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DogController dogController;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(dogController).build();
    }

    @Test
    void testGetDogs() throws Exception{
        final var mvcResult = mockMvc.perform(get("/dogs")).andExpect(status().isOk()).andReturn();
        assertEquals(mvcResult.getResponse().getStatus(), 200);
    }

    @Test
    void testAddDog() throws Exception{
        final var mvcResult = mockMvc.perform(post("/dog")
                .contentType("application/json")
                .content("{\"name\": \"Kola\",\n" +
                        "        \"age\": \"1\",\n" +
                        "        \"emailDogOwner\": \"ewus9999@gmail.com\"}")).andExpect(status().isOk()).andReturn();
        assertEquals(mvcResult.getResponse().getStatus(), 200);
    }

    @Test
    void testDeleteDog() throws Exception{
        final var mvcResult = mockMvc.perform(delete("/delete/dog")
                .contentType("application/json")
                .content("{\"id\": \"1\"}"))
                .andExpect(status().isOk()).andReturn();
        assertEquals(mvcResult.getResponse().getStatus(), 200);
    }

    @Test
    void testEditDog() throws Exception{
        final var mvcResult = mockMvc.perform(post("/edit/dog")
            .contentType("application/json")
            .content("{\"id\":2,\"name\":\"Kola\",\"age\":2,\"emailDogOwner\": \"ewus9999@gmail.com\"}"))
            .andExpect(status().isOk()).andReturn();
        assertEquals(mvcResult.getResponse().getStatus(), 200);

    }

}
