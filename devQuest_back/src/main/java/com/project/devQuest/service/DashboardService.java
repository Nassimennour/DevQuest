package com.project.devQuest.service;

import com.project.devQuest.model.Dashboard;
import com.project.devQuest.model.Quizz;
import com.project.devQuest.model.CodingChallenge;
import com.project.devQuest.repository.DashboardRepository;
import com.project.devQuest.repository.QuizzRepository;
import com.project.devQuest.repository.CodingChallengeRepository;
import com.project.devQuest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private DashboardRepository dashboardRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuizzRepository quizzRepository;
    @Autowired
    private CodingChallengeRepository codingChallengeRepository;

    public Dashboard getDashboardByUserId(Long userId) {
        return dashboardRepository.findByUserId(userId);
    }

    public Dashboard addSuggestedQuizz(Long userId, Long quizzId) {
        Dashboard dashboard = dashboardRepository.findByUserId(userId);
        Quizz quizz = quizzRepository.findById(quizzId).orElseThrow(() -> new RuntimeException("Quizz not found"));
        dashboard.getSuggestedQuizzes().add(quizz);
        return dashboardRepository.save(dashboard);
    }

    public Dashboard addSuggestedCodingChallenge(Long userId, Long codingChallengeId) {
        Dashboard dashboard = dashboardRepository.findByUserId(userId);
        CodingChallenge codingChallenge = codingChallengeRepository.findById(codingChallengeId).orElseThrow(() -> new RuntimeException("Coding Challenge not found"));
        dashboard.getSuggestedCodingChallenges().add(codingChallenge);
        return dashboardRepository.save(dashboard);
    }
}