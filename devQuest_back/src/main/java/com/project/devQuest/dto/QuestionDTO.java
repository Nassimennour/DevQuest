package com.project.devQuest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {
    private long id;
    private String question;
    private String[] options;
    private String correctAnswer;
    private String difficulty;
    private long quizzId;
}
