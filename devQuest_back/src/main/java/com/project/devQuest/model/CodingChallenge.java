package com.project.devQuest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CodingChallenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    @Column(length = 2000)
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
    @JsonIgnore
    private List<Solution> solutions;
    @ManyToMany(mappedBy = "codingChallengeHistory", fetch = FetchType.LAZY)
    @JsonIgnore
    @JsonBackReference
    private List<User> users; // Users who have attempted this challenge
    private long timesTaken = 0;
    private Date creationDate;

}
