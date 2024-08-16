package com.project.devQuest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolutionDTO {
    private String code;
    private long codingChallengeId;
    private long userId;
    private boolean isCorrect;
    private LocalDateTime submissionDate;
}
