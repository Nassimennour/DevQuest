package com.project.devQuest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "quizzes")
public class Quizz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String overview;
    private Difficulty difficulty;
    @OneToMany(mappedBy = "quizz", cascade = CascadeType.ALL)
    private List<Question> questions;
    @ManyToOne
    @JoinColumn(name = "technology_id")
    private Technology technology;
    private int duration; // In minutes
    private Date creationDate;
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;
    @OneToMany(mappedBy = "quizz", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Score> scores;
    private long timesTaken = 0;
    private double averageScore = 0;
}
