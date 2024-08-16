package com.project.devQuest.controller;

import com.project.devQuest.model.UserProgress;
import com.project.devQuest.service.UserProgressService;
import com.project.devQuest.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/progress")
public class UserProgressController {

    @Autowired
    private UserProgressService userProgressService;
    @Autowired
    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserProgressController.class);

    @GetMapping("/my-progresses")
    public ResponseEntity<List<UserProgress>> getMyProgresses() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("Received GET /user/progress/my-progresses from user: " + userDetails.getUsername());
        long id = userService.findByUsername(userDetails.getUsername()).getId();
        return ResponseEntity.ok(userProgressService.getUserProgressByUserId(id));
    }

    @GetMapping("/technology/{technologyId}")
    public ResponseEntity<UserProgress> getMyProgressByTechnologyId(@PathVariable long technologyId) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("Received GET /user/progress/technology/" + technologyId + " from user: " + userDetails.getUsername());
        long id = userService.findByUsername(userDetails.getUsername()).getId();
        return ResponseEntity.ok(userProgressService.getUserProgressByUserIdAndTechnologyId(id, technologyId));
    }

    @GetMapping("/my-progresses/ordered")
    public ResponseEntity<List<UserProgress>> getMyProgressesOrdered() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("Received GET /user/progress/my-progresses/ordered from user: " + userDetails.getUsername());
        long id = userService.findByUsername(userDetails.getUsername()).getId();
        return ResponseEntity.ok(userProgressService.getUserProgressByUserIdOrderByProgressPercentageDesc(id));
    }



}
