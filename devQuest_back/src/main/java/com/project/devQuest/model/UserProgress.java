package com.project.devQuest.model;

import jakarta.persistence.*;
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
    private int completedChallenges;
    private int completedQuizzes;
    private float progressPercentage;
    private int totalScore;
    private Date lastActivityDate;
    @OneToOne
    @JoinColumn(name = "last_quizz_id", referencedColumnName = "id")
    private Quizz lastQuizz;
    @OneToOne
    @JoinColumn(name = "last_codingChallenge_id", referencedColumnName = "id")
    private CodingChallenge lastCodingChallenge;
}
