package com.project.devQuest.repository;

import com.project.devQuest.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
    List<Resource> findByTechnologyId(Long technologyId);
    List<Resource> findByUserId(Long userId);
}
