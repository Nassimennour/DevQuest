package com.project.devQuest.repository;

import com.project.devQuest.model.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Long> {
    public List<Technology> findByName(String name);
    public List<Technology> findByCategoryId(Long categoryId);

}
