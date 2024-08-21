package com.project.devQuest.controller;

import com.project.devQuest.dto.ChangePasswordDTO;
import com.project.devQuest.dto.UserDTO;
import com.project.devQuest.model.Technology;
import com.project.devQuest.model.User;
import com.project.devQuest.service.TechnologyService;
import com.project.devQuest.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private TechnologyService technologyService;

    @PutMapping("/change-password")
    public ResponseEntity<?> changePassworxd(@Valid @RequestBody ChangePasswordDTO changePasswordDTO) {
        try {
            userService.changePassword(changePasswordDTO);
            return  ResponseEntity.ok("Password changed successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/profile")
    public ResponseEntity<UserDTO> createProfile(@Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @GetMapping("/my-profile")
    public ResponseEntity<UserDTO> getMyProfile() {
        logger.info("Received request to get profile for user");
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("Received request to get profile for user: {}", userDetails.getUsername());
        UserDTO userDTO = userService.findByUsername(userDetails.getUsername());
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<UserDTO> getProfile(@PathVariable Long id) {
        UserDTO userDTO = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/profile/username/{username}")
    public ResponseEntity<UserDTO> getProfileByUsername(@PathVariable String username) {
        UserDTO userDTO = userService.findByUsername(username);
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping("/profile")
    public ResponseEntity<UserDTO> updateProfile(@Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @DeleteMapping("/profile/{id}")
    public ResponseEntity<?> deleteProfile(@PathVariable Long id) {
        if (!userService.existsById(id)) {
            return ResponseEntity.badRequest().body("User not found");
        }
        userService.deleteById(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    // add a skill to the skill set of the user
    @PutMapping("/profile/add-skill/{technologyId}")
    public ResponseEntity<UserDTO> addSkill(@PathVariable long technologyId) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDTO userDTO = userService.findByUsername(userDetails.getUsername());
        Technology technology = technologyService.getTechnologyById(technologyId);
        userDTO.getSkills().add(technology);
        return ResponseEntity.ok(userService.update(userDTO));
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verifyUser(@RequestParam("token") String token){
        try {
            userService.verifyToken(token);
            return ResponseEntity.ok("User verified successfully");
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public Map<String, String> handleValidationExceptions(IllegalArgumentException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return response;
    }

}
