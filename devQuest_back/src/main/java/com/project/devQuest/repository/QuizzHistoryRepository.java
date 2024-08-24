package com.project.devQuest.repository;

import com.project.devQuest.dto.QuizzCompletionStatsDTO;
import com.project.devQuest.model.QuizzHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface QuizzHistoryRepository extends JpaRepository<QuizzHistory, Long> {
    List<QuizzHistory> findByUserId(long id);
    boolean existsByUserId(long id);
    @Query("SELECT new com.project.devQuest.dto.QuizzCompletionStatsDTO(FUNCTION('DATE_FORMAT', qh.compltedAt, :format), COUNT(qh)) " +
            "FROM QuizzHistory qh " +
            "WHERE qh.compltedAt >= :startDate " +
            "GROUP BY FUNCTION('DATE_FORMAT', qh.compltedAt, :format)")
    List<QuizzCompletionStatsDTO> findQuizzCompletionStats(LocalDateTime startDate, String format);
    // Get recent quizz history entries
    List<QuizzHistory> findTop10ByOrderByCompltedAtDesc();
    // Get number of quizz history entries by date
    long countByCompltedAt(LocalDateTime date);
}
