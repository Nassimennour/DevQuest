package com.project.devQuest.controller;

import com.project.devQuest.dto.CodingChallengeDTO;
import com.project.devQuest.model.CodingChallenge;
import com.project.devQuest.service.CodingChallengeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/coding-challenges")
public class UserCodingChallengeController{
    @Autowired
    private CodingChallengeService codingChallengeService;
    private static final Logger logger = LoggerFactory.getLogger(UserCodingChallengeController.class);

    @GetMapping("")
    public ResponseEntity<List<CodingChallenge>> getAllCodingChallenges() {
        logger.info("Received GET /user/coding-challenges");
        return ResponseEntity.ok(codingChallengeService.getAllCodingChallenges());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CodingChallenge> getCodingChallengeById(@PathVariable long id) {
        logger.info("Received GET /user/coding-challenge/" + id);
        return ResponseEntity.ok(codingChallengeService.getCodingChallengeById(id).orElseThrow(() -> new RuntimeException("Coding challenge not found")));
    }

    @GetMapping("/technology/{technologyId}")
    public ResponseEntity<List<CodingChallenge>> getCodingChallengesByTechnologyId(@PathVariable long technologyId) {
        logger.info("Received GET /user/coding-challenges/technology/" + technologyId);
        return ResponseEntity.ok(codingChallengeService.getCodingChallengesByTechnologyId(technologyId));
    }

    @GetMapping("/creator/{creatorId}")
    public ResponseEntity<List<CodingChallenge>> getCodingChallengesByCreatorId(@PathVariable long creatorId) {
        logger.info("Received GET /user/coding-challenges/creator/" + creatorId);
        return ResponseEntity.ok(codingChallengeService.getCodingChallengesByCreatorId(creatorId));
    }

    @GetMapping("/difficulty/{difficulty}")
    public ResponseEntity<List<CodingChallenge>> getCodingChallengesByDifficulty( @PathVariable String difficulty) {
        logger.info("Received GET /user/coding-challenges/difficulty/" + difficulty);
        return ResponseEntity.ok(codingChallengeService.getCodingChallengesByDifficulty(difficulty));
    }

    @GetMapping("/creator/{creatorId}/technology/{technologyId}")
    public ResponseEntity<List<CodingChallenge>> getCodingChallengesByCreatorIdAndTechnologyId(@PathVariable long creatorId,@PathVariable long technologyId) {
        logger.info("Received GET /user/coding-challenges/creator/" + creatorId + "/technology/" + technologyId);
        return ResponseEntity.ok(codingChallengeService.getCodingChallengesByCreatorIdAndTechnologyId(creatorId, technologyId));
    }

    @PostMapping("")
    public ResponseEntity<CodingChallenge> saveCodingChallenge(@RequestBody CodingChallengeDTO codingChallenge) {
        logger.info("Received POST /user/coding-challenges");
        return ResponseEntity.ok(codingChallengeService.saveCodingChallenge(codingChallenge));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCodingChallenge(@PathVariable long id){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("Received DELETE /user/coding-challenges/" + id + " by " + userDetails.getUsername());
        CodingChallenge codingChallenge = codingChallengeService.getCodingChallengeById(id).orElseThrow(() -> new RuntimeException("Coding challenge not found"));
        if (codingChallenge.getCreator().getUsername().equals(userDetails.getUsername())) {
            codingChallengeService.deleteCodingChallenge(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(403).body("You are not allowed to delete this coding challenge");
        }
    }




}
