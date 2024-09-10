package com.project.devQuest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSolutionDTO {
    private long id;
    private String code;
    private boolean isCorrect;
    private LocalDateTime submissionDate;
}
