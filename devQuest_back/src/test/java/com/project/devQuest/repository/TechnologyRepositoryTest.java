package com.project.devQuest.repository;

import com.project.devQuest.model.Category;
import com.project.devQuest.model.Technology;
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
public class TechnologyRepositoryTest {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private TechnologyRepository technologyRepository;

    @Test
    public void whenFindByName_thenReturnTechnology() {
        // Given
        Technology technology = new Technology();
        technology.setName("Java");
        entityManager.persist(technology);
        Technology technology2 = new Technology();
        technology2.setName("Python");
        entityManager.persist(technology2);
        entityManager.flush();
        // When
        List<Technology> foundTechnology = technologyRepository.findByName(technology.getName());
        // Then
        assertTrue(foundTechnology.size() == 1);
        assertEquals(foundTechnology.get(0).getName(), "Java");
    }

    @Test
    public void whenFindByCategoryId_thenReturnTechnology() {
        // Given
        Category category = new Category();
        category.setName("Backend");
        entityManager.persist(category);
        entityManager.flush();

        Technology technology = new Technology();
        technology.setName("Java");
        technology.setCategory(category);
        entityManager.persist(technology);

        Technology technology2 = new Technology();
        technology2.setName("Python");
        technology2.setCategory(category);
        entityManager.persist(technology2);
        entityManager.flush();
        // When
        List<Technology> foundTechnology = technologyRepository.findByCategoryId(category.getId());
        // Then
        assertTrue(foundTechnology.size() == 2);
        for (Technology tech: foundTechnology) {
            assertEquals(tech.getCategory().getId(), category.getId());
        }
    }
}
