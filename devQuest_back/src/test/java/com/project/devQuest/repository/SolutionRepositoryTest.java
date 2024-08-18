package com.project.devQuest.repository;

import com.project.devQuest.model.CodingChallenge;
import com.project.devQuest.model.Solution;
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
public class SolutionRepositoryTest {

    @Autowired
    private SolutionRepository solutionRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void whenFindByUserId_thenReturnSolutions() {
        // Given
        User user = new User();
        user.setUsername("user");
        user.setPassword("password123");
        user.setEmail("user@mail.com");
        entityManager.persistAndFlush(user);

        Solution solution = new Solution();
        solution.setUser(user);
        entityManager.persistAndFlush(solution);

        // When
        List<Solution> foundSolutions = solutionRepository.findByUserId(user.getId());

        // Then
        assertTrue(foundSolutions.size() == 1);
        assertEquals(foundSolutions.get(0).getUser().getId(), user.getId());
    }

    @Test
    public void whenFindByCodingChallengeId_thenReturnSolutions() {
        // Given
        CodingChallenge codingChallenge = new CodingChallenge();
        entityManager.persistAndFlush(codingChallenge);

        Solution solution = new Solution();
        solution.setCodingChallenge(codingChallenge);
        entityManager.persistAndFlush(solution);

        // When
        List<Solution> foundSolutions = solutionRepository.findByCodingChallengeId(codingChallenge.getId());

        // Then
        assertTrue(foundSolutions.size() == 1);
        assertEquals(foundSolutions.get(0).getCodingChallenge().getId(), codingChallenge.getId());
    }
}