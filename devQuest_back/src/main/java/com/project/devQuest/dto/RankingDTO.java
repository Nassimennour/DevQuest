package com.project.devQuest.dto;

import com.project.devQuest.model.Ranking;
import com.project.devQuest.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RankingDTO {
    private int score;
    private long userId;
    private int position;
    private int level;

}
