package com.project.devQuest.repository;

import com.project.devQuest.model.UserProgress;
import jakarta.validation.Valid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserProgressRepository extends JpaRepository<UserProgress, Long> {
    List<UserProgress> findByUserId(Long userId);
    Optional<UserProgress> findByUserIdAndTechnologyId(Long userId, Long technologyId);
    List<UserProgress> findByTechnologyIdOrderByProgressPercentageDesc(Long technologyId);
    List<UserProgress> findByUserIdAndLastActivityDateAfter(long user_id, Date lastActivityDate);
    // method to find User progress sorted by progress percentage
    List<UserProgress> findByUserIdOrderByProgressPercentageDesc(long userId);
}
