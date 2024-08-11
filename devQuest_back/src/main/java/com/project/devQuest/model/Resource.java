package com.project.devQuest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String url;
    private String description;
    @ManyToOne
    @JoinColumn(name = "suggestor_id")
    private User user; // Suggested by
    @ManyToOne
    @JoinColumn(name = "technology_id")
    private Technology technology;
    private Status approvalStatus;
}
