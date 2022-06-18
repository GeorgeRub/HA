package com.ha.back.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:test.application.properties"
)
@AutoConfigureMockMvc
class TestControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @Test
    @DisplayName("test get all")
    void allAccess() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/test/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Public Content."));

//        ResponseEntity<String> response = restTemplate.getForEntity(
//                new URL("http://localhost:" + port +"/api/test/all").toString(), String.class
//        );
//
//        assertEquals("Public Content.", response.getBody());

    }
}