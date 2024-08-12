package com.project.devQuest.controller;


import com.project.devQuest.model.AuthenticationRequest;
import com.project.devQuest.model.RegistrationRequest;
import com.project.devQuest.model.User;
import com.project.devQuest.security.JwtUtil;
import com.project.devQuest.service.MyUserDetailsService;
import com.project.devQuest.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (Exception e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return jwt;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegistrationRequest registrationRequest) throws Exception {
        User user = new User();
        user.setUsername(registrationRequest.getUsername());
        user.setPassword(registrationRequest.getPassword());
        user.setEmail(registrationRequest.getEmail());
        user.setRole(registrationRequest.getRole());
        userService.save(user);

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(registrationRequest.getUsername(), registrationRequest.getPassword()));
        final UserDetails userDetails = userDetailsService.loadUserByUsername(registrationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return jwt;
    }


}
