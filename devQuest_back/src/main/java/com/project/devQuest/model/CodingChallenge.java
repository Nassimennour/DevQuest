package com.project.devQuest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "coding_challenges")
public class CodingChallenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    private Difficulty difficulty;
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;
    @ManyToOne
    @JoinColumn(name = "technology_id")
    private Technology technology;
    private int duration;
    @OneToMany(mappedBy = "codingChallenge", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Solution> solutions;
    @ManyToMany(mappedBy = "codingChallengeHistory", fetch = FetchType.LAZY)
    private List<User> users; // Users who have attempted this challenge
    private long timesTaken = 0;
    private Date creationDate;

}
