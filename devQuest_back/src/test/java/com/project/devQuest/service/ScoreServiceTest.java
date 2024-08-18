package com.project.devQuest.service;

import com.project.devQuest.model.*;
import com.project.devQuest.repository.*;
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
public class ScoreServiceTest {

    @InjectMocks
    private ScoreService scoreService;

    @Mock
    private ScoreRepository scoreRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private QuizzRepository quizzRepository;

    @Mock
    private AnswerRepository answerRepository;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private QuizzHistoryRepository quizzHistoryRepository;

    @Mock
    private UserProgressRepository userProgressRepository;

    @Test
    public void whenComputeAndSaveScore_thenReturnSavedScore() {
        User user = new User();
        Quizz quizz = new Quizz();
        Answer answer = new Answer();
        answer.setQuestionId(1L);
        Question question = new Question();
        question.setCorrectAnswer("correct");
        answer.setAnswer("correct");
        List<Answer> answers = Collections.singletonList(answer);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(quizzRepository.findById(1L)).thenReturn(Optional.of(quizz));
        when(questionRepository.findById(1L)).thenReturn(Optional.of(question));
        when(scoreRepository.save(any(Score.class))).thenReturn(new Score());

        Score savedScore = scoreService.computeAndSaveScore(1L, 1L, answers);

        assertNotNull(savedScore);
        verify(userRepository, times(1)).findById(1L);
        verify(quizzRepository, times(1)).findById(1L);
        verify(questionRepository, times(1)).findById(1L);
        verify(scoreRepository, times(1)).save(any(Score.class));
    }

    @Test
    public void whenGetScoresByUserId_thenReturnScoreList() {
        Score score = new Score();
        when(scoreRepository.findByUserId(1L)).thenReturn(Collections.singletonList(score));

        List<Score> scoreList = scoreService.getScoresByUserId(1L);

        assertEquals(1, scoreList.size());
        verify(scoreRepository, times(1)).findByUserId(1L);
    }

    @Test
    public void whenGetScoresByQuizzId_thenReturnScoreList() {
        Score score = new Score();
        when(scoreRepository.findByQuizzId(1L)).thenReturn(Collections.singletonList(score));

        List<Score> scoreList = scoreService.getScoresByQuizzId(1L);

        assertEquals(1, scoreList.size());
        verify(scoreRepository, times(1)).findByQuizzId(1L);
    }

    @Test
    public void whenGetScoresByQuizzIdAndUserId_thenReturnScoreList() {
        Score score = new Score();
        when(scoreRepository.findByQuizzIdAndUserId(1L, 1L)).thenReturn(Collections.singletonList(score));

        List<Score> scoreList = scoreService.getScoresByQuizzIdAndUserId(1L, 1L);

        assertEquals(1, scoreList.size());
        verify(scoreRepository, times(1)).findByQuizzIdAndUserId(1L, 1L);
    }

    @Test
    public void whenGetAverageScoreByQuizzId_thenReturnAverageScore() {
        when(scoreRepository.findAverageScoreByQuizzId(1L)).thenReturn(85.0);

        double averageScore = scoreService.getAverageScoreByQuizzId(1L);

        assertEquals(85.0, averageScore);
        verify(scoreRepository, times(1)).findAverageScoreByQuizzId(1L);
    }

    @Test
    public void whenGetAverageScoreByUserId_thenReturnAverageScore() {
        when(scoreRepository.findAverageScoreByUserId(1L)).thenReturn(90.0);

        double averageScore = scoreService.getAverageScoreByUserId(1L);

        assertEquals(90.0, averageScore);
        verify(scoreRepository, times(1)).findAverageScoreByUserId(1L);
    }

    @Test
    public void whenGetAverageScoreByUserIdAndTechnologyId_thenReturnAverageScore() {
        when(scoreRepository.findAverageScoreByUserIdAndTechnologyId(1L, 1L)).thenReturn(75.0);

        double averageScore = scoreService.getAverageScoreByUserIdAndTechnologyId(1L, 1L);

        assertEquals(75.0, averageScore);
        verify(scoreRepository, times(1)).findAverageScoreByUserIdAndTechnologyId(1L, 1L);
    }

    @Test
    public void whenGetAverageScoreByUserIdAndCategoryId_thenReturnAverageScore() {
        when(scoreRepository.findAverageScoreByUserIdAndCategoryId(1L, 1L)).thenReturn(80.0);

        double averageScore = scoreService.getAverageScoreByUserIdAndCategoryId(1L, 1L);

        assertEquals(80.0, averageScore);
        verify(scoreRepository, times(1)).findAverageScoreByUserIdAndCategoryId(1L, 1L);
    }

    @Test
    public void whenGetAllScores_thenReturnScoreList() {
        Score score = new Score();
        when(scoreRepository.findAll()).thenReturn(Collections.singletonList(score));

        List<Score> scoreList = scoreService.getAllScores();

        assertEquals(1, scoreList.size());
        verify(scoreRepository, times(1)).findAll();
    }

    @Test
    public void whenGetScoreById_thenReturnScore() {
        Score score = new Score();
        when(scoreRepository.findById(1L)).thenReturn(Optional.of(score));

        Score foundScore = scoreService.getScoreById(1L);

        assertNotNull(foundScore);
        verify(scoreRepository, times(1)).findById(1L);
    }

    @Test
    public void whenDeleteScore_thenVerifyDeletion() {
        doNothing().when(scoreRepository).deleteById(1L);

        scoreService.deleteScore(1L);

        verify(scoreRepository, times(1)).deleteById(1L);
    }

    @Test
    public void whenUpdateScore_thenReturnUpdatedScore() {
        Score score = new Score();
        score.setId(1L);
        score.setScore(95.0);

        when(scoreRepository.save(score)).thenReturn(score);

        Score updatedScore = scoreService.updateScore(score);

        assertNotNull(updatedScore);
        assertEquals(95.0, updatedScore.getScore());
        verify(scoreRepository, times(1)).save(score);
    }

    @Test
    public void whenSaveScore_thenReturnSavedScore() {
        Score score = new Score();
        score.setScore(85.0);

        when(scoreRepository.save(score)).thenReturn(score);

        Score savedScore = scoreService.saveScore(score);

        assertNotNull(savedScore);
        assertEquals(85.0, savedScore.getScore());
        verify(scoreRepository, times(1)).save(score);
    }
}