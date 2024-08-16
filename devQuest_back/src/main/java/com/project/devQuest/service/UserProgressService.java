package com.project.devQuest.service;

import com.project.devQuest.model.UserProgress;
import com.project.devQuest.repository.UserProgressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserProgressService {

    @Autowired
    private UserProgressRepository userProgressRepository;
    private final static Logger logger = LoggerFactory.getLogger(UserProgressService.class);

    public List<UserProgress> getAllUserProgress() {
        logger.info("Fetching all user progress");
        return userProgressRepository.findAll();
    }

    public UserProgress getUserProgressById(Long id) {
        logger.info("Fetching user progress with id: " + id);
        return userProgressRepository.findById(id).orElseThrow(() -> new RuntimeException("User progress not found"));
    }

    public List<UserProgress> getUserProgressByUserId(Long userId) {
        logger.info("Fetching user progress for user with id: " + userId);
        return userProgressRepository.findByUserId(userId);
    }

   public UserProgress getUserProgressByUserIdAndTechnologyId(Long userId, long technologyId) {
        logger.info("Fetching user progress for user with id: " + userId + " and technology with id: " + technologyId);
        return userProgressRepository.findByUserIdAndTechnologyId(userId, technologyId).orElseThrow(() -> new RuntimeException("User progress not found"));
   }

   public List<UserProgress> getUserProgressByTechnologyIdOrderByProgressPercentageDesc(Long technologyId) {
        logger.info("Fetching user progress for technology with id: " + technologyId + " sorted by progress percentage");
        return userProgressRepository.findByTechnologyIdOrderByProgressPercentageDesc(technologyId);
   }

   public List<UserProgress> getUserProgressByUserIdAndLastActivityDateAfter(long userId, Date lastActivityDate) {
        logger.info("Fetching user progress for user with id: " + userId + " and last activity date after: " + lastActivityDate);
        return userProgressRepository.findByUserIdAndLastActivityDateAfter(userId, lastActivityDate);
   }

    public List<UserProgress> getUserProgressByUserIdOrderByProgressPercentageDesc(long userId) {
          logger.info("Fetching user progress for user with id: " + userId + " sorted by progress percentage");
          return userProgressRepository.findByUserIdOrderByProgressPercentageDesc(userId);
    }

    public void DeleteUserProgress(Long id) {
        logger.info("Deleting user progress with id: " + id);
        userProgressRepository.deleteById(id);
    }




}
