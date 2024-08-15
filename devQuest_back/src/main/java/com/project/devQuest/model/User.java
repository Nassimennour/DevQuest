package com.project.devQuest.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
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
    @Valid

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    @NotNull(message = "Username is required")
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;
    @Column(unique = true)
    @NotNull(message = "Email is required")
    @NotBlank(message = "Email is required")
    @Email(message = "Email is invalid")
    private String email;
    private String fullname;
    @NotNull(message = "Password is required")
    @NotBlank(message = "Password is required")
    private String password;
    private String bio;
    private String profilePicture;
    private Gender gender;
    private Date birthDate;
    private String role; // ADMIN or USER
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", fullname='" + fullname + '\'' +
                ", bio='" + bio + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", isDarkMode=" + isDarkMode +
                '}';
    }
}
