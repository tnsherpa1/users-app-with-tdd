package com.example.usersapp.controllers;


import com.example.usersapp.models.User;
import com.example.usersapp.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UsersController.class)
public class UsersControllerTest {
    @MockBean
    private UserRepository mockUserRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper jsonObjectMapper;

    private User newUser;
    private User updatedSecondUser;
    @Before
    public void setUp() {
        User firstUser = new User(
            "someone",
            "Ima",
            "Person"
        );

        User secondUser = new User(
            "someone_else",
            "Someone",
            "Else"
        );

        Iterable<User> mockUsers =
                Stream.of(firstUser, secondUser).collect(Collectors.toList());

        given(mockUserRepository.findAll()).willReturn(mockUsers);
        given(mockUserRepository.findOne(1L)).willReturn(firstUser);

        newUser = new User(
                "new_user_for_create",
                "New",
                "User"
        );
        given(mockUserRepository.save(newUser)).willReturn(newUser);

        updatedSecondUser = new User(
                "updated_username",
                "Updated",
                "Info"
        );
        given(mockUserRepository.save(updatedSecondUser)).willReturn(updatedSecondUser);
    }

    @Test
    public void findAllUsers_success_returnsStatusOK() throws Exception {
        this.mockMvc
                .perform(get("/users"))
                .andExpect(status().isOk());
    }

    @Test
    public void findAllUsers_success_returnAllUsersAsJSON() throws Exception {
        this.mockMvc
                .perform(get("/users"))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void createUser_success_returnsStatusOk() throws Exception {

        this.mockMvc
                .perform(
                        post("/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(newUser))
                )
                .andExpect(status().isOk());
    }

    @Test
    public void createUser_success_returnsUserName() throws Exception {

        this.mockMvc
                .perform(
                        post("/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(newUser))
                )
                .andExpect(jsonPath("$.userName", is("new_user_for_create")));
    }

    @Test
    public void findUserById_success_returnsStatusOK() throws Exception {

        this.mockMvc
                .perform(get("/users/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void findUserById_success_returnUserName() throws Exception {

        this.mockMvc
                .perform(get("/users/1"))
                .andExpect(jsonPath("$.userName", is("someone")));
    }

    @Test
    public void updatedUserById_success_returnsStatusOk() throws Exception {
        this.mockMvc
                .perform(
                        patch("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObjectMapper.writeValueAsString(updatedSecondUser))
                )
                .andExpect(status().isOk());
    }

    @Test
    public void updateUserById_success_returnsUpdatedUserName() throws Exception {
        this.mockMvc
                .perform(
                        patch("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObjectMapper.writeValueAsString(updatedSecondUser))
                )
                .andExpect(jsonPath("$.userName",is("updated_username")));
    }

}
