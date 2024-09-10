package com.project.devQuest.controller;

import com.project.devQuest.dto.SolutionDTO;
import com.project.devQuest.dto.UpdateSolutionDTO;
import com.project.devQuest.model.Solution;
import com.project.devQuest.service.SolutionService;
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
@RequestMapping("/user/solutions")
public class UserSolutionController {

    @Autowired
    private SolutionService solutionService;
    @Autowired
    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserSolutionController.class);

    @GetMapping("/challenge/{challengeId}")
    public ResponseEntity<List<Solution>> getSolutionsByChallengeId(@PathVariable long challengeId) {
        logger.info("Received GET /user/solutions/challenge/" + challengeId);
        return ResponseEntity.ok(solutionService.getAllSolutionsForChallenge(challengeId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Solution>> getSolutionsByUserId(@PathVariable long userId) {
        logger.info("Received GET /user/solutions/user/" + userId);
        return ResponseEntity.ok(solutionService.getAllSolutionsForUser(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Solution> getSolutionById(@PathVariable long id) {
        logger.info("Received GET /user/solutions/" + id);
        return ResponseEntity.ok(solutionService.getSolutionById(id));
    }

    @GetMapping("/my-solutions")
    public ResponseEntity<List<Solution>> getMySolutions() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("Received GET /user/solutions/my-solutions from user: " + userDetails.getUsername());
        long id = userService.findByUsername(userDetails.getUsername()).getId();
        return ResponseEntity.ok(solutionService.getAllSolutionsForUser(id));
    }

    @PostMapping("")
    public ResponseEntity<Solution> saveSolution(@RequestBody SolutionDTO solutionDTO) {
        logger.info("Received POST /user/solutions");
        return ResponseEntity.ok(solutionService.saveSolution(solutionDTO));
    }

    @PutMapping("")
    public ResponseEntity<Solution> updateSolution(@RequestBody UpdateSolutionDTO solutionDTO) {
        logger.info("Received PUT /user/solutions");
        return ResponseEntity.ok(solutionService.updateSolution(solutionDTO));
    }


}
