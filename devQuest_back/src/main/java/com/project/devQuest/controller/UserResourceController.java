package com.project.devQuest.controller;


import com.project.devQuest.dto.ResourceDTO;
import com.project.devQuest.model.Resource;
import com.project.devQuest.service.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/resources")
public class UserResourceController {
    @Autowired
    private ResourceService resourceService;
    private static final Logger logger = LoggerFactory.getLogger(UserResourceController.class);


    @GetMapping("")
    public ResponseEntity<List<Resource>> getAllResources() {
        try {
            List<Resource> resources = resourceService.getAllResources();
            return ResponseEntity.ok(resources);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getResourceById(@PathVariable Long id) {
        Resource resource = resourceService.getResourceById(id);
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/technology/{technologyId}")
    public ResponseEntity<List<Resource>> getResourcesByTechnologyId(@PathVariable Long technologyId) {
        List<Resource> resources = resourceService.getResourcesByTechnologyId(technologyId);
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Resource>> getResourcesByUserId(@PathVariable Long userId) {
        List<Resource> resources = resourceService.getResourcesByUserId(userId);
        return ResponseEntity.ok(resources);
    }

    @PostMapping("")
    public ResponseEntity<Resource> createResource(@RequestBody ResourceDTO resource) {
        try {
            Resource createdResource = resourceService.createResource(resource);
            return ResponseEntity.ok(createdResource);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("")
    public ResponseEntity<Resource> updateResource(@RequestBody ResourceDTO resource) {
        try {
            Resource updatedResource = resourceService.updateResource(resource);
            return ResponseEntity.ok(updatedResource);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
