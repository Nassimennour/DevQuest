package com.project.devQuest.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "solutions")
public class Solution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 2000)
    private String code;
    @ManyToOne
    @JoinColumn(name = "codingChallenge_id")
    private CodingChallenge codingChallenge;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIdentityReference(alwaysAsId = false)
    private User user; // Submitted by
    private boolean isCorrect;
    private LocalDateTime submissionDate;
}
