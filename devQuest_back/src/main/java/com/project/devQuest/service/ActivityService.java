package com.project.devQuest.service;


import com.project.devQuest.dto.ActivityDTO;
import com.project.devQuest.dto.UserActivityDTO;
import com.project.devQuest.model.*;
import com.project.devQuest.repository.*;
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

    public List<ActivityDTO> getRecentActivities(){
        List<ActivityDTO> activities = new ArrayList<>();
        // fetch recent users, quizz, coding challenges, quizz history and solutions
        List<User> recentUsers = userRepository.findTop10ByOrderByRegistrationDateDesc();
        List<Quizz> recentQuizzes = quizzRepository.findTop10ByOrderByCreationDateDesc();
        List<CodingChallenge> recentCodingChallenges = codingChallengeRepository.findTop10ByOrderByCreationDateDesc();
        List<QuizzHistory> recentQuizzHistory = quizzHistoryRepository.findTop10ByOrderByCompltedAtDesc();
        List<Solution> recentSolutions = solutionRepository.findTop10ByOrderBySubmissionDateDesc();
        // Add activities to the activity list
        recentUsers.forEach(user -> activities.add(new ActivityDTO("User registered", user.getFullname(), "", user.getRegistrationDate())));
        recentQuizzes.forEach(quizz -> activities.add(new ActivityDTO("Quizz created", quizz.getTitle(), "", quizz.getCreationDate())));
        recentCodingChallenges.forEach(codingChallenge -> activities.add(new ActivityDTO("Coding challenge created", codingChallenge.getTitle(), "", codingChallenge.getCreationDate())));
        recentQuizzHistory.forEach(quizzHistory -> activities.add(new ActivityDTO("Quizz completed", quizzHistory.getQuizz().getTitle(), quizzHistory.getUser().getFullname(), Date.from(quizzHistory.getCompltedAt().atZone(ZoneId.systemDefault()).toInstant()))));
        recentSolutions.forEach(solution -> activities.add(new ActivityDTO("Solution submitted", solution.getCodingChallenge().getTitle(), solution.getUser().getFullname(), Date.from(solution.getSubmissionDate().atZone(ZoneId.systemDefault()).toInstant()))));
        // Sort activities by date
        activities.sort((a1,a2) -> a2.getDate().compareTo(a1.getDate())); // sort in descending order
        return activities;
    }

    // Get users activity stats for the last 30 days
    public List<UserActivityDTO> getUserActivity(){
        List<UserActivityDTO> userActivities = new ArrayList<>();
        // compute users activity stats
        for (Date date = new Date(System.currentTimeMillis() - 30 * 24 * 60 * 60 * 1000); date.before(new Date()); date.setTime(date.getTime() + 24 * 60 * 60 * 1000)){
            long quizzCount = quizzHistoryRepository.countByCompltedAt(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
            long solutionCount = solutionRepository.countBySubmissionDate(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
            userActivities.add(new UserActivityDTO(date, quizzCount + solutionCount));
        }
        return userActivities;
    }


}
