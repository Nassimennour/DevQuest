package com.project.devQuest.repository;

import com.project.devQuest.model.CodingChallenge;
import com.project.devQuest.model.Difficulty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodingChallengeRepository extends JpaRepository<CodingChallenge, Long> {
    List<CodingChallenge> findByTechnologyId(Long id);
    List<CodingChallenge> findByCreatorId(Long id);
    List<CodingChallenge> findByDifficulty(Difficulty difficulty);
    List<CodingChallenge> findByCreatorIdAndTechnologyId(Long id, Long technologyId);
}
