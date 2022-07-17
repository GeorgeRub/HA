package com.ha.back.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ha.back.exceptions.account.NotFoundException;
import com.ha.back.models.user.ERole;
import com.ha.back.models.user.User;
import com.ha.back.payload.request.security.LoginRequest;
import com.ha.back.payload.request.security.SignupRequest;
import com.ha.back.payload.response.MessageResponse;
import com.ha.back.payload.response.security.JwtResponse;
import com.ha.back.repositories.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:test.application.properties"
)
@Sql(value = {"/roles_create.sql", "/user_create.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/roles_drop.sql", "/users_drop.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class UserServiceTest {

    @Autowired
    public MockMvc mvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private static User user;

    ObjectMapper objectMapper = new ObjectMapper();

    public void createUser() throws Exception {
        Set<String> roles = new HashSet<>();
        roles.add(ERole.ROLE_USER.toString());
        SignupRequest request = SignupRequest.buildSignup()
                .email("george@mail.com")
                .password("12345678")
                .username("george")
                .role(roles)
                .build();
        String user = objectMapper.writer().writeValueAsString(request);
        MvcResult mvcResult = mvc.perform(
                        post("/api/auth/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(user)
                )
                .andDo(print())
                .andReturn();
        String answer = mvcResult.getResponse().getContentAsString();
        MessageResponse responseMessageObject = objectMapper.readValue(answer, MessageResponse.class);
        assertEquals("User registered successfully!", responseMessageObject.getMessage());

    }

    public void findUser() throws NotFoundException {
        user = userService.getByName("george");
    }

    @BeforeEach
    private void before() throws Exception {
        try {
            findUser();
        } catch (NotFoundException e) {
            createUser();
            findUser();
        }

    }

    @AfterEach
    private void after() {
        userRepository.delete(user);
    }

    @DisplayName("Test user get by name")
    @Test
    void getByName() {
        assertEquals(user.getUsername(), "george");
        assertNotEquals(user.getUsername(), "george123");
    }

    @Test
    @DisplayName("sign in test")
    void signInTest() throws Exception {

//        LoginRequest loginRequest = new LoginRequest("george", "12345678");

        LoginRequest loginRequest = LoginRequest
                .builder()
                .username("george")
                .password("12345678")
                .build();
        String loginRequestString = objectMapper.writer().writeValueAsString(loginRequest);
        MvcResult mvcResult = mvc.perform(
                        post("/api/auth/signin")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(loginRequestString)
                )
                .andDo(print())
                .andReturn();

        String answer = mvcResult.getResponse().getContentAsString();
        JwtResponse jwtResponse = objectMapper.readValue(answer, JwtResponse.class);
        System.out.println(jwtResponse);
    }

}