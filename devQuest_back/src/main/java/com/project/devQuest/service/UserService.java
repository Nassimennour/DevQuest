package com.project.devQuest.service;

import com.project.devQuest.converter.UserDTOConverter;
import com.project.devQuest.dto.ChangePasswordDTO;
import com.project.devQuest.dto.UserDTO;
import com.project.devQuest.model.Dashboard;
import com.project.devQuest.model.Ranking;
import com.project.devQuest.model.User;
import com.project.devQuest.model.VerificationToken;
import com.project.devQuest.repository.TechnologyRepository;
import com.project.devQuest.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
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
    private EmailService emailService;


    @Autowired
    private VerificationTokenService verificationTokenService;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserDTO save(User user){
        logger.info("Saving user: {}", user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setVerified(false);
        Dashboard dashboard = new Dashboard();
        user.setDashboard(dashboard);
        Ranking ranking = new Ranking();
        user.setRanking(ranking);
        User savedUser = userRepository.save(user);
        logger.info("User saved successfully: {}", savedUser.getUsername());
        VerificationToken verificationToken = verificationTokenService.createVerificationToken(savedUser);
        emailService.sendVerificationEmail(user.getEmail(), verificationToken.getToken());
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
        logger.info("Found {} users", users.size());
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

    public void verifyToken(String token){
        VerificationToken verificationToken = verificationTokenService.getVerificationToken(token);
        if (verificationToken == null){
            throw new IllegalArgumentException("Invalid token");
        }
        User user = verificationToken.getUser();
        if (user.isVerified()){
            throw new IllegalArgumentException("User already verified");
        }
        if (verificationToken.getExpiryDate().before(new Date())){
            throw new IllegalArgumentException("Token expired");
        }
        user.setVerified(true);
        userRepository.save(user);
    }

    public void deleteAll(){
        logger.info("Deleting all users");
        userRepository.deleteAll();
    }

    public boolean existsById(Long id){
        logger.info("Checking if user exists by id: {}", id);
        return userRepository.existsById(id);
    }

    public UserDTO update(User user){
        logger.info("Updating user: {}", user.getUsername());
        User existingUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new IllegalArgumentException("User not found")
        );
        existingUser.setEmail(user.getEmail());
        existingUser.setUsername(user.getUsername());
        existingUser.setFullname(user.getFullname());
        existingUser.setRole(user.getRole());
        existingUser.setVerified(user.isVerified());
        existingUser.setBio(user.getBio());
        existingUser.setGender(user.getGender());
        existingUser.setBirthDate(user.getBirthDate());
        existingUser.setProfilePicture(user.getProfilePicture());
        userRepository.save(existingUser);
        logger.info("User updated successfully: {}", existingUser.getUsername());
        return userDTOConverter.convertToDTO(existingUser);
    }

    public UserDTO update(UserDTO userDTO){
        logger.info("Updating user: {}", userDTO.getUsername());
        User user = userDTOConverter.convertToEntity(userDTO);
        User updatedUser = userRepository.save(user);
        logger.info("User updated successfully: {}", updatedUser.getUsername());
        return userDTO;
    }

    // Number of users
    public  long count(){
        return userRepository.count();
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