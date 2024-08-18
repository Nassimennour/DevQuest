package com.project.devQuest.service;
import com.project.devQuest.dto.QuestionDTO;
import com.project.devQuest.model.Difficulty;
import com.project.devQuest.model.Question;
import com.project.devQuest.model.Quizz;
import com.project.devQuest.repository.QuestionRepository;
import com.project.devQuest.repository.QuizzRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class QuestionServiceTest {

    @InjectMocks
    private QuestionService questionService;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private QuizzRepository quizzRepository;

    @Test
    public void whenGetAllQuestions_thenReturnQuestionList() {
        Question question = new Question();
        when(questionRepository.findByQuizzId(1L)).thenReturn(Collections.singletonList(question));

        List<Question> questionList = questionService.getAllQuestions(1L);

        assertEquals(1, questionList.size());
        verify(questionRepository, times(1)).findByQuizzId(1L);
    }

    @Test
    public void whenGetQuestionById_thenReturnQuestion() {
        Question question = new Question();
        when(questionRepository.findById(1L)).thenReturn(Optional.of(question));

        Question foundQuestion = questionService.getQuestionById(1L);

        assertNotNull(foundQuestion);
        verify(questionRepository, times(1)).findById(1L);
    }

    @Test
    public void whenAddQuestion_thenReturnSavedQuestion() {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setQuestion("What is Java?");
        questionDTO.setOptions(new String[]{"A", "B", "C", "D"});
        questionDTO.setCorrectAnswer("A");
        questionDTO.setDifficulty("EASY");
        questionDTO.setQuizzId(1L);

        Quizz quizz = new Quizz();
        Question question = new Question();
        question.setQuestion("What is Java?");
        when(quizzRepository.findById(1L)).thenReturn(Optional.of(quizz));
        when(questionRepository.save(any(Question.class))).thenReturn(question);

        Question savedQuestion = questionService.addQuestion(questionDTO);

        assertNotNull(savedQuestion);
        assertEquals("What is Java?", savedQuestion.getQuestion());
        verify(quizzRepository, times(1)).findById(1L);
        verify(questionRepository, times(1)).save(any(Question.class));
    }

    @Test
    public void whenUpdateQuestion_thenReturnUpdatedQuestion() {
        Question question = new Question();
        question.setId(1L);
        question.setQuestion("Updated question");

        when(questionRepository.save(question)).thenReturn(question);

        Question updatedQuestion = questionService.updateQuestion(question);

        assertNotNull(updatedQuestion);
        assertEquals("Updated question", updatedQuestion.getQuestion());
        verify(questionRepository, times(1)).save(question);
    }

    @Test
    public void whenDeleteQuestion_thenVerifyDeletion() {
        doNothing().when(questionRepository).deleteById(1L);

        questionService.deleteQuestion(1L);

        verify(questionRepository, times(1)).deleteById(1L);
    }

    @Test
    public void whenDeleteAllQuestions_thenVerifyDeletion() {
        Question question = new Question();
        when(questionRepository.findByQuizzId(1L)).thenReturn(Collections.singletonList(question));
        doNothing().when(questionRepository).deleteAll(anyList());

        questionService.deleteAllQuestions(1L);

        verify(questionRepository, times(1)).findByQuizzId(1L);
        verify(questionRepository, times(1)).deleteAll(anyList());
    }

    @Test
    public void whenGetQuestionsByQuizzId_thenReturnQuestionList() {
        Question question = new Question();
        when(questionRepository.findByQuizzId(1L)).thenReturn(Collections.singletonList(question));

        List<Question> questionList = questionService.getQuestionsByQuizzId(1L);

        assertEquals(1, questionList.size());
        verify(questionRepository, times(1)).findByQuizzId(1L);
    }
}
