package com.project.devQuest.controller;

import com.project.devQuest.dto.UserDTO;
import com.project.devQuest.model.User;
import com.project.devQuest.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {

    @InjectMocks
    private AdminController adminController;

    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    @Test
    public void whenGetAllUsers_thenReturnUserList() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("testuser");

        when(userService.findAll()).thenReturn(Collections.singletonList(userDTO));

        mockMvc.perform(get("/admin/users"))
                .andExpect(status().isOk());
    }

    @Test
    public void whenGetUserById_thenReturnUserDTO() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("testuser");

        when(userService.findById(1L)).thenReturn(Optional.of(userDTO));

        mockMvc.perform(get("/admin/users/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void whenGetUserByUsername_thenReturnUserDTO() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("testuser");

        when(userService.findByUsername("testuser")).thenReturn(userDTO);

        mockMvc.perform(get("/admin/users/testuser"))
                .andExpect(status().isOk());
    }

    @Test
    public void whenCreateUser_thenReturnUserDTO() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();

        User user = new User();
        user.setUsername("testuser");

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("testuser");

        when(userService.save(any(User.class))).thenReturn(userDTO);

        mockMvc.perform(post("/admin/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"testuser\", \"password\":\"testpassword\", \"email\":\"testuser@example.com\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void whenDeleteUser_thenReturnOk() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();

        doNothing().when(userService).deleteById(1L);

        mockMvc.perform(delete("/admin/users/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void whenDeleteAllUsers_thenReturnOk() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();

        doNothing().when(userService).deleteAll();

        mockMvc.perform(delete("/admin/users"))
                .andExpect(status().isOk());
    }

    @Test
    public void whenUpdateUser_thenReturnUserDTO() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();

        User user = new User();
        user.setUsername("testuser");

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("testuser");

        when(userService.save(any(User.class))).thenReturn(userDTO);

        mockMvc.perform(put("/admin/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"testuser\", \"password\":\"testpassword\", \"email\":\"testuser@example.com\"}"))
                .andExpect(status().isOk());
    }
}