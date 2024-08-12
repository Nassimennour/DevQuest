package com.project.devQuest.controller;

import com.project.devQuest.model.Quizz;
import com.project.devQuest.model.QuizzHistory;
import com.project.devQuest.service.QuizzHistoryService;
import com.project.devQuest.service.QuizzService;
import com.project.devQuest.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/user/quizzes")
public class UserQuizzController {

    @Autowired
    private QuizzService quizzService;

    @Autowired
    private QuizzHistoryService quizzHistoryService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<List<Quizz>> getAllQuizzes() {
        return ResponseEntity.ok(quizzService.getAllQuizzes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quizz> getQuizzById(@PathVariable long id){
        Quizz quizz = quizzService.findById(id).orElseThrow(() -> new IllegalArgumentException("Quizz not found"));
        return ResponseEntity.ok(quizz);
    }

    // Get the quizz history of the authenticated user
    @GetMapping("/userId/{userId}")
    public ResponseEntity<List<QuizzHistory>> getQuizzHistoryByUserId(@PathVariable long userId){
        return ResponseEntity.ok(quizzHistoryService.findByUserId(userId));
    }


    @PostMapping("")
    public ResponseEntity<Quizz> createQuizz(@RequestBody Quizz quizz){
        return ResponseEntity.ok(quizzService.createQuizz(quizz));
    }

    // update quizzes that the authenticated user created
    @PutMapping("/{id}")
    public ResponseEntity<?> updateQuizz(@PathVariable long id, @RequestBody Quizz quizz){
        Quizz existingQuizz = quizzService.findById(id).orElseThrow(() -> new IllegalArgumentException("Quizz not found"));
        // Get authenticated user
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        if (!existingQuizz.getCreator().getUsername().equals(username)) {
            return ResponseEntity.status(403).body("You are not allowed to update this quizz");
        }
        return ResponseEntity.ok(quizzService.updateQuizz(quizz));
    }


    //Get quizzes created by the authenticated user
    @GetMapping("/my-quizzes")
    public ResponseEntity<List<Quizz>> getMyQuizzes() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        List<Quizz> allQuizzes = quizzService.getAllQuizzes();
        List<Quizz> myQuizzes = allQuizzes.stream()
                .filter(quizz -> quizz.getCreator().getUsername().equals(username))
                .collect(Collectors.toList());
        return ResponseEntity.ok(myQuizzes);
    }

    // Method to delete a quiz created by the authenticated user
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuizz(@PathVariable long id) {
        Quizz existingQuizz = quizzService.findById(id).orElseThrow(() -> new IllegalArgumentException("Quizz not found"));
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        if (!existingQuizz.getCreator().getUsername().equals(username)) {
            return ResponseEntity.status(403).body("You are not allowed to delete this quizz");
        }
        quizzService.deleteQuizz(id);
        return ResponseEntity.ok("Quizz deleted successfully");
    }

    // Method to get quizzes by technology for the authenticated user
    @GetMapping("/technology/{technologyId}")
    public ResponseEntity<List<Quizz>> getQuizzesByTechnology(@PathVariable long technologyId) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        List<Quizz> quizzes = quizzService.getQuizzesByTechnologyId(technologyId).stream()
                .filter(quizz -> quizz.getCreator().getUsername().equals(username))
                .collect(Collectors.toList());
        return ResponseEntity.ok(quizzes);
    }

    // Method to get quizz history of the authenticated User
    @GetMapping("/history")
    public ResponseEntity<List<QuizzHistory>> getQuizzHistory() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        long userId = userService.findByUsername(username).getId();
        return ResponseEntity.ok(quizzHistoryService.findByUserId(userId));
    }


}
