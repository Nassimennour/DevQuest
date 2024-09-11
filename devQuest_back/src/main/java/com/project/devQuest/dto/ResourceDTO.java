package com.project.devQuest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceDTO {
    long id;
    String title;
    String url;
    String description;
    long userId;
    long technologyId;
    String approvalStatus;
}
