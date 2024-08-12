package com.project.devQuest.dto;

import com.project.devQuest.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private long id;
    private String username;
    private String email;
    private String fullname;
    private String bio;
    private String profilePicture;
    private Gender gender;
    private String role; // ADMIN or User
    private boolean isVerified;
    private Date registrationDate;
    private Set<Technology> skills; // Names of technologies
    private List<QuizzHistory> quizzHistory; // Titles of quizzes
    private List<CodingChallenge> codingChallengeHistory; // Titles of coding challenges
    private boolean isDarkMode;
    private List<UserProgress> userProgressList; // His progress in each technology
}