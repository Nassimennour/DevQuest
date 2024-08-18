package com.project.devQuest.repository;

import com.project.devQuest.model.Dashboard;
import com.project.devQuest.model.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DashboardRepositoryTest {

    @Autowired
    private DashboardRepository dashboardRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void whenFindByUserId_thenReturnDashboard() {
        // Given
        User user = new User();
        user.setUsername("user");
        user.setPassword("password123");
        user.setEmail("user@mail.com");
        entityManager.persistAndFlush(user);

        Dashboard dashboard = new Dashboard();
        dashboard.setUser(user);
        entityManager.persistAndFlush(dashboard);

        // When
        Dashboard foundDashboard = dashboardRepository.findByUserId(user.getId());

        // Then
        assertEquals(foundDashboard.getUser().getId(), user.getId());
    }
}