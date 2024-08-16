package com.project.devQuest.controller;

import com.project.devQuest.model.Technology;
import com.project.devQuest.service.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/technologies")
public class UserTechnologyController {

    @Autowired
    private TechnologyService technologyService;

    @GetMapping("")
    public ResponseEntity<List<Technology>> getAllTechnologies() {
        return ResponseEntity.ok(technologyService.getAllTechnologies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTechnologyById(@PathVariable Long id) {
        if (!technologyService.existsById(id)) {
            return ResponseEntity.badRequest().body("Technology not found");
        }
        return ResponseEntity.ok(technologyService.getTechnologyById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Technology>> getTechnologiesByName(@PathVariable String name) {
        return ResponseEntity.ok(technologyService.getTechnologiesByName(name));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Technology>> getTechnologiesByCategoryId(@PathVariable Long categoryId) {
        return ResponseEntity.ok(technologyService.getTechnologiesByCategoryId(categoryId));
    }

}
