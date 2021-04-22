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
class DogOwnerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DogOwnerController dogOwnerController;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(dogOwnerController).build();
    }

    @Test
    void testGetDogOwners() throws Exception{
        final var mvcResult = mockMvc.perform(get("/dogowners")).andExpect(status().isOk()).andReturn();
        assertEquals(mvcResult.getResponse().getStatus(), 200);
    }

    @Test
    void testAddDogOwner() throws Exception{
        final var mvcResult = mockMvc.perform(post("/dogowner")
                .contentType("application/json")
                .content("{\"name\": \"Anna\", \"surname\": \"Kowalska\", \"email\": \"ania@gmail.com\"}")).andExpect(status().isOk()).andReturn();
        assertEquals(mvcResult.getResponse().getStatus(), 200);
    }

    @Test
    void testDeleteDogOwner() throws Exception{
        final var mvcResult = mockMvc.perform(delete("/delete/dogowner")
                .contentType("application/json")
                .content("{\"id\": 1}")).andExpect(status().isOk()).andReturn();
        assertEquals(mvcResult.getResponse().getStatus(), 200);
    }
    @Test
    void testEditDogOwner() throws Exception{
        final var mvcResult = mockMvc.perform(post("/edit/dogowner")
                .contentType("application/json")
                .content("{\"name\": \"Ewus\", \"surname\": \"Szewczus\", \"email\": \"ewus9999@gmail.com\"}")).andExpect(status().isOk()).andReturn();
        assertEquals(mvcResult.getResponse().getStatus(), 200);
    }


}
