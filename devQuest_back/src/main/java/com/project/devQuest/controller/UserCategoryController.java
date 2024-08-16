package com.project.devQuest.controller;

import com.project.devQuest.model.Category;
import com.project.devQuest.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/categories")
public class UserCategoryController {
    @Autowired
    private CategoryService categoryService;

    private final static Logger logger = LoggerFactory.getLogger(UserCategoryController.class);

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
}
