package com.project.devQuest.controller;

import com.project.devQuest.dto.RankingDTO;
import com.project.devQuest.model.Ranking;
import com.project.devQuest.service.RankingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/admin/rankings")
public class AdminRankingController {

    @Autowired
    private RankingService rankingService;

    @GetMapping("")
    public ResponseEntity<List<Ranking>> getAllRankings() {
        return ResponseEntity.ok(rankingService.getAllRankings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRankingById(@PathVariable Long id) {
        if (!rankingService.existsById(id)) {
            return ResponseEntity.badRequest().body("Ranking not found");
        }
        return ResponseEntity.ok(rankingService.getRankingById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getRankingByUserId(@PathVariable Long userId) {
        if (!rankingService.getRankingByUserId(userId).isPresent()) {
            return ResponseEntity.badRequest().body("Ranking not found");
        }
        return ResponseEntity.ok(rankingService.getRankingByUserId(userId).get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRanking(@PathVariable Long id) {
        if (!rankingService.existsById(id)) {
            return ResponseEntity.badRequest().body("Ranking not found");
        }
        rankingService.deleteRanking(id);
        return ResponseEntity.ok("Ranking deleted successfully");
    }

    @PostMapping("")
    public ResponseEntity<Ranking> createRanking(@RequestBody RankingDTO ranking) {
        return ResponseEntity.ok(rankingService.createRanking(ranking));
    }

    @PutMapping("")
    public ResponseEntity<?> updateRanking(@RequestBody Ranking ranking) {
        if (!rankingService.existsById(ranking.getId())) {
            return ResponseEntity.badRequest().body("Ranking not found");
        }
        return ResponseEntity.ok(rankingService.updateRanking(ranking));
    }
}
