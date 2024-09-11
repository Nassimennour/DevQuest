package com.project.devQuest.service;

import com.project.devQuest.dto.ActivityDTO;
import com.project.devQuest.dto.UserActivityDTO;
import com.project.devQuest.model.*;
import com.project.devQuest.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ActivityService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuizzRepository quizzRepository;
    @Autowired
    private CodingChallengeRepository codingChallengeRepository;
    @Autowired
    private QuizzHistoryRepository quizzHistoryRepository;
    @Autowired
    private SolutionRepository solutionRepository;
    private final static Logger logger = LoggerFactory.getLogger(ActivityService.class);

    public List<ActivityDTO> getRecentActivities() {
        logger.info("Fetching recent activities");
        List<ActivityDTO> activities = new ArrayList<>();
        // fetch recent users, quizz, coding challenges, quizz history and solutions
        List<User> recentUsers = userRepository.findTop10ByOrderByRegistrationDateDesc();
        logger.info("Fetched {} recent users", recentUsers.size());
        List<Quizz> recentQuizzes = quizzRepository.findTop10ByOrderByCreationDateDesc();
        logger.info("Fetched {} recent quizz", recentQuizzes.size());
        List<CodingChallenge> recentCodingChallenges = codingChallengeRepository.findTop10ByOrderByCreationDateDesc();
        logger.info("Fetched {} recent coding challenges", recentCodingChallenges.size());
        List<QuizzHistory> recentQuizzHistory = quizzHistoryRepository.findTop10ByOrderByCompltedAtDesc();
        logger.info("Fetched {} recent quizz history", recentQuizzHistory.size());
        List<Solution> recentSolutions = solutionRepository.findTop10ByOrderBySubmissionDateDesc();
        logger.info("Fetched {} recent solutions", recentSolutions.size());
        // Add activities to the activity list
        recentUsers.forEach(user -> {
            if (user.getRegistrationDate() != null) {
                activities.add(new ActivityDTO("User registered", user.getFullname(), "", user.getRegistrationDate()));
            }
        });
        recentQuizzes.forEach(quizz -> {
            if (quizz.getCreationDate() != null) {
                activities.add(new ActivityDTO("Quizz created", quizz.getTitle(), quizz.getCreator().getUsername(), quizz.getCreationDate()));
            }
        });
        recentCodingChallenges.forEach(codingChallenge -> {
            if (codingChallenge.getCreationDate() != null) {
                activities.add(new ActivityDTO("Coding challenge created", codingChallenge.getTitle(), codingChallenge.getCreator().getUsername(), codingChallenge.getCreationDate()));
            }
        });
        recentQuizzHistory.forEach(quizzHistory -> {
            if (quizzHistory.getCompltedAt() != null) {
                activities.add(new ActivityDTO("Quizz completed", quizzHistory.getQuizz().getTitle(), quizzHistory.getUser().getFullname(), Date.from(quizzHistory.getCompltedAt().atZone(ZoneId.systemDefault()).toInstant())));
            }
        });
        recentSolutions.forEach(solution -> {
            if (solution.getSubmissionDate() != null) {
                activities.add(new ActivityDTO("Solution submitted", solution.getCodingChallenge().getTitle(), solution.getUser().getUsername(), Date.from(solution.getSubmissionDate().atZone(ZoneId.systemDefault()).toInstant())));
            }
        });
        // Sort activities by date
        activities.sort((a1, a2) -> a2.getDate().compareTo(a1.getDate())); // sort in descending order
        return activities;
    }

    // Get users activity stats for the last 30 days
    public List<UserActivityDTO> getUserActivity() {
        List<UserActivityDTO> userActivities = new ArrayList<>();
        // compute users activity stats
        for (Date date = new Date(System.currentTimeMillis() - 30 * 24 * 60 * 60 * 1000); date.before(new Date()); date.setTime(date.getTime() + 24 * 60 * 60 * 1000)) {
            long quizzCount = quizzHistoryRepository.countByCompltedAt(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
            long solutionCount = solutionRepository.countBySubmissionDate(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
            userActivities.add(new UserActivityDTO(date, quizzCount + solutionCount));
        }
        return userActivities;
    }
}