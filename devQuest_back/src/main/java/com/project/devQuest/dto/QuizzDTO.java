package com.project.devQuest.dto;

import com.project.devQuest.model.Difficulty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizzDTO {
    private long id;
    private String title;
    private String overview;
    private Difficulty difficulty;
    private long technologyId;
    private int duration; // In minutes
    private Date creationDate;
    private long creatorId;
}
