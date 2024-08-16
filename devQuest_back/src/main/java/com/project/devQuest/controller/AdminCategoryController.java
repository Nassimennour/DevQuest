package com.project.devQuest.controller;

import com.project.devQuest.dto.CategoryDTO;
import com.project.devQuest.model.Category;
import com.project.devQuest.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/categories")
public class AdminCategoryController {

    @Autowired
    private CategoryService categoryService;
    private final static Logger logger = LoggerFactory.getLogger(AdminCategoryController.class);

    @GetMapping("")
    public ResponseEntity<List<Category>> getAllCategories() {
        logger.info("Received request to get all categories");
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(Long id) {
        logger.info("Received request to get category by id: " + id);
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @GetMapping("/parent/{id}")
    public ResponseEntity<List<Category>> getCategoryByParentCategoryId(Long id) {
        logger.info("Received request to get category by parent category id: " + id);
        return ResponseEntity.ok(categoryService.getCategoryByParentCategoryId(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Category> getCategoryByName(String name) {
        logger.info("Received request to get category by name: " + name);
        return ResponseEntity.ok(categoryService.getCategoryByName(name));
    }

    @PostMapping()
    public ResponseEntity<Category> createCategory(CategoryDTO category) {
        logger.info("Received request to create category: " + category.getName());
        return ResponseEntity.ok(categoryService.createCategory(category));
    }

    @PutMapping("")
    public ResponseEntity<Category> updateCategory(Category category) {
        logger.info("Received request to update category: " + category.getName());
        return ResponseEntity.ok(categoryService.updateCategory(category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(Long id) {
        logger.info("Received request to delete category by id: " + id);
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteAllCategories() {
        logger.info("Received request to delete all categories");
        categoryService.deleteAllCategories();
        return ResponseEntity.ok().build();
    }




}
