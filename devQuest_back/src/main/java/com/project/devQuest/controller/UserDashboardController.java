package com.project.devQuest.controller;

import com.project.devQuest.model.Dashboard;
import com.project.devQuest.service.DashboardService;
import com.project.devQuest.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/dashboards")
public class UserDashboardController {
    @Autowired
    private DashboardService dashboardService;
    @Autowired
    private UserService userService;
    private final static Logger logger= LoggerFactory.getLogger(UserDashboardController.class);

    @GetMapping("/my-dashboard")
    public ResponseEntity<Dashboard> getMyDashboard(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("Received GET /user/dashboards/my-dashboard from user: " + userDetails.getUsername());
        long id = userService.findByUsername(userDetails.getUsername()).getId();
        return ResponseEntity.ok(dashboardService.getDashboardByUserId(id));
    }
}
