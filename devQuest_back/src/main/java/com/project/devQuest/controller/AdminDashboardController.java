package com.project.devQuest.controller;

import com.project.devQuest.model.Dashboard;
import com.project.devQuest.service.DashboardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/dashboards")
public class AdminDashboardController {
    @Autowired
    private DashboardService dashboardService;
    private final static Logger logger = LoggerFactory.getLogger(AdminDashboardController.class);

    @GetMapping("")
    public ResponseEntity<List<Dashboard>> getAllDashboards() {
        logger.info("Received GET /admin/dashboards");
        return ResponseEntity.ok(dashboardService.getAllDashboards());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Dashboard> getDashboardByUserId(@PathVariable long userId) {
        logger.info("Received GET /admin/dashboards/user/" + userId);
        return ResponseEntity.ok(dashboardService.getDashboardByUserId(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dashboard> getDashboardById(@PathVariable long id) {
        logger.info("Received GET /admin/dashboards/" + id);
        return ResponseEntity.ok(dashboardService.getDashboardById(id));
    }

    @PostMapping("/user/{userId}/quizz/{quizzId}")
    public ResponseEntity<Dashboard> addSuggestedQuizz(@PathVariable long userId,@PathVariable long quizzId) {
        logger.info("Received POST /admin/dashboards/user/" + userId + "/quizz/" + quizzId);
        return ResponseEntity.ok(dashboardService.addSuggestedQuizz(userId, quizzId));
    }

    @PostMapping("/user/{userId}/coding-challenge/{codingChallengeId}")
    public ResponseEntity<Dashboard> addSuggestedCodingChallenge(@PathVariable long userId,@PathVariable long codingChallengeId) {
        logger.info("Received POST /admin/dashboards/user/" + userId + "/coding-challenge/" + codingChallengeId);
        return ResponseEntity.ok(dashboardService.addSuggestedCodingChallenge(userId, codingChallengeId));
    }

    @DeleteMapping("/user/{userId}/quizz/{quizzId}")
    public ResponseEntity<Dashboard> removeSuggestedQuizz(@PathVariable long userId,@PathVariable long quizzId) {
        logger.info("Received DELETE /admin/dashboards/user/" + userId + "/quizz/" + quizzId);
        return ResponseEntity.ok(dashboardService.removeSuggestedQuizz(userId, quizzId));
    }

    @DeleteMapping("/user/{userId}/coding-challenge/{codingChallengeId}")
    public ResponseEntity<Dashboard> removeSuggestedCodingChallenge(@PathVariable long userId,@PathVariable long codingChallengeId) {
        logger.info("Received DELETE /admin/dashboards/user/" + userId + "/coding-challenge/" + codingChallengeId);
        return ResponseEntity.ok(dashboardService.removeSuggestedCodingChallenge(userId, codingChallengeId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDashboard(@PathVariable long id) {
        logger.info("Received DELETE /admin/dashboards/" + id);
        dashboardService.deleteDashboard(id);
        return ResponseEntity.noContent().build();
    }

}
