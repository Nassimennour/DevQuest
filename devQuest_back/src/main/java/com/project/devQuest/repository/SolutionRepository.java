package com.project.devQuest.repository;

import com.project.devQuest.model.Solution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SolutionRepository extends JpaRepository<Solution, Long> {
    List<Solution> findByUserId(Long id);
    List<Solution> findByCodingChallengeId(Long id);
    List<Solution> findTop10ByOrderBySubmissionDateDesc();
    long countBySubmissionDate(LocalDateTime date);
}
