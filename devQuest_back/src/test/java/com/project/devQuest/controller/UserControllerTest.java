package com.project.devQuest.controller;

import com.project.devQuest.dto.ChangePasswordDTO;
import com.project.devQuest.dto.UserDTO;
import com.project.devQuest.model.User;
import com.project.devQuest.service.MyUserDetails;
import com.project.devQuest.service.TechnologyService;
import com.project.devQuest.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private TechnologyService technologyService;

    private MockMvc mockMvc;

    @Mock
    private SecurityContextHolder securityContextHolder;

    @Test
    public void whenChangePassword_thenReturnOk() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO();
        changePasswordDTO.setCurrentPassword("oldPassword");
        changePasswordDTO.setNewPassword("newPassword");

        doNothing().when(userService).changePassword(any(ChangePasswordDTO.class));

        mockMvc.perform(put("/user/change-password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"oldPassword\":\"oldPassword\", \"newPassword\":\"newPassword\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void whenCreateProfile_thenReturnUserDTO() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        User user = new User();
        user.setUsername("testuser");

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("testuser");

        when(userService.save(any(User.class))).thenReturn(userDTO);

        mockMvc.perform(post("/user/profile")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"testuser\", \"password\":\"testpassword\", \"email\":\"testuser@example.com\"}"))
                .andExpect(status().isOk());
    }


}