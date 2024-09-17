package com.project.devQuest.service;

import com.project.devQuest.dto.RankingDTO;
import com.project.devQuest.model.Ranking;
import com.project.devQuest.model.User;
import com.project.devQuest.model.UserProgress;
import com.project.devQuest.repository.RankingRepository;
import com.project.devQuest.repository.UserProgressRepository;
import com.project.devQuest.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RankingService {

    @Autowired
    private RankingRepository rankingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserProgressRepository userProgressRepository;

    public List<Ranking> getAllRankings(){
        log.info("Finding all rankings");
        return rankingRepository.findAll();
    }

    public Ranking createRanking(RankingDTO rankingDTO){
        log.info("Creating ranking: {}", rankingDTO);
        Ranking ranking = new Ranking();
        ranking.setScore(rankingDTO.getScore());
        ranking.setUser(userRepository.findById(rankingDTO.getUserId()).orElseThrow(() -> new IllegalArgumentException("User not found")));
        ranking.setLevel(rankingDTO.getLevel());
        ranking.setPosition(rankingDTO.getPosition());
        return rankingRepository.save(ranking);
    }

    public Ranking updateRanking(Ranking newRanking){
        if (!rankingRepository.existsById(newRanking.getId())) {
            throw new IllegalArgumentException("Ranking not found");
        }
        log.info("Updating ranking: {}", newRanking);
        return rankingRepository.save(newRanking);
    }

    public void deleteRanking(Long id){
        if (!rankingRepository.existsById(id)) {
            throw new IllegalArgumentException("Ranking not found");
        }
        log.info("Deleting ranking by id: {}", id);
        rankingRepository.deleteById(id);
    }

    public boolean existsById(Long id){
        log.info("Checking if ranking exists by id: {}", id);
        return rankingRepository.existsById(id);
    }

    public Ranking getRankingById(Long id){
        log.info("Finding ranking by id: {}", id);
        return rankingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Ranking not found"));
    }

    public Optional<Ranking> getRankingByUserId(Long userId){
        log.info("Finding ranking by user id: {}", userId);
        return rankingRepository.findByUserId(userId);
    }

    public void updateRankings() {
        log.info("Updating rankings for all users with role 'User'");
        // Fetch all users with role 'User'
        List<User> users = userRepository.findAll().stream()
                .filter(user -> "USER".equalsIgnoreCase(user.getRole()))
                .collect(Collectors.toList());

        // Compute scores for each user
        List<Ranking> rankings = users.stream()
                .map(this::computeRanking)
                .sorted((r1, r2) -> Integer.compare(r2.getScore(), r1.getScore())) // Sort by score descending
                .toList();
        // Update positions in rankings
        for (int i = 0; i < rankings.size(); i++) {
            Ranking ranking = rankings.get(i);
            ranking.setPosition(i + 1);
            rankingRepository.save(ranking);
        }
    }

    private Ranking computeRanking(User user) {
        int score = 0;

        //Rank criteria: sum of completed challenges and quizzes
        List<UserProgress> progressList = userProgressRepository.findByUserId(user.getId());
        for (UserProgress progress : progressList) {
            score += progress.getCompletedChallenges();
            score += progress.getCompletedQuizzes();
        }
        // Create or update ranking
        Ranking ranking = rankingRepository.findByUserId(user.getId()).orElse(new Ranking());
        ranking.setUser(user);
        ranking.setScore(score);
        ranking.setLevel(computeLevel(score)); // Example level computation
        return ranking;
    }

    private int computeLevel(int score) {
        // Example level computation based on score
        if (score < 10) {
            return 1;
        } else if (score < 20) {
            return 2;
        } else {
            return 3;
        }
    }
}
