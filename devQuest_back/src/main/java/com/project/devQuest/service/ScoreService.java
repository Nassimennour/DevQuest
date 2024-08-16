package com.project.devQuest.service;

import com.project.devQuest.model.*;
import com.project.devQuest.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuizzRepository quizzRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuizzHistoryRepository quizzHistoryRepository;

    private final static Logger logger = LoggerFactory.getLogger(ScoreService.class);

    public Score computeAndSaveScore(long userId, long quizzId, List<Answer> answers) {
        logger.info("Computing score for user: " + userId + " and quizz: " + quizzId);
        double scoreValue = 0;
        for (Answer answer : answers) {
            Question question = questionRepository.findById(answer.getQuestionId())
                    .orElseThrow(() -> new RuntimeException("Question not found"));
            answer.setQuestion(question);
            if (question.getCorrectAnswer().equals(answer.getAnswer())) {
                answer.setCorrect(true);
                scoreValue++;
            } else {
                answer.setCorrect(false);
            }
        }
        // Take two digits after the decimal point
        scoreValue = Math.round(scoreValue / answers.size() * 100) / 100.0;
        Score scoreEntity = new Score();
        scoreEntity.setUser(userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found")));
        scoreEntity.setQuizz(quizzRepository.findById(quizzId).orElseThrow(() -> new RuntimeException("Quizz not found")));
        scoreEntity.setScore(scoreValue);
        scoreEntity.setAnswers(answers);
        Score savedScore = scoreRepository.save(scoreEntity);
        answers.forEach(answer -> answer.setScore(savedScore));
        answerRepository.saveAll(answers);
        // ADD the Quizz to the user's quizz history
        QuizzHistory quizzHistory = new QuizzHistory();
        quizzHistory.setQuizz(quizzRepository.findById(quizzId).orElseThrow(() -> new RuntimeException("Quizz not found")));
        quizzHistory.setUser(userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found")));
        quizzHistory.setScore(scoreValue);
        quizzHistory.setCompltedAt(LocalDateTime.now());
        return savedScore;
    }

    public List<Score> getScoresByUserId(long userId) {
        logger.info("Getting scores for user: " + userId);
        return scoreRepository.findByUserId(userId);
    }

    public List<Score> getScoresByQuizzId(long quizzId) {
        logger.info("Getting scores for quizz: " + quizzId);
        return scoreRepository.findByQuizzId(quizzId);
    }

    public List<Score> getScoresByQuizzIdAndUserId(long quizzId, long userId) {
        logger.info("Getting scores for user: " + userId + " and quizz: " + quizzId);
        return scoreRepository.findByQuizzIdAndUserId(quizzId, userId);
    }

    public double getAverageScoreByQuizzId(long quizzId) {
        logger.info("Getting average score for quizz: " + quizzId);
        return scoreRepository.findAverageScoreByQuizzId(quizzId);
    }

    public double getAverageScoreByUserId(long userId) {
        logger.info("Getting average score for user: " + userId);
        return scoreRepository.findAverageScoreByUserId(userId);
    }

    public double getAverageScoreByUserIdAndTechnologyId(long userId, long technologyId) {
        logger.info("Getting average score for user: " + userId + " and technology: " + technologyId);
        return scoreRepository.findAverageScoreByUserIdAndTechnologyId(userId, technologyId);
    }

    public double getAverageScoreByUserIdAndCategoryId(long userId, long categoryId) {
        logger.info("Getting average score for user: " + userId + " and category: " + categoryId);
        return scoreRepository.findAverageScoreByUserIdAndCategoryId(userId, categoryId);
    }


    public List<Score> getAllScores() {
        logger.info("Getting all scores");
        return scoreRepository.findAll();
    }

    public Score getScoreById(long id) {
        logger.info("Getting score with id: " + id);
        return scoreRepository.findById(id).orElseThrow(() -> new RuntimeException("Score not found"));
    }

    public void deleteScore(long id) {
        logger.info("Deleting score with id: " + id);
        scoreRepository.deleteById(id);
    }

    public Score updateScore(Score score){
        logger.info("Updating score with id: " + score.getId());
        return scoreRepository.save(score);
    }

    public Score saveScore(Score score){
        logger.info("Saving score");
        return scoreRepository.save(score);
    }

}