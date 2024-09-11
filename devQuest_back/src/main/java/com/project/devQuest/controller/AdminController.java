package com.project.devQuest.controller;

import com.project.devQuest.dto.ActivityDTO;
import com.project.devQuest.dto.UserActivityDTO;
import com.project.devQuest.dto.UserDTO;
import com.project.devQuest.model.User;
import com.project.devQuest.service.ActivityService;
import com.project.devQuest.service.CodingChallengeService;
import com.project.devQuest.service.QuizzService;
import com.project.devQuest.service.TechnologyService;
import com.project.devQuest.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/users")
@Slf4j
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private QuizzService quizzService;
    @Autowired
    private CodingChallengeService codingChallengeService;
    @Autowired
    private TechnologyService technologyService;
    @Autowired
    private ActivityService activityService;

    @GetMapping("")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        logger.info("Received GET request to fetch all users");
        try {
            List<UserDTO> users  = userService.findAll();
            logger.info("Returning {} users", users.size());
            return ResponseEntity.ok(users);
        }catch(Exception e) {
            logger.error("Error fetching users", e);
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO userDTO = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        UserDTO userDTO = userService.findByUsername(username);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("")
    public ResponseEntity<UserDTO> createUser(@RequestBody User user) {
        logger.info("Received POST request to create user: {}", user.getUsername());
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
    public ResponseEntity<UserDTO> updateUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.update(user));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public Map<String, String> handleValidationExceptions(IllegalArgumentException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return response;
    }

    // Get some statistics about the users, quizzes, coding challenges, etc.
    @GetMapping("/statistics")
    public ResponseEntity<Map<String, Long>> getStatistics() {
        Map<String, Long> statistics = new HashMap<>();
        statistics.put("users", userService.count());
        statistics.put("quizzes", quizzService.count());
        statistics.put("codingChallenges", codingChallengeService.countCodingChallenges());
        statistics.put("technologies", technologyService.count());
        return ResponseEntity.ok(statistics);
    }


    // Recent activities (users, quizzes, coding challenges, solutions, etc.)
    @GetMapping("/recent-activities")
    public ResponseEntity<List<ActivityDTO>> getRecentActivities() {
       logger.info("Received GET request to fetch recent activities");
         return ResponseEntity.ok(activityService.getRecentActivities());
    }

    // User activity stats for the last 30 days
    @GetMapping("/activity")
    public ResponseEntity<List<UserActivityDTO>> getUserActivity() {
        logger.info("Received GET request to fetch user activity stats");
        return ResponseEntity.ok(activityService.getUserActivity());
    }
}
