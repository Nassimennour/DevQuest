package com.project.devQuest.controller;

import com.project.devQuest.dto.UserDTO;
import com.project.devQuest.model.AuthenticationRequest;
import com.project.devQuest.model.RegistrationRequest;
import com.project.devQuest.model.User;
import com.project.devQuest.security.JwtUtil;
import com.project.devQuest.service.MyUserDetailsService;
import com.project.devQuest.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AuthenticationControllerTest {

    @InjectMocks
    private AuthenticationController authenticationController;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private MyUserDetailsService userDetailsService;

    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    @Test
    public void whenCreateAuthenticationToken_thenReturnToken() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(authenticationController).build();

        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setUsername("testuser");
        authenticationRequest.setPassword("testpassword");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);
        when(userDetailsService.loadUserByUsername("testuser")).thenReturn(mock(UserDetails.class));
        when(jwtUtil.generateToken(any(UserDetails.class))).thenReturn("test-token");

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"testuser\", \"password\":\"testpassword\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void whenRegister_thenReturnUser() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(authenticationController).build();

        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setUsername("testuser");
        registrationRequest.setPassword("testpassword");
        registrationRequest.setEmail("testuser@example.com");

        User user = new User();
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("testuser");
        userDTO.setEmail("user@mail.com");
        userDTO.setRole("USER");
        when(userService.save(any(User.class))).thenReturn(userDTO);

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"testuser\", \"password\":\"testpassword\", \"email\":\"testuser@example.com\"}"))
                .andExpect(status().isOk());
    }
}