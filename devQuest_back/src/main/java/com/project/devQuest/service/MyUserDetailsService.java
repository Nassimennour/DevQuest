package com.project.devQuest.service;

import com.project.devQuest.model.User;
import com.project.devQuest.repository.UserRepository;
import com.project.devQuest.security.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        logger.info("Loading user by username: {}", username);
        if (userRepository.existsByUsername(username)) {
            User user = userRepository.findByUsername(username).get();
            logger.info("User found: {}", user);
            return new MyUserDetails(user);
        } else {
            logger.warn("User not found with username: {}", username);
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

}
