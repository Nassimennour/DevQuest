package com.project.devQuest.controller;

import com.project.devQuest.dto.UserDTO;
import com.project.devQuest.model.User;
import com.project.devQuest.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/users")
@Slf4j
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        try {
            List<UserDTO> users  = userService.findAll();
            return ResponseEntity.ok(users);
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO userDTO = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        UserDTO userDTO = userService.findByUsername(username);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("")
    public ResponseEntity<UserDTO> createUser(User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        if (!userService.existsById(id)) {
            return ResponseEntity.badRequest().body("User not found");
        }
        userService.deleteById(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteAllUsers() {
        userService.deleteAll();
        return ResponseEntity.ok("All users deleted successfully");
    }

    @PutMapping("")
    public ResponseEntity<UserDTO> updateUser(User user) {
        return ResponseEntity.ok(userService.save(user));
    }
}
