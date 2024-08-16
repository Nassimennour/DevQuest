package com.project.devQuest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodingChallengeDTO {
    private String title;
    private String description;
    private String difficulty;
    private long creatorId;
    private long technologyId;
    private int duration;
}
