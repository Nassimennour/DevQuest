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
@Table(name = "solutions")
public class Solution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String code;
    @ManyToOne
    @JoinColumn(name = "codingChallenge_id")
    private CodingChallenge codingChallenge;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // Submitted by
    private boolean isCorrect;
    private Date submissionDate;
}
