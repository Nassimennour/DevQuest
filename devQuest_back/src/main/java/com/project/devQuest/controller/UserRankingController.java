package com.project.devQuest.controller;

import com.project.devQuest.dto.UserDTO;
import com.project.devQuest.model.Ranking;
import com.project.devQuest.service.RankingService;
import com.project.devQuest.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/user/rankings")
public class UserRankingController {

    @Autowired
    private RankingService rankingService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<List<Ranking>> getAllRankings() {
        return ResponseEntity.ok(rankingService.getAllRankings());
    }

    @GetMapping("/my-ranking")
    public ResponseEntity<Ranking> getMyRanking() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDTO userDTO = userService.findByUsername(userDetails.getUsername());
        return ResponseEntity.ok(rankingService.getRankingByUserId(userDTO.getId()).get());
    }


}
