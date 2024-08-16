package com.project.devQuest.service;

import com.project.devQuest.dto.QuestionDTO;
import com.project.devQuest.model.Difficulty;
import com.project.devQuest.model.Question;
import com.project.devQuest.repository.QuestionRepository;
import com.project.devQuest.repository.QuizzRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuizzRepository quizzRepository;
    private final static Logger logger = LoggerFactory.getLogger(QuestionService.class);


    public List<Question> getAllQuestions(Long quizzId) {
        logger.info("Fetching all questions for quizz with id: " + quizzId);
        return questionRepository.findByQuizzId(quizzId);
    }

    public Question getQuestionById(Long id) {
        logger.info("Fetching question with id: " + id);
        return questionRepository.findById(id).orElseThrow(() -> new RuntimeException("Question not found"));
    }

    public Question addQuestion(QuestionDTO questiondto) {
        logger.info("Adding question: " + questiondto.getQuestion());
        Question question = new Question();
        question.setQuestion(questiondto.getQuestion());
        question.setOptions(Arrays.asList(questiondto.getOptions()));
        question.setCorrectAnswer(questiondto.getCorrectAnswer());
        question.setDifficulty(Difficulty.valueOf(questiondto.getDifficulty()));
        question.setQuizz(quizzRepository.findById(questiondto.getQuizzId()).orElseThrow(() -> new RuntimeException("Quizz not found")));
        return questionRepository.save(question);
    }

    public Question updateQuestion(Question question) {
        logger.info("Updating question with id: " + question.getId());
        return questionRepository.save(question);
    }


    public void deleteQuestion(Long id) {
        logger.info("Deleting question with id: " + id);
        questionRepository.deleteById(id);
    }

    public void deleteAllQuestions(Long quizzId) {
        logger.info("Deleting all questions for quizz with id: " + quizzId);
        questionRepository.deleteAll(getAllQuestions(quizzId));
    }

    public List<Question> getQuestionsByQuizzId(Long quizzId) {
        logger.info("Fetching all questions for quizz with id: " + quizzId);
        return questionRepository.findByQuizzId(quizzId);
    }

}
