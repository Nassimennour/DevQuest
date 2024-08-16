package com.project.devQuest.controller;

import com.project.devQuest.model.Answer;
import com.project.devQuest.model.Quizz;
import com.project.devQuest.model.Score;
import com.project.devQuest.service.ScoreService;
import com.project.devQuest.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/scores")
public class UserScoreController {

    @Autowired
    private ScoreService scoreService;
    @Autowired
    private UserService userService;
    private final static Logger logger = LoggerFactory.getLogger(UserScoreController.class);

    @GetMapping("")
    public ResponseEntity<List<Score>> getMyScores() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("Getting scores for user: " + userDetails.getUsername());
        List<Score> myScores = scoreService.getScoresByUserId(userService.findByUsername(userDetails.getUsername()).getId());
        return ResponseEntity.ok(myScores);
    }

    @GetMapping("/quizz/{quizzId}")
    public ResponseEntity<List<Score>> getScoresByQuizzId(@PathVariable long quizzId) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("Getting scores for user: " + userDetails.getUsername() + " and quizz: " + quizzId);
        List<Score> scores = scoreService.getScoresByQuizzIdAndUserId(quizzId, userService.findByUsername(userDetails.getUsername()).getId());
        return ResponseEntity.ok(scores);
    }

    @PostMapping("/quizz/{quizzId}")
    public ResponseEntity<Score> submitQuizz(@PathVariable long quizzId, @RequestBody List<Answer> answers) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("Submitting quizz for user: " + userDetails.getUsername() + " and quizz: " + quizzId);
        Score myScore = scoreService.computeAndSaveScore(userService.findByUsername(userDetails.getUsername()).getId(), quizzId, answers);
        return ResponseEntity.ok(myScore);
    }

    @GetMapping("/quizz/{quizzId}/average")
    public ResponseEntity<Double> getAverageScoreByQuizzId(@PathVariable long quizzId) {
        logger.info("Getting average score for quizz: " + quizzId);
        Double averageScore = scoreService.getAverageScoreByQuizzId(quizzId);
        return ResponseEntity.ok(averageScore);
    }

    @GetMapping("/average")
    public ResponseEntity<Double> getMyAverageScore() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("Getting average score for user: " + userDetails.getUsername());
        Double averageScore = scoreService.getAverageScoreByUserId(userService.findByUsername(userDetails.getUsername()).getId());
        return ResponseEntity.ok(averageScore);
    }

    @GetMapping("/average/technology/{technologyId}")
    public ResponseEntity<Double> getMyAverageScoreByTechnologyId(@PathVariable long technologyId) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("Getting average score for user: " + userDetails.getUsername() + " at technology: " + technologyId);
         Double averageScore = scoreService.getAverageScoreByUserIdAndTechnologyId(userService.findByUsername(userDetails.getUsername()).getId(), technologyId);
        return ResponseEntity.ok(averageScore);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<Double> getMyAverageScoreByCategoryId(@PathVariable long categoryId) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("Getting average score for user: " + userDetails.getUsername() + " at category: " + categoryId);
        Double averageScore = scoreService.getAverageScoreByUserIdAndCategoryId(userService.findByUsername(userDetails.getUsername()).getId(), categoryId);
        return ResponseEntity.ok(averageScore);
    }




}
