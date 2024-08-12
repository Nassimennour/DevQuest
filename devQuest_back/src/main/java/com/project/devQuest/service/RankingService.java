package com.project.devQuest.service;

import com.project.devQuest.model.Ranking;
import com.project.devQuest.repository.RankingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RankingService {

    @Autowired
    private RankingRepository rankingRepository;

    public List<Ranking> getAllRankings(){
        log.info("Finding all rankings");
        return rankingRepository.findAll();
    }

    public Ranking createRanking(Ranking ranking){
        log.info("Creating ranking: {}", ranking);
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
}
