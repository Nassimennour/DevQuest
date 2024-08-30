package com.project.devQuest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuizzCompletionStatsDTO {
    private String period;
    private long count;

    public QuizzCompletionStatsDTO(String period, long count) {
        this.period = period;
        this.count = count;
    }
}
