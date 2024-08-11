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
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String question;
    @ElementCollection
    @CollectionTable(name = "question_options")
    @Column(name = "option")
    private List<String> options;
    private String correctAnswer;
    private Difficulty difficulty;
    @ManyToOne
    @JoinColumn(name = "quizz_id")
    private Quizz quizz;
}
