package com.project.devQuest.service;

import com.project.devQuest.model.Technology;
import com.project.devQuest.repository.TechnologyRepositiry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TechnologyService {
    @Autowired
    private TechnologyRepositiry technologyRepositiry;

    public List<Technology> getAllTechnologies(){
        log.info("Finding all technologies");
        return technologyRepositiry.findAll();
    }

    public Technology createTechnology(Technology technology){
        log.info("Creating technology: {}", technology.getName());
        return technologyRepositiry.save(technology);
    }

    public Technology updateTechnology(Technology technology){
        if (!technologyRepositiry.existsById(technology.getId())) {
            throw new IllegalArgumentException("Technology not found");
        }
        log.info("Updating technology: {}", technology.getName());
        return technologyRepositiry.save(technology);
    }

    public Technology getTechnologyById(Long id){
        log.info("Finding technology by id: {}", id);
        return technologyRepositiry.findById(id).orElseThrow(() -> new IllegalArgumentException("Technology not found"));
    }

    public List<Technology> getTechnologiesByName(String name){
        log.info("Finding technologies by name: {}", name);
        return technologyRepositiry.findByName(name);
    }

    public void deleteTechnology(Long id){
        if (!technologyRepositiry.existsById(id)) {
            throw new IllegalArgumentException("Technology not found");
        }
        log.info("Deleting technology by id: {}", id);
        technologyRepositiry.deleteById(id);
    }

    public List<Technology> getTechnologiesByCategoryId(Long categoryId){
        log.info("Finding technologies by category id: {}", categoryId);
        return technologyRepositiry.findByCategoryId(categoryId);
    }

    public boolean existsById(Long id){
        log.info("Checking if technology exists by id: {}", id);
        return technologyRepositiry.existsById(id);
    }
}
