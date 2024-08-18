package com.project.devQuest.repository;

import com.project.devQuest.model.QuizzHistory;
import com.project.devQuest.model.User;
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
public class QuizzHistoryRepositoryTest {

    @Autowired
    private QuizzHistoryRepository quizzHistoryRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void whenFindByUserId_thenReturnQuizzHistories() {
        // Given
        User user = new User();
        user.setEmail("user@email.com");
        user.setUsername("user");
        user.setPassword("password123");
        entityManager.persist(user);
        entityManager.flush();  // Commit the data

        QuizzHistory quizzHistory = new QuizzHistory();
        quizzHistory.setUser(user);
        entityManager.persist(quizzHistory);
        entityManager.flush();

        // When
        List<QuizzHistory> foundQuizzHistories = quizzHistoryRepository.findByUserId(user.getId());

        // Then
        assertTrue(foundQuizzHistories.size() == 1);
        assertEquals(foundQuizzHistories.get(0).getUser().getId(), user.getId());
    }

    @Test
    public void whenExistsByUserId_thenReturnTrue() {
        // Given
        User user = new User();
        user.setEmail("user@email.com");
        user.setUsername("user");
        user.setPassword("password123");
        entityManager.persist(user);
        entityManager.flush();

        QuizzHistory quizzHistory = new QuizzHistory();
        quizzHistory.setUser(user);
        entityManager.persist(quizzHistory);
        entityManager.flush();

        // When
        boolean exists = quizzHistoryRepository.existsByUserId(user.getId());

        // Then
        assertTrue(exists);
    }

    @Test
    public void whenNotExistsByUserId_thenReturnFalse() {
        // Given
        User user = new User();
        user.setEmail("user@email.com");
        user.setUsername("user");
        user.setPassword("password123");
        entityManager.persist(user);
        entityManager.flush();

        // When
        boolean exists = quizzHistoryRepository.existsByUserId(user.getId());

        // Then
        assertTrue(!exists);
    }
}