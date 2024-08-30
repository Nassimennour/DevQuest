package com.project.devQuest.service;

import com.project.devQuest.dto.QuizzCompletionStatsDTO;
import com.project.devQuest.model.QuizzHistory;
import com.project.devQuest.repository.QuizzHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class QuizzHistoryService {

    @Autowired
    private QuizzHistoryRepository quizzHistoryRepository;

    public List<QuizzHistory> findByUserId(long id){
        log.info("Finding quizz history by user id: {}", id);
        return quizzHistoryRepository.findByUserId(id);
    }

    public boolean existsByUserId(long id){
        log.info("Checking if quizz history exists by user id: {}", id);
        return quizzHistoryRepository.existsByUserId(id);
    }

    public List<QuizzHistory> findAll() {
        log.info("Finding all quizz history");
        return quizzHistoryRepository.findAll();
    }

    public Optional<QuizzHistory> findById(long id){
        log.info("Finding quizz history by id: {}", id);
        return quizzHistoryRepository.findById(id);
    }

    public QuizzHistory updateQuizzHistory(QuizzHistory quizzHistory){
        log.info("Updating quizz history: {}", quizzHistory.getId());
        if (!quizzHistoryRepository.existsById(quizzHistory.getId())) {
            throw new IllegalArgumentException("QuizzHistory not found");
        }
        return quizzHistoryRepository.save(quizzHistory);
    }

    public List<QuizzCompletionStatsDTO> getQuizzCompletionStats(String period) {
        LocalDateTime startDate = LocalDateTime.now().minus(1, ChronoUnit.YEARS);
        String format = period.equalsIgnoreCase("month") ? "%Y-%m" : "%Y-%u";  // month or week
        log.info("Finding quizz completion stats for period: {}", period);
        List<Object[]> results = quizzHistoryRepository.findQuizzCompletionStatsNative(startDate, format);
        return results.stream()
                .map(result -> new QuizzCompletionStatsDTO((String) result[0], ((Number) result[1]).longValue()))
                .collect(Collectors.toList());
    }
}