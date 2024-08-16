package com.project.devQuest.repository;

import com.project.devQuest.model.Dashboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DashboardRepository extends JpaRepository<Dashboard, Long> {
    Dashboard findByUserId(Long userId);
}