package com.project.devQuest.repository;

import com.project.devQuest.model.Quizz;
import com.project.devQuest.model.Technology;
import com.project.devQuest.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class QuizzRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private QuizzRepository quizzRepository;


    @Test
    public void whenFindByTitle_thenReturnQuizzes(){
        // Given
        Quizz quizz1 = new Quizz();
        Quizz quizz2 = new Quizz();
        quizz1.setTitle("test");
        quizz2.setTitle("test");
        entityManager.persistAndFlush(quizz1);
        entityManager.persistAndFlush(quizz2);
        // when
        List<Quizz> foundQuizzes = quizzRepository.findByTitle("test");
        // Then
        assertTrue(foundQuizzes.size() == 2);
        for (Quizz quizz : foundQuizzes) {
            assertTrue(quizz.getTitle().equals("test"));
        }
    }

    @Test
    public void whenFindByTechnologyId_thenReturnQuizzes() {
        // Given
        Technology technology = new Technology();
        technology.setName("Spring");
        entityManager.persist(technology);
        entityManager.flush();

        Quizz quizz1 = new Quizz();
        quizz1.setTitle("Spring Basics");
        quizz1.setTechnology(technology);
        entityManager.persist(quizz1);

        Quizz quizz2 = new Quizz();
        quizz2.setTitle("Spring Advanced");
        quizz2.setTechnology(technology);
        entityManager.persist(quizz2);

        entityManager.flush();

        // When
        List<Quizz> foundQuizzes = quizzRepository.findByTechnologyId(technology.getId());

        // Then
        assertNotNull(foundQuizzes);
        assertEquals(2, foundQuizzes.size());
        assertEquals("Spring Basics", foundQuizzes.get(0).getTitle());
        assertEquals("Spring Advanced", foundQuizzes.get(1).getTitle());
    }

    @Test
    public void whenFindByCreatorId_thenReturnQuizzes() {
        // Given
        User creator = new User();
        creator.setUsername("john_doe");
        creator.setEmail("john.doe@example.com");
        creator.setPassword("password");
        creator = entityManager.merge(creator); // Merge the entity instead of persisting

        Quizz quizz1 = new Quizz();
        quizz1.setTitle("Java Basics");
        quizz1.setCreator(creator);
        entityManager.persist(quizz1);

        Quizz quizz2 = new Quizz();
        quizz2.setTitle("Java Advanced");
        quizz2.setCreator(creator);
        entityManager.persist(quizz2);

        entityManager.flush();

        // When
        List<Quizz> foundQuizzes = quizzRepository.findByCreatorId(creator.getId());

        // Then
        assertNotNull(foundQuizzes);
        assertEquals(2, foundQuizzes.size());
        assertEquals("Java Basics", foundQuizzes.get(0).getTitle());
        assertEquals("Java Advanced", foundQuizzes.get(1).getTitle());
    }

}
