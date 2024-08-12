package com.project.devQuest.repository;

import com.project.devQuest.model.QuizzHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizzHistoryRepository extends JpaRepository<QuizzHistory, Long> {
    public List<QuizzHistory> findByUserId(long id);
    public boolean existsByUserId(long id);

}
