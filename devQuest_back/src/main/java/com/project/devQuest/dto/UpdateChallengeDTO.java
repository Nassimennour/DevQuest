package com.project.devQuest.dto;

import com.project.devQuest.model.Difficulty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateChallengeDTO {
    private long id;
    private String title;
    private String description;
    private Difficulty difficulty;
    private long technologyId;
    private int duration;
}
