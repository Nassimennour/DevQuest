package com.project.devQuest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_progresses")
public class UserProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "technology_id")
    private Technology technology;
    @Min(value = 0, message = "Completed challenges must be greater than or equal to 0")
    private int completedChallenges = 0;
    @Min(value = 0, message = "Completed quizzes must be greater than or equal to 0")
    private int completedQuizzes = 0;
    private double progressPercentage = 0;
    private int totalScore = 0;
    private Date lastActivityDate;
    @OneToOne
    @JoinColumn(name = "last_quizz_id", referencedColumnName = "id", nullable = true)
    private Quizz lastQuizz;
    @OneToOne
    @JoinColumn(name = "last_codingChallenge_id", referencedColumnName = "id", nullable = true)
    private CodingChallenge lastCodingChallenge;
}
