package com.project.devQuest.service;

import com.project.devQuest.converter.UserDTOConverter;
import com.project.devQuest.dto.ChangePasswordDTO;
import com.project.devQuest.dto.UserDTO;
import com.project.devQuest.model.Technology;
import com.project.devQuest.model.User;
import com.project.devQuest.repository.TechnologyRepositiry;
import com.project.devQuest.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserDTOConverter userDTOConverter;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TechnologyRepositiry technologyRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserDTO save(User user){
        logger.info("Saving user: {}", user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        logger.info("User saved successfully: {}", savedUser.getUsername());
        return userDTOConverter.convertToDTO(savedUser);
    }

    public UserDTO findByUsername(String username){
        logger.info("Finding user by username: {}", username);
        User user = userRepository.findByUsername(username).orElse(null);
        return userDTOConverter.convertToDTO(user);
    }

    public boolean existsByUsername(String username){
        logger.info("Checking if user exists by username: {}", username);
        return userRepository.existsByUsername(username);
    }

    public UserDTO findByEmail(String email){
        logger.info("Finding user by email: {}", email);
        User user = userRepository.findByEmail(email).orElse(null);
        return userDTOConverter.convertToDTO(user);
    }

    public List<UserDTO> findAll(){
        logger.info("Finding all users");
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> userDTOConverter.convertToDTO(user)).toList();
    }

    public Optional<UserDTO> findById(Long id){
        logger.info("Finding user by id: {}", id);
        User user = userRepository.findById(id).orElse(null);
        return Optional.ofNullable(userDTOConverter.convertToDTO(user));
    }

    public void deleteById(Long id){
        logger.info("Deleting user by id: {}", id);
        userRepository.deleteById(id);
    }

    public void deleteAll(){
        logger.info("Deleting all users");
        userRepository.deleteAll();
    }

    public boolean existsById(Long id){
        logger.info("Checking if user exists by id: {}", id);
        return userRepository.existsById(id);
    }

    public UserDTO update(UserDTO userDTO){
        logger.info("Updating user: {}", userDTO.getUsername());
        User user = userDTOConverter.convertToEntity(userDTO);
        User updatedUser = userRepository.save(user);
        logger.info("User updated successfully: {}", updatedUser.getUsername());
        return userDTO;
    }

    @Transactional
    public void changePassword(ChangePasswordDTO changePasswordDTO){ {
        User user = userRepository.findByUsername(changePasswordDTO.getUsername()).orElseThrow(
                () -> new UsernameNotFoundException("User not found with username: " + changePasswordDTO.getUsername())
        );
        if (!passwordEncoder.matches(changePasswordDTO.getCurrentPassword(), user.getPassword())){
            throw new IllegalArgumentException("Incorrect current password");
        }
        user.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
        userRepository.save(user);
    }

}}