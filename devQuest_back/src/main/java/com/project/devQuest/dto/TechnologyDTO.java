package com.project.devQuest.dto;

import com.project.devQuest.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TechnologyDTO {
    private long id;
    private String name;
    private String overview;
    private String logo;
    private long categoryId;
}
