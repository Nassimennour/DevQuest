package com.project.devQuest.repository;

import com.project.devQuest.model.Technology;
import com.project.devQuest.model.User;
import com.project.devQuest.model.UserProgress;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserProgressRepositoryTest {

    @Autowired
    private UserProgressRepository userProgressRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void whenFindByUserId_thenReturnUserProgress() {
        // Given
        User user = new User();
        user.setUsername("user");
        user.setPassword("password123");
        user.setEmail("user@mail.com");
        entityManager.persistAndFlush(user);

        UserProgress userProgress = new UserProgress();
        userProgress.setUser(user);
        entityManager.persistAndFlush(userProgress);

        // When
        List<UserProgress> foundProgress = userProgressRepository.findByUserId(user.getId());

        // Then
        assertTrue(foundProgress.size() == 1);
        assertEquals(foundProgress.get(0).getUser().getId(), user.getId());
    }

    @Test
    public void whenFindByUserIdAndTechnologyId_thenReturnUserProgress() {
        // Given
        User user = new User();
        user.setUsername("user");
        user.setPassword("password123");
        user.setEmail("user@mail.com");
        entityManager.persistAndFlush(user);

        Technology technology = new Technology();
        technology.setName("Java");
        entityManager.persistAndFlush(technology);

        UserProgress userProgress = new UserProgress();
        userProgress.setUser(user);
        userProgress.setTechnology(technology);
        entityManager.persistAndFlush(userProgress);

        // When
        Optional<UserProgress> foundProgress = userProgressRepository.findByUserIdAndTechnologyId(user.getId(), technology.getId());

        // Then
        assertTrue(foundProgress.isPresent());
        assertEquals(foundProgress.get().getUser().getId(), user.getId());
        assertEquals(foundProgress.get().getTechnology().getId(), technology.getId());
    }

    @Test
    public void whenFindByTechnologyIdOrderByProgressPercentageDesc_thenReturnUserProgress() {
        // Given
        Technology technology = new Technology();
        entityManager.persistAndFlush(technology);

        User user1 = new User();
        user1.setUsername("user");
        user1.setPassword("password123");
        user1.setEmail("user@mail.com");
        entityManager.persistAndFlush(user1);

        User user2 = new User();
        user2.setUsername("user2");
        user2.setPassword("password123");
        user2.setEmail("user2@gmail.com");
        entityManager.persistAndFlush(user2);

        UserProgress userProgress1 = new UserProgress();
        userProgress1.setUser(user1);
        userProgress1.setTechnology(technology);
        userProgress1.setProgressPercentage(50);
        entityManager.persistAndFlush(userProgress1);

        UserProgress userProgress2 = new UserProgress();
        userProgress2.setUser(user2);
        userProgress2.setTechnology(technology);
        userProgress2.setProgressPercentage(75);
        entityManager.persistAndFlush(userProgress2);

        // When
        List<UserProgress> foundProgress = userProgressRepository.findByTechnologyIdOrderByProgressPercentageDesc(technology.getId());

        // Then
        assertTrue(foundProgress.size() == 2);
        assertEquals(foundProgress.get(0).getProgressPercentage(), 75);
        assertEquals(foundProgress.get(1).getProgressPercentage(), 50);
    }

    @Test
    public void whenFindByUserIdAndLastActivityDateAfter_thenReturnUserProgress() {
        // Given
        User user = new User();
        user.setUsername("user");
        user.setPassword("password123");
        user.setEmail("user@mail.com");
        entityManager.persistAndFlush(user);

        UserProgress userProgress = new UserProgress();
        userProgress.setUser(user);
        userProgress.setLastActivityDate(new Date());
        entityManager.persistAndFlush(userProgress);

        // When
        List<UserProgress> foundProgress = userProgressRepository.findByUserIdAndLastActivityDateAfter(user.getId(), new Date(System.currentTimeMillis() - 1000));

        // Then
        assertTrue(foundProgress.size() == 1);
        assertEquals(foundProgress.get(0).getUser().getId(), user.getId());
    }

    @Test
    public void whenFindByUserIdOrderByProgressPercentageDesc_thenReturnUserProgress() {
        // Given
        User user = new User();
        user.setUsername("user");
        user.setPassword("password123");
        user.setEmail("user@mail.com");
        entityManager.persistAndFlush(user);

        UserProgress userProgress1 = new UserProgress();
        userProgress1.setUser(user);
        userProgress1.setProgressPercentage(50);
        entityManager.persistAndFlush(userProgress1);

        UserProgress userProgress2 = new UserProgress();
        userProgress2.setUser(user);
        userProgress2.setProgressPercentage(75);
        entityManager.persistAndFlush(userProgress2);

        // When
        List<UserProgress> foundProgress = userProgressRepository.findByUserIdOrderByProgressPercentageDesc(user.getId());

        // Then
        assertTrue(foundProgress.size() == 2);
        assertEquals(foundProgress.get(0).getProgressPercentage(), 75);
        assertEquals(foundProgress.get(1).getProgressPercentage(), 50);
    }
}