package com.ha.back.controllers.account;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:test.application.properties"
)
@WithMockUser(username = "george", roles = {"USER", "ADMIN"}, password = "12345678")
@Sql(value = {"/roles_create.sql", "/user_create.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/roles_drop.sql", "/users_drop.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class AccountListApiTest {

    @Autowired
    public MockMvc mockMvc;

    @Test
    @WithMockUser(username = "george", password = "12345678")
    void getAccountList() throws Exception {
        mockMvc.perform(
                get("/api/account/all")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getAccountListWith403Error() throws Exception {
        mockMvc.perform(
                get("/api/account/all")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}