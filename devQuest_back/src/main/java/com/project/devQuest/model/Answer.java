package com.project.devQuest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="answers")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Transient
    private long questionId;
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
    private String answer;
    private boolean isCorrect;
    @ManyToOne
    @JoinColumn(name = "score_id")
    @JsonIgnore
    private Score score;
}
