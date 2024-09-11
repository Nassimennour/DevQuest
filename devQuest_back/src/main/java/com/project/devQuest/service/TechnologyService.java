package com.project.devQuest.service;

import com.project.devQuest.dto.TechnologyDTO;
import com.project.devQuest.dto.TechnologyPopularityDTO;
import com.project.devQuest.model.Technology;
import com.project.devQuest.repository.CategoryRepository;
import com.project.devQuest.repository.TechnologyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Technology updateTechnology(TechnologyDTO technology){
        log.info("Updating technology: {}", technology.getName());
        Technology existingTechnology = technologyRepository.findById(technology.getId()).orElseThrow(() -> new IllegalArgumentException("Technology not found"));
        existingTechnology.setName(technology.getName());
        existingTechnology.setLogo(technology.getLogo());
        existingTechnology.setOverview(technology.getOverview());
        existingTechnology.setCategory(categoryRepository.findById(technology.getCategoryId()).orElseThrow(() -> new IllegalArgumentException("Category not found")));
        return technologyRepository.save(existingTechnology);
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

    public long count(){
        log.info("Counting the number of technologies");
        return technologyRepository.count();
    }
    // Find most popular technologies
    public List<TechnologyPopularityDTO> getTechnoloyPopularity() {
        log.info("Finding technology popularity");
        List<TechnologyPopularityDTO> technologyPopularityList = new ArrayList<>();
        List<Technology> technologies = technologyRepository.findAll();
        for (Technology technology : technologies) {
            technologyPopularityList.add(new TechnologyPopularityDTO(technology.getName(), technology.getQuizzList().size() + technology.getCodingChallengeList().size() -1));
        }
        technologyPopularityList.sort((t1, t2) -> Math.toIntExact(t1.getPopularityCount() - t2.getPopularityCount()));
        return technologyPopularityList.subList(0, Math.min(3, technologyPopularityList.size()));
    }
}
