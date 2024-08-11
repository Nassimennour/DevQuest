package com.project.devQuest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dashboards")
public class Dashboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    private float progressPrecentage;
    @ManyToMany
    @JoinTable(
            name = "dashboard_suggested_quizzes",
            joinColumns = @JoinColumn(name = "dashboard_id"),
            inverseJoinColumns = @JoinColumn(name = "quizz_id")
    )
    private List<Quizz> suggestedQuizzes;
    @ManyToMany
    @JoinTable(
            name = "dashboard_suggested_codingChallenges",
            joinColumns = @JoinColumn(name = "dashboard_id"),
            inverseJoinColumns = @JoinColumn(name = "codingChallenge_id")
    )
    private List<CodingChallenge> suggestedCodingChallenges;
}
