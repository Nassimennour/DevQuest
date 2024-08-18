package com.project.devQuest.repository;

import com.project.devQuest.model.*;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ScoreRepositoryTest {

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void whenFindByUserId_thenReturnScores() {
        // Given
        User user = new User();
        user.setEmail("user@email.com");
        user.setUsername("user");
        user.setPassword("password123");
        entityManager.persist(user);
        entityManager.flush();

        Quizz quizz = new Quizz();
        entityManager.persist(quizz);
        entityManager.flush();

        Score score = new Score();
        score.setUser(user);
        score.setQuizz(quizz);
        score.setScore(85);
        entityManager.persist(score);
        entityManager.flush();

        // When
        List<Score> foundScores = scoreRepository.findByUserId(user.getId());

        // Then
        assertTrue(foundScores.size() == 1);
        assertEquals(foundScores.get(0).getScore(), 85);
    }

    @Test
    public void whenFindByQuizzId_thenReturnScores() {
        // Given
        User user = new User();
        user.setEmail("user@email.com");
        user.setUsername("user");
        user.setPassword("password123");
        entityManager.persist(user);
        entityManager.flush();

        Quizz quizz = new Quizz();
        entityManager.persist(quizz);
        entityManager.flush();

        Score score = new Score();
        score.setUser(user);
        score.setQuizz(quizz);
        score.setScore(85);
        entityManager.persist(score);
        entityManager.flush();

        // When
        List<Score> foundScores = scoreRepository.findByQuizzId(quizz.getId());

        // Then
        assertTrue(foundScores.size() == 1);
        assertEquals(foundScores.get(0).getScore(), 85);
    }

    @Test
    public void whenFindByQuizzIdAndUserId_thenReturnScores() {
        // Given
        User user = new User();
        user.setEmail("user@email.com");
        user.setUsername("user");
        user.setPassword("password123");
        entityManager.persist(user);
        entityManager.flush();

        Quizz quizz = new Quizz();
        entityManager.persist(quizz);
        entityManager.flush();

        Score score = new Score();
        score.setUser(user);
        score.setQuizz(quizz);
        score.setScore(85);
        entityManager.persist(score);
        entityManager.flush();

        // When
        List<Score> foundScores = scoreRepository.findByQuizzIdAndUserId(quizz.getId(), user.getId());

        // Then
        assertTrue(foundScores.size() == 1);
        assertEquals(foundScores.get(0).getScore(), 85);
    }

    @Test
    public void whenFindAverageScoreByQuizzId_thenReturnAverageScore() {
        // Given
        User user = new User();
        user.setEmail("user@email.com");
        user.setUsername("user");
        user.setPassword("password123");
        entityManager.persist(user);
        entityManager.flush();

        Quizz quizz = new Quizz();
        entityManager.persist(quizz);
        entityManager.flush();

        Score score1 = new Score();
        score1.setUser(user);
        score1.setQuizz(quizz);
        score1.setScore(85);
        entityManager.persist(score1);


        Score score2 = new Score();
        score2.setUser(user);
        score2.setQuizz(quizz);
        score2.setScore(95);
        entityManager.persist(score2);
        entityManager.flush();

        // When
        Double averageScore = scoreRepository.findAverageScoreByQuizzId(quizz.getId());

        // Then
        assertEquals(averageScore, 90.0);
    }

    @Test
    public void whenFindAverageScoreByUserId_thenReturnAverageScore() {
        // Given
        User user = new User();
        user.setEmail("user@email.com");
        user.setUsername("user");
        user.setPassword("password123");
        entityManager.persist(user);
        entityManager.flush();

        Quizz quizz1 = new Quizz();
        entityManager.persist(quizz1);

        Quizz quizz2 = new Quizz();
        entityManager.persist(quizz2);

        Score score1 = new Score();
        score1.setUser(user);
        score1.setQuizz(quizz1);
        score1.setScore(85);
        entityManager.persist(score1);

        Score score2 = new Score();
        score2.setUser(user);
        score2.setQuizz(quizz2);
        score2.setScore(95);
        entityManager.persist(score2);

        // When
        Double averageScore = scoreRepository.findAverageScoreByUserId(user.getId());

        // Then
        assertEquals(averageScore, 90.0);
    }

    @Test
    public void whenFindAverageScoreByUserIdAndTechnologyId_thenReturnAverageScore() {
        // Given
        User user = new User();
        user.setEmail("user@email.com");
        user.setUsername("user");
        user.setPassword("password123");
        entityManager.persist(user);
        entityManager.flush();

        Technology technology = new Technology();
        technology.setName("Java");
        entityManager.persist(technology);
        entityManager.flush();

        Quizz quizz1 = new Quizz();
        quizz1.setTechnology(technology);
        entityManager.persist(quizz1);

        Quizz quizz2 = new Quizz();
        quizz2.setTechnology(technology);
        entityManager.persist(quizz2);
        entityManager.flush();

        Score score1 = new Score();
        score1.setUser(user);
        score1.setQuizz(quizz1);
        score1.setScore(85);
        entityManager.persist(score1);

        Score score2 = new Score();
        score2.setUser(user);
        score2.setQuizz(quizz2);
        score2.setScore(95);
        entityManager.persist(score2);

        // When
        Double averageScore = scoreRepository.findAverageScoreByUserIdAndTechnologyId(user.getId(), technology.getId());

        // Then
        assertEquals(averageScore, 90.0);
    }

    @Test
    public void whenFindAverageScoreByUserIdAndCategoryId_thenReturnAverageScore() {
        // Given
        User user = new User();
        user.setEmail("user@email.com");
        user.setUsername("user");
        user.setPassword("password123");
        entityManager.persist(user);
        entityManager.flush();

        Category category = new Category();
        category.setName("Backend");
        entityManager.persist(category);
        entityManager.flush();

        Technology technology = new Technology();
        technology.setName("Java");
        technology.setCategory(category);
        entityManager.persist(technology);
        entityManager.flush();

        Quizz quizz1 = new Quizz();
        quizz1.setTechnology(technology);
        entityManager.persist(quizz1);

        Quizz quizz2 = new Quizz();
        quizz2.setTechnology(technology);
        entityManager.persist(quizz2);
        entityManager.flush();

        Score score1 = new Score();
        score1.setUser(user);
        score1.setQuizz(quizz1);
        score1.setScore(85);
        entityManager.persist(score1);

        Score score2 = new Score();
        score2.setUser(user);
        score2.setQuizz(quizz2);
        score2.setScore(95);
        entityManager.persist(score2);
        entityManager.flush();

        // When
        Double averageScore = scoreRepository.findAverageScoreByUserIdAndCategoryId(user.getId(), category.getId());

        // Then
        assertEquals(averageScore, 90.0);
    }
}