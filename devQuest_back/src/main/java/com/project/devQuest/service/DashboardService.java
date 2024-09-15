package com.project.devQuest.service;

import com.project.devQuest.model.Dashboard;
import com.project.devQuest.model.Quizz;
import com.project.devQuest.model.CodingChallenge;
import com.project.devQuest.model.User;
import com.project.devQuest.repository.DashboardRepository;
import com.project.devQuest.repository.QuizzRepository;
import com.project.devQuest.repository.CodingChallengeRepository;
import com.project.devQuest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<Dashboard> getAllDashboards() {
        return dashboardRepository.findAll();
    }

    public void deleteDashboard(Long id) {
        dashboardRepository.deleteById(id);
    }

    public Dashboard getDashboardById(Long id) {
        return dashboardRepository.findById(id).orElseThrow(() -> new RuntimeException("Dashboard not found"));
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

    public Dashboard removeSuggestedQuizz(Long userId, Long quizzId) {
        Dashboard dashboard = dashboardRepository.findByUserId(userId);
        Quizz quizz = quizzRepository.findById(quizzId).orElseThrow(() -> new RuntimeException("Quizz not found"));
        dashboard.getSuggestedQuizzes().remove(quizz);
        return dashboardRepository.save(dashboard);
    }

    public Dashboard removeSuggestedCodingChallenge(Long userId, Long codingChallengeId) {
        Dashboard dashboard = dashboardRepository.findByUserId(userId);
        CodingChallenge codingChallenge = codingChallengeRepository.findById(codingChallengeId).orElseThrow(() -> new RuntimeException("Coding Challenge not found"));
        dashboard.getSuggestedCodingChallenges().remove(codingChallenge);
        return dashboardRepository.save(dashboard);
    }

    //UPDATE DASHBOARD
    public Dashboard updateDashboard(Dashboard dashboard) {
        if (!dashboardRepository.existsById(dashboard.getId())) {
            throw new IllegalArgumentException("Dashboard not found");
        }
        return dashboardRepository.save(dashboard);
    }

    // Compute suggested quizzes and coding challenges
    public void computeSuggestions(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Dashboard dashboard = dashboardRepository.findByUserId(userId);

        // Suggest quizzes based on user's skills
        List<Quizz> suggestedQuizzes = quizzRepository.findAll().stream()
                .filter(quizz -> user.getSkills().contains(quizz.getTechnology()))
                .collect(Collectors.toList());

        // Suggest coding challenges based on user's skills
        List<CodingChallenge> suggestedCodingChallenges = codingChallengeRepository.findAll().stream()
                .filter(challenge -> user.getSkills().contains(challenge.getTechnology()))
                .collect(Collectors.toList());

        dashboard.setSuggestedQuizzes(suggestedQuizzes);
        dashboard.setSuggestedCodingChallenges(suggestedCodingChallenges);
        dashboardRepository.save(dashboard);
    }
}