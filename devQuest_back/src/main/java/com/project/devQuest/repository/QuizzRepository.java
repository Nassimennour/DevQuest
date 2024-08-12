package com.project.devQuest.repository;

import com.project.devQuest.model.Quizz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizzRepository extends JpaRepository<Quizz, Long> {
    public List<Quizz> findByTechnologyId(Long technologyId);
    public List<Quizz> findByCreatorId(Long creatorId);
    public List<Quizz> findByTitle(String title);
}
