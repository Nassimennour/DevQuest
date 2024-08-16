package com.project.devQuest.controller;

import com.project.devQuest.model.Solution;
import com.project.devQuest.service.SolutionService;
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
@RequestMapping("/admin/solutions")
public class AdminSolutionController {

    @Autowired
    private SolutionService solutionService;
    private final static Logger logger = LoggerFactory.getLogger(AdminSolutionController.class);

    @GetMapping("")
    public ResponseEntity<List<Solution>> getAllSolutions() {
        logger.info("Received GET /admin/solutions");
        return ResponseEntity.ok(solutionService.getAllSolutions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Solution> getSolutionById(long id) {
        logger.info("Received GET /admin/solutions/" + id);
        return ResponseEntity.ok(solutionService.getSolutionById(id));
    }

    @GetMapping("/challenge/{challengeId}")
    public ResponseEntity<List<Solution>> getSolutionsByChallengeId(long challengeId) {
        logger.info("Received GET /admin/solutions/challenge/" + challengeId);
        return ResponseEntity.ok(solutionService.getAllSolutionsForChallenge(challengeId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Solution>> getSolutionsByUserId(long userId) {
        logger.info("Received GET /admin/solutions/user/" + userId);
        return ResponseEntity.ok(solutionService.getAllSolutionsForUser(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSolution(long id) {
        logger.info("Received DELETE /admin/solutions/" + id);
        solutionService.deleteSolution(id);
        return ResponseEntity.noContent().build();
    }



}
