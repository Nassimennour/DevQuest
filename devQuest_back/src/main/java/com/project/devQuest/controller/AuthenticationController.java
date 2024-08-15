package com.project.devQuest.controller;

import com.project.devQuest.model.AuthenticationRequest;
import com.project.devQuest.model.RegistrationRequest;
import com.project.devQuest.model.User;
import com.project.devQuest.security.JwtUtil;
import com.project.devQuest.service.MyUserDetailsService;
import com.project.devQuest.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class AuthenticationController {
    @Autowired
    public AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            log.info("Authenticating user: {}", authenticationRequest.getUsername());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            final String jwt = jwtUtil.generateToken(userDetails);
            log.info("Authentication successful for user: {}", authenticationRequest.getUsername());
            return jwt;
        } catch (Exception e) {
            log.error("Authentication failed for user: {}", authenticationRequest.getUsername(), e);
            throw new Exception("Incorrect username or password", e);

        }

    }

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegistrationRequest registrationRequest) throws Exception {
        log.info("Registering user: {}", registrationRequest.getUsername());
        User user = new User();
        user.setUsername(registrationRequest.getUsername());
        user.setEmail(registrationRequest.getEmail());
        user.setPassword(registrationRequest.getPassword());
        user.setRole(registrationRequest.getRole());
        userService.save(user);
        log.info("Registration successful for user: {}", registrationRequest.getUsername());
        // Automatic login after registration
        //authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(registrationRequest.getUsername(), registrationRequest.getPassword()));
        //final UserDetails userDetails = userDetailsService.loadUserByUsername(registrationRequest.getUsername());
        //final String jwt = jwtUtil.generateToken(userDetails);
        //return jwt;
        return "User registered successfully";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public Map<String, String> handleValidationExceptions(IllegalArgumentException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return response;
    }
}