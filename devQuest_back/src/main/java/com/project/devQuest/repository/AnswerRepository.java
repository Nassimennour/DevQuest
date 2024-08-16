package com.project.devQuest.repository;

import com.project.devQuest.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByScoreId(Long id);
    Optional<Answer> findByQuestionIdAndScoreId(Long questionId, Long scoreId);
}
