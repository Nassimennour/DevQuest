package com.project.devQuest.service;

import com.project.devQuest.dto.CategoryDTO;
import com.project.devQuest.model.Category;
import com.project.devQuest.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    private final static Logger logger = LoggerFactory.getLogger(CategoryService.class);

    public Category getCategoryById(Long id) {
        logger.info("Finding category by id: " + id);
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public Category getCategoryByName(String name) {
        logger.info("Finding category by name: " + name);
        if (categoryRepository.findByName(name).isPresent()){
            return categoryRepository.findByName(name).get();
        } else {
            logger.info("Category not found by name: {}", name);
            throw new RuntimeException("Category not found");
        }
    }

    public List<Category> getAllCategories() {
        logger.info("Finding all categories");
        return categoryRepository.findAll();
    }

    public List<Category> getCategoryByParentCategoryId(Long id) {
        logger.info("Finding category by parent category id: " + id);
        return categoryRepository.findByParentCategoryId(id);
    }

    public Category createCategory(CategoryDTO categoryDTO) {
        logger.info("Creating category: " + categoryDTO.getName());
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setParentCategory(categoryRepository.findById(categoryDTO.getParentCategoryId()).orElse(null));
        return categoryRepository.save(category);
    }

    public Category updateCategory(Category category) {
        logger.info("Updating category: " + category.getName());
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        logger.info("Deleting category by id: " + id);
        categoryRepository.deleteById(id);
    }

    public void deleteAllCategories() {
        logger.info("Deleting all categories");
        categoryRepository.deleteAll();
    }
}
