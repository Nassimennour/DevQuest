package com.project.devQuest.controller;

import com.project.devQuest.dto.QuizzDTO;
import com.project.devQuest.model.Difficulty;
import com.project.devQuest.model.Quizz;
import com.project.devQuest.model.QuizzHistory;
import com.project.devQuest.service.QuizzHistoryService;
import com.project.devQuest.service.QuizzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/admin/quizzes")
public class AdminQuizzController {
    @Autowired
    private QuizzService quizzService;

    @Autowired
    private QuizzHistoryService quizzHistoryService;

    @GetMapping("")
    public ResponseEntity<List<Quizz>> getAllQuizzes() {
        try {
            List<Quizz> quizzes = quizzService.getAllQuizzes();
            return ResponseEntity.ok(quizzes);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quizz> getQuizzById(@PathVariable Long id) {
        Quizz quizz = quizzService.findById(id).orElseThrow(() -> new IllegalArgumentException("Quizz not found"));
        return ResponseEntity.ok(quizz);
    }

    @GetMapping("/technology/{technologyId}")
    public ResponseEntity<List<Quizz>> getQuizzesByTechnologyId(@PathVariable Long technologyId) {
        List<Quizz> quizzes = quizzService.getQuizzesByTechnologyId(technologyId);
        return ResponseEntity.ok(quizzes);
    }

    @GetMapping("/creator/{creatorId}")
    public ResponseEntity<List<Quizz>> getQuizzesByCreatorId(@PathVariable Long creatorId) {
        List<Quizz> quizzes = quizzService.getQuizzesByCreatorId(creatorId);
        return ResponseEntity.ok(quizzes);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<Quizz>> getQuizzesByTitle(@PathVariable String title) {
        List<Quizz> quizzes = quizzService.getQuizzesByTitle(title);
        return ResponseEntity.ok(quizzes);
    }

    @GetMapping("/difficulty/{difficulty}")
    public ResponseEntity<List<Quizz>> getQuizzesByDifficulty(@PathVariable String difficulty) {
        try {
            Difficulty diff = Difficulty.valueOf(difficulty.toUpperCase());
            return ResponseEntity.ok(quizzService.getQuizzesByDifficulty(diff));
        }catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("")
    public ResponseEntity<Quizz> createQuizz(@RequestBody QuizzDTO quizzDTO) {
        return ResponseEntity.ok(quizzService.createQuizz(quizzDTO));
    }

    @PutMapping("")
    public ResponseEntity<Quizz> updateQuizz(@RequestBody Quizz quizz) {
        return ResponseEntity.ok(quizzService.updateQuizz(quizz));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuizz(@PathVariable Long id) {
        if (!quizzService.existsById(id)) {
            return ResponseEntity.badRequest().body("Quizz not found");
        }
        quizzService.deleteQuizz(id);
        return ResponseEntity.ok("Quizz deleted successfully");
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteAllQuizzes() {
        quizzService.deleteAllQuizzes();
        return ResponseEntity.ok("All quizzes deleted successfully");
    }

    // Get quizz history by user's id
    @GetMapping("/history/{userId}")
    public ResponseEntity<List<QuizzHistory>> getQuizzHistoryByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(quizzHistoryService.findByUserId(userId));
    }



}
