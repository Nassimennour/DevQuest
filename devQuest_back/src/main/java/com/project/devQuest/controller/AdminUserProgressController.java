package com.project.devQuest.controller;

import com.project.devQuest.model.UserProgress;
import com.project.devQuest.service.UserProgressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/user-progresses")
public class AdminUserProgressController {
    @Autowired
    private UserProgressService userProgressService;
    private final static Logger logger = LoggerFactory.getLogger(AdminUserProgressController.class);

    // Get all User Progresses
    @GetMapping("")
    public ResponseEntity<List<UserProgress>> getAllUserProgresses() {
        logger.info("Received GET /admin/user-progresses");
        return ResponseEntity.ok(userProgressService.getAllUserProgress());
    }
    // Get User Progress by id
    @GetMapping("/{id}")
    public ResponseEntity<UserProgress> getUserProgressById(@PathVariable long id) {
        logger.info("Received GET /admin/user-progresses/" + id);
        return ResponseEntity.ok(userProgressService.getUserProgressById(id));
    }
    // Get User Progresses by User Id
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserProgress>> getUserProgressByUserId(@PathVariable long userId) {
        logger.info("Received GET /admin/user-progresses/user/" + userId);
        return ResponseEntity.ok(userProgressService.getUserProgressByUserId(userId));
    }

    // Get User Progress by Technology Id ordered by Progress Percentage
    @GetMapping("/technology/{technologyId}/ordered")
    public ResponseEntity<List<UserProgress>> getUserProgressByTechnologyIdOrdered(@PathVariable long technologyId) {
        logger.info("Received GET /admin/user-progresses/technology/" + technologyId);
        return ResponseEntity.ok(userProgressService.getUserProgressByTechnologyIdOrderByProgressPercentageDesc(technologyId));
    }
    // Get User Progress by User Id and Technology Id
    @GetMapping("/user/{userId}/technology/{technologyId}")
    public ResponseEntity<UserProgress> getUserProgressByUserIdAndTechnologyId(@PathVariable long userId,@PathVariable long technologyId) {
        logger.info("Received GET /admin/user-progresses/user/" + userId + "/technology/" + technologyId);
        return ResponseEntity.ok(userProgressService.getUserProgressByUserIdAndTechnologyId(userId, technologyId));
    }

    // Get Uesr Progress By UserId ordered by Progress Percentage
    @GetMapping("/user/{userId}/ordered")
    public ResponseEntity<List<UserProgress>> getUserProgressByUserIdOrdered(@PathVariable long userId) {
        logger.info("Received GET /admin/user-progresses/user/" + userId);
        return ResponseEntity.ok(userProgressService.getUserProgressByUserIdOrderByProgressPercentageDesc(userId));
    }

    // Delete User Progress by Id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUserProgress(@PathVariable long id) {
        logger.info("Received DELETE /admin/user-progresses/delete/" + id);
        userProgressService.DeleteUserProgress(id);
        return ResponseEntity.noContent().build();
    }


}
