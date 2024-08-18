package com.project.devQuest.repository;

import com.project.devQuest.model.Ranking;
import com.project.devQuest.model.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RankingRepositoryTest {

    @Autowired
    private RankingRepository rankingRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void whenFindByUserId_thenReturnRanking() {
        // Given
        User user = new User();
        user.setUsername("user");
        user.setPassword("password123");
        user.setEmail("user@mail.com");
        entityManager.persistAndFlush(user);

        Ranking ranking = new Ranking();
        ranking.setUser(user);
        entityManager.persistAndFlush(ranking);

        // When
        Optional<Ranking> foundRanking = rankingRepository.findByUserId(user.getId());

        // Then
        assertTrue(foundRanking.isPresent());
        assertEquals(foundRanking.get().getUser().getId(), user.getId());
    }
}