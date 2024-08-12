package com.project.devQuest.service;

import com.project.devQuest.model.QuizzHistory;
import com.project.devQuest.repository.QuizzHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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


}
