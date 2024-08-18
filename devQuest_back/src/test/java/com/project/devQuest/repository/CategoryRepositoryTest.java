package com.project.devQuest.repository;

import com.project.devQuest.model.Category;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private EntityManager entityManager;


    @Test
    public void whenFindByName_thenReturnCategory() {
        // Given
        Category category = new Category();
        category.setName("Java");
        entityManager.persist(category);
        Category category2 = new Category();
        category2.setName("Python");
        entityManager.persist(category2);
        entityManager.flush();
        // When
        Optional<Category> foundCategoriy = categoryRepository.findByName(category.getName());
        // Then
        assertNotNull(foundCategoriy);
        assertEquals(foundCategoriy.get().getName(), "Java");
    }

    @Test
    public void whenFindByParentCategoryId_thenReturnCategory() {
        // Given
        Category parentCategory = new Category();
        parentCategory.setName("Backend");
        entityManager.persist(parentCategory);
        entityManager.flush();

        Category category = new Category();
        category.setName("Java");
        category.setParentCategory(parentCategory);
        entityManager.persist(category);

        Category category2 = new Category();
        category2.setName("Python");
        category2.setParentCategory(parentCategory);
        entityManager.persist(category2);
        entityManager.flush();
        // When
        List<Category> foundCategory = categoryRepository.findByParentCategoryId(parentCategory.getId());
        // Then
        assertTrue(foundCategory.size() == 2);
        for (Category cat : foundCategory) {
            assertEquals(cat.getParentCategory().getId(), parentCategory.getId());
        }
    }
}
