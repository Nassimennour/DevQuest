package com.project.devQuest.repository;

import com.project.devQuest.model.CodingChallenge;
import com.project.devQuest.model.Difficulty;
import com.project.devQuest.model.Technology;
import com.project.devQuest.model.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CodingChallengeRepositoryTest {

    @Autowired
    private CodingChallengeRepository codingChallengeRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void whenFindByTechnologyId_thenReturnCodingChallenges() {
        // Given
        Technology technology = new Technology();
        technology.setName("Java");
        entityManager.persistAndFlush(technology);

        CodingChallenge codingChallenge = new CodingChallenge();
        codingChallenge.setTechnology(technology);
        entityManager.persistAndFlush(codingChallenge);

        // When
        List<CodingChallenge> foundChallenges = codingChallengeRepository.findByTechnologyId(technology.getId());

        // Then
        assertTrue(foundChallenges.size() == 1);
        assertEquals(foundChallenges.get(0).getTechnology().getId(), technology.getId());
    }

    @Test
    public void whenFindByCreatorId_thenReturnCodingChallenges() {
        // Given
        User creator = new User();
        creator.setUsername("user");
        creator.setPassword("password123");
        creator.setEmail("user@mail.com");
        entityManager.persistAndFlush(creator);

        CodingChallenge codingChallenge = new CodingChallenge();
        codingChallenge.setCreator(creator);
        entityManager.persistAndFlush(codingChallenge);

        // When
        List<CodingChallenge> foundChallenges = codingChallengeRepository.findByCreatorId(creator.getId());

        // Then
        assertTrue(foundChallenges.size() == 1);
        assertEquals(foundChallenges.get(0).getCreator().getId(), creator.getId());
    }

    @Test
    public void whenFindByDifficulty_thenReturnCodingChallenges() {
        // Given
        Difficulty difficulty = Difficulty.EASY;

        CodingChallenge codingChallenge = new CodingChallenge();
        codingChallenge.setDifficulty(difficulty);
        entityManager.persistAndFlush(codingChallenge);

        // When
        List<CodingChallenge> foundChallenges = codingChallengeRepository.findByDifficulty(difficulty);

        // Then
        assertTrue(foundChallenges.size() == 1);
        assertEquals(foundChallenges.get(0).getDifficulty(), difficulty);
    }

    @Test
    public void whenFindByCreatorIdAndTechnologyId_thenReturnCodingChallenges() {
        // Given
        User creator = new User();
        creator.setUsername("user");
        creator.setPassword("password123");
        creator.setEmail("user@mail.com");
        entityManager.persistAndFlush(creator);

        Technology technology = new Technology();
        entityManager.persistAndFlush(technology);

        CodingChallenge codingChallenge = new CodingChallenge();
        codingChallenge.setCreator(creator);
        codingChallenge.setTechnology(technology);
        entityManager.persistAndFlush(codingChallenge);

        // When
        List<CodingChallenge> foundChallenges = codingChallengeRepository.findByCreatorIdAndTechnologyId(creator.getId(), technology.getId());

        // Then
        assertTrue(foundChallenges.size() == 1);
        assertEquals(foundChallenges.get(0).getCreator().getId(), creator.getId());
        assertEquals(foundChallenges.get(0).getTechnology().getId(), technology.getId());
    }
}