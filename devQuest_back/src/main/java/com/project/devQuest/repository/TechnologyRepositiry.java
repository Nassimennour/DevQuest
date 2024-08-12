package com.project.devQuest.repository;

import com.project.devQuest.model.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TechnologyRepositiry extends JpaRepository<Technology, Long> {
    public List<Technology> findByName(String name);
    public List<Technology> findByCategoryId(Long categoryId);

}
