package com.project.devQuest.repository;

import com.project.devQuest.model.Question;
import com.project.devQuest.model.Quizz;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class QuestionRepositoryTest {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private EntityManager entityManager;

    @Test
    public void whenFindByQuizzId_thenReturnQuestions() {
        // Given
        Quizz quizz = new Quizz();
        quizz.setTitle("Java Basics");
        entityManager.persist(quizz);
        entityManager.flush();

        Question question1 = new Question();
        question1.setQuizz(quizz);
        question1.setQuestion("What is Java?");
        entityManager.persist(question1);

        Question question2 = new Question();
        question2.setQuizz(quizz);
        question2.setQuestion("What is Java?");
        entityManager.persist(question2);
        entityManager.flush();

        // When
        List<Question> foundQuestions = questionRepository.findByQuizzId(quizz.getId());

        // Then
        assertTrue(foundQuestions.size() == 2);
        for (Question question : foundQuestions) {
            assertTrue(question.getQuizz().getId() == quizz.getId());
        }
    }


}
