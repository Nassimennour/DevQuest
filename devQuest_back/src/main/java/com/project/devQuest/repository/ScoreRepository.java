package com.project.devQuest.repository;

import com.project.devQuest.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findByUserId(Long id);
    List<Score> findByQuizzId(Long id);
    List<Score> findByQuizzIdAndUserId(Long quizzId, Long userId);
    @Query("SELECT AVG(s.score) FROM Score s WHERE s.quizz.id = :id")
    Double findAverageScoreByQuizzId(@Param("id") Long id);
    @Query("SELECT AVG(s.score) FROM Score s WHERE s.user.id = :userId")
    Double findAverageScoreByUserId(@Param("userId") Long userId);
    // Average score for a user in a technology
    @Query("SELECT AVG(s.score) FROM Score s WHERE s.user.id = :userId AND s.quizz.technology.id = :technologyId")
    Double findAverageScoreByUserIdAndTechnologyId(@Param("userId") Long userId, @Param("technologyId") Long technologyId);
    // Average score for a user in a category
    @Query("SELECT AVG(s.score) FROM Score s WHERE s.user.id = :userId AND s.quizz.technology.category.id = :categoryId")
    Double findAverageScoreByUserIdAndCategoryId(@Param("userId") Long userId, @Param("categoryId") Long categoryId);
}
