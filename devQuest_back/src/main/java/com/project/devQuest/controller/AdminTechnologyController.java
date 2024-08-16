package com.project.devQuest.controller;

import com.project.devQuest.dto.TechnologyDTO;
import com.project.devQuest.model.Technology;
import com.project.devQuest.service.TechnologyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/technologies")
@Slf4j
public class AdminTechnologyController {
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

    @PostMapping("")
    public ResponseEntity<Technology> createTechnology(@RequestBody TechnologyDTO technology) {
        return ResponseEntity.ok(technologyService.createTechnology(technology));
    }

    @PutMapping("")
    public ResponseEntity<?> updateTechnology(@RequestBody Technology technology) {
        if (!technologyService.existsById(technology.getId())) {
            return ResponseEntity.badRequest().body("Technology not found");
        }
        return ResponseEntity.ok(technologyService.updateTechnology(technology));
    }


}
