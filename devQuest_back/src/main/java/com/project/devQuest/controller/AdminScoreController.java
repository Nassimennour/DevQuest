package com.project.devQuest.controller;

import com.project.devQuest.model.Score;
import com.project.devQuest.service.ScoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/scores")
public class AdminScoreController {
    @Autowired
    private ScoreService scoreService;
    private final static Logger logger = LoggerFactory.getLogger(AdminScoreController.class);

    @GetMapping("")
    public ResponseEntity<List<Score>> getAllScores() {
        return ResponseEntity.ok(scoreService.getAllScores());
    }

    // Get Scores of a User
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Score>> getScoresByUserId(long userId) {
        return ResponseEntity.ok(scoreService.getScoresByUserId(userId));
    }

    // Get Scores of a Quizz
    @GetMapping("/quizz/{quizzId}")
    public ResponseEntity<List<Score>> getScoresByQuizzId(long quizzId) {
        return ResponseEntity.ok(scoreService.getScoresByQuizzId(quizzId));
    }

    // Get Scores of a User in a Quizz
    @GetMapping("/quizz/{quizzId}/user/{userId}")
    public ResponseEntity<List<Score>> getScoresByQuizzIdAndUserId(long quizzId, long userId) {
        return ResponseEntity.ok(scoreService.getScoresByQuizzIdAndUserId(quizzId, userId));
    }

    // Get Average Score of a Quizz
    @GetMapping("/quizz/{quizzId}/average")
    public ResponseEntity<Double> getAverageScoreByQuizzId(long quizzId) {
        return ResponseEntity.ok(scoreService.getAverageScoreByQuizzId(quizzId));
    }

    // Get Average Score of a User
    @GetMapping("/user/{userId}/average")
    public ResponseEntity<Double> getAverageScoreByUserId(long userId) {
        return ResponseEntity.ok(scoreService.getAverageScoreByUserId(userId));
    }

    // Get Average Score of a User in a Technology
    @GetMapping("/user/{userId}/technology/{technologyId}/average")
    public ResponseEntity<Double> getAverageScoreByUserIdAndTechnologyId(long userId, long technologyId) {
        return ResponseEntity.ok(scoreService.getAverageScoreByUserIdAndTechnologyId(userId, technologyId));
    }

    // Get Average Score of a User in a Category
    @GetMapping("/user/{userId}/category/{categoryId}/average")
    public ResponseEntity<Double> getAverageScoreByUserIdAndCategoryId(long userId, long categoryId) {
        return ResponseEntity.ok(scoreService.getAverageScoreByUserIdAndCategoryId(userId, categoryId));
    }

    // Get Score by Id
    @GetMapping("/{id}")
    public ResponseEntity<Score> getScoreById(long id) {
        return ResponseEntity.ok(scoreService.getScoreById(id));
    }

    // Delete Score
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScore(long id) {
        scoreService.deleteScore(id);
        return ResponseEntity.ok().build();
    }

}
