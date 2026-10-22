package com.example.marketPlace.controller;

import com.example.marketPlace.controller.DTO.UserDTO;
import com.example.marketPlace.controller.mapper.UserMapper;
import com.example.marketPlace.model.User;
import com.example.marketPlace.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private UserMapper userMapper;

    @Test
    void createUser() throws Exception {

        UUID userId = UUID.randomUUID();

        User user = new User();
        user.setId(userId);
        user.setName("João");
        user.setEmail("joao@email.com");
        user.setPassword("123456");

        when(userMapper.toUser(any(UserDTO.class))).thenReturn(user);

        mockMvc.perform(post("/user")
                        .with(user("test").roles("USER"))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                {
                    "name": "João",
                    "email": "joao@email.com",
                    "password": "123456"
                }
                """))
                .andExpect(status().isCreated());

        verify(userService).saveUser(any(User.class));
    }

    @Test
    @WithMockUser
    void getUserById() throws Exception {
        UUID userId = UUID.randomUUID();

        User user = new User();
        user.setId(userId);
        user.setName("João");
        user.setEmail("joao@email.com");

        when(userService.getUserById(userId))
                .thenReturn(user);

        mockMvc.perform(get("/user/{id}", userId)
                        .with(user("test").roles("USER")))
                .andDo(print());
    }

    @Test
    @WithMockUser
    void getAllUsers() throws Exception {

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setName("João");
        user.setEmail("joao@email.com");
        user.setPassword("senhaCodificada");

        UserDTO userDTO = new UserDTO(
                "João",
                "senhaCodificada",
                "joao@email.com"
        );

        when(userService.getAllUser()).thenReturn(List.of(user));
        when(userMapper.toUserDTO(user)).thenReturn(userDTO);

        mockMvc.perform(get("/user"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("João"))
                .andExpect(jsonPath("$[0].email").value("joao@email.com"));
    }
}