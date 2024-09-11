package com.project.devQuest.repository;

import com.project.devQuest.dto.TechnologyPopularityDTO;
import com.project.devQuest.model.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Long> {
    List<Technology> findByName(String name);
    List<Technology> findByCategoryId(Long categoryId);

    @Query("SELECT new com.project.devQuest.dto.TechnologyPopularityDTO(t.name, COALESCE(SUM(q.timesTaken + c.timesTaken), 0)) " +
            "FROM Technology t " +
            "LEFT JOIN t.quizzList q " +
            "LEFT JOIN t.codingChallengeList c " +
            "GROUP BY t.name")
    List<TechnologyPopularityDTO> findTechnologyPopularity();
}