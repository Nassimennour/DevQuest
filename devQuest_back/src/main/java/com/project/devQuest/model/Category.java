package com.project.devQuest.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "category")
    private List<Technology> technologies;
    @ManyToOne
    @JoinColumn(name = "parentCategory_id")
    private Category parentCategory;
    @OneToMany(mappedBy = "parentCategory")
    private List<Category> subCategories;
}
