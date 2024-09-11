package com.project.devQuest.service;

import com.project.devQuest.dto.ResourceDTO;
import com.project.devQuest.model.Resource;
import com.project.devQuest.model.Status;
import com.project.devQuest.repository.ResourceRepository;
import com.project.devQuest.repository.TechnologyRepository;
import com.project.devQuest.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TechnologyRepository technologyRepository;
    private static final Logger logger = LoggerFactory.getLogger(ResourceService.class);

    public Resource getResourceById(Long id) {
        logger.info("Finding resource by id: {}", id);
        return resourceRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Resource not found"));
    }

    public List<Resource> getAllResources() {
        logger.info("Finding all resources");
        return resourceRepository.findAll();
    }

    public List<Resource> getResourcesByTechnologyId(Long technologyId) {
        logger.info("Finding resources by technology id: {}", technologyId);
        return resourceRepository.findByTechnologyId(technologyId);
    }

    public List<Resource> getResourcesByUserId(Long userId) {
        logger.info("Finding resources by user id: {}", userId);
        return resourceRepository.findByUserId(userId);
    }

    public Resource createResource(ResourceDTO resourceDTO) {
        logger.info("Creating resource: {}", resourceDTO.getTitle());
        Resource resource = new Resource();
        resource.setUrl(resourceDTO.getUrl());
        resource.setTitle(resourceDTO.getTitle());
        resource.setDescription(resourceDTO.getDescription());
        resource.setUser(userRepository.findById(resourceDTO.getUserId()).orElseThrow(() -> new IllegalArgumentException("User not found")));
        resource.setTechnology(technologyRepository.findById(resourceDTO.getTechnologyId()).orElseThrow(() -> new IllegalArgumentException("Technology not found")));
        resource.setApprovalStatus(Status.valueOf(resourceDTO.getApprovalStatus()));
        return resourceRepository.save(resource);
    }

    public Resource updateResource(ResourceDTO resourceDTO) {
        logger.info("Updating resource: {}", resourceDTO.getTitle());
        Resource resource = resourceRepository.findById(resourceDTO.getId()).orElseThrow(() -> new IllegalArgumentException("Resource not found"));
        resource.setUrl(resourceDTO.getUrl());
        resource.setTitle(resourceDTO.getTitle());
        resource.setDescription(resourceDTO.getDescription());
        resource.setUser(userRepository.findById(resourceDTO.getUserId()).orElseThrow(() -> new IllegalArgumentException("User not found")));
        resource.setTechnology(technologyRepository.findById(resourceDTO.getTechnologyId()).orElseThrow(() -> new IllegalArgumentException("Technology not found")));
        resource.setApprovalStatus(Status.valueOf(resourceDTO.getApprovalStatus()));
        return resourceRepository.save(resource);
    }

    public void deleteResource(Long id) {
        logger.info("Deleting resource by id: {}", id);
        resourceRepository.deleteById(id);
    }
}
