package com.project.devQuest.controller;

import com.project.devQuest.dto.QuestionDTO;
import com.project.devQuest.model.Question;
import com.project.devQuest.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/questions")
public class AdminQuestionController {
    @Autowired
    private QuestionService questionService;
    private final static Logger logger = LoggerFactory.getLogger(AdminQuestionController.class);

    @GetMapping("/quizz/{quizzId}")
    public ResponseEntity<List<Question>> getQuestionsByQuizzId(@PathVariable Long quizzId) {
        logger.info("Fetching all questions for quizz with id: " + quizzId);
        return ResponseEntity.ok(questionService.getAllQuestions(quizzId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
        logger.info("Fetching question with id: " + id);
        return ResponseEntity.ok(questionService.getQuestionById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        logger.info("Deleting question with id: " + id);
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("")
    public ResponseEntity<Question> createQuestion(@RequestBody QuestionDTO questionDTO) {
        logger.info("Adding question: " + questionDTO.getQuestion());
        return ResponseEntity.ok(questionService.addQuestion(questionDTO));
    }

    @PutMapping("")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question) {
        logger.info("Updating question with id: " + question.getId());
        return ResponseEntity.ok(questionService.updateQuestion(question));
    }
}
