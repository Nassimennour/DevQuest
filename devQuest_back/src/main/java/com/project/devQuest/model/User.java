package com.project.devQuest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "app_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String email;
    private String fullname;
    private String password;
    private String bio;
    private String profilePicture;
    private Gender gender;
    private Date birthDate;
    private String role; // ADMIN or User
    private boolean isVerified;
    private Date registrationDate;
    @ManyToMany
    @JoinTable(name = "user_skills",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "technology_id"))
    Set<Technology> skills;
    @OneToMany(mappedBy = "user")
    List<QuizzHistory> quizzHistory;
    @ManyToMany
    @JoinTable(name = "user_codingChallenges",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "codingChallenge_id"))
    List<CodingChallenge> codingChallengeHistory;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private Ranking ranking;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private Dashboard dashboard;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Score> scores; // scores in quizzes
    private boolean isDarkMode;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserProgress> userProgressList; // His progress in each technology
}
