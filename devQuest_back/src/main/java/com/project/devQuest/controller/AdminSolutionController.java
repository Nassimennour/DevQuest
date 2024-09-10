package com.project.devQuest.controller;

import com.project.devQuest.dto.UpdateSolutionDTO;
import com.project.devQuest.model.Solution;
import com.project.devQuest.service.SolutionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Solution> getSolutionById(@PathVariable long id) {
        logger.info("Received GET /admin/solutions/" + id);
        return ResponseEntity.ok(solutionService.getSolutionById(id));
    }

    @GetMapping("/challenge/{challengeId}")
    public ResponseEntity<List<Solution>> getSolutionsByChallengeId(@PathVariable long challengeId) {
        logger.info("Received GET /admin/solutions/challenge/" + challengeId);
        return ResponseEntity.ok(solutionService.getAllSolutionsForChallenge(challengeId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Solution>> getSolutionsByUserId(@PathVariable long userId) {
        logger.info("Received GET /admin/solutions/user/" + userId);
        return ResponseEntity.ok(solutionService.getAllSolutionsForUser(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSolution(@PathVariable long id) {
        logger.info("Received DELETE /admin/solutions/" + id);
        solutionService.deleteSolution(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("")
    public ResponseEntity<Solution> updateSolution(@RequestBody UpdateSolutionDTO updateSolutionDTO) {
        logger.info("Received PUT /admin/solutions");
        return ResponseEntity.ok(solutionService.updateSolution(updateSolutionDTO));
    }



}
