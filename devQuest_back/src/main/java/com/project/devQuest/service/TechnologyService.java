package com.project.devQuest.service;

import com.project.devQuest.dto.TechnologyDTO;
import com.project.devQuest.model.Technology;
import com.project.devQuest.repository.CategoryRepository;
import com.project.devQuest.repository.TechnologyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TechnologyService {
    @Autowired
    private TechnologyRepository technologyRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Technology> getAllTechnologies(){
        log.info("Finding all technologies");
        return technologyRepository.findAll();
    }

    public Technology createTechnology(TechnologyDTO technologyDTO){
        log.info("Creating technology: {}", technologyDTO.getName());
        Technology technology = new Technology();
        technology.setName(technologyDTO.getName());
        technology.setLogo(technologyDTO.getLogo());
        technology.setOverview(technologyDTO.getOverview());
        technology.setCategory(categoryRepository.findById(technologyDTO.getCategoryId()).orElseThrow(() -> new IllegalArgumentException("Category not found")));
        return technologyRepository.save(technology);
    }

    public Technology updateTechnology(Technology technology){
        if (!technologyRepository.existsById(technology.getId())) {
            throw new IllegalArgumentException("Technology not found");
        }
        log.info("Updating technology: {}", technology.getName());
        return technologyRepository.save(technology);
    }

    public Technology getTechnologyById(Long id){
        log.info("Finding technology by id: {}", id);
        return technologyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Technology not found"));
    }

    public List<Technology> getTechnologiesByName(String name){
        log.info("Finding technologies by name: {}", name);
        return technologyRepository.findByName(name);
    }

    public void deleteTechnology(Long id){
        if (!technologyRepository.existsById(id)) {
            throw new IllegalArgumentException("Technology not found");
        }
        log.info("Deleting technology by id: {}", id);
        technologyRepository.deleteById(id);
    }

    public List<Technology> getTechnologiesByCategoryId(Long categoryId){
        log.info("Finding technologies by category id: {}", categoryId);
        return technologyRepository.findByCategoryId(categoryId);
    }

    public boolean existsById(Long id){
        log.info("Checking if technology exists by id: {}", id);
        return technologyRepository.existsById(id);
    }
}
