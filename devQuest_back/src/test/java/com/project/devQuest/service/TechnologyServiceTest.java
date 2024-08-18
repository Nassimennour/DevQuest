package com.project.devQuest.service;

import com.project.devQuest.dto.TechnologyDTO;
import com.project.devQuest.model.Technology;
import com.project.devQuest.model.Category;
import com.project.devQuest.repository.CategoryRepository;
import com.project.devQuest.repository.TechnologyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TechnologyServiceTest {

    @InjectMocks
    private TechnologyService technologyService;

    @Mock
    private TechnologyRepository technologyRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    public void whenGetAllTechnologies_thenReturnTechnologyList() {
        Technology technology = new Technology();
        when(technologyRepository.findAll()).thenReturn(Collections.singletonList(technology));

        List<Technology> technologyList = technologyService.getAllTechnologies();

        assertEquals(1, technologyList.size());
        verify(technologyRepository, times(1)).findAll();
    }

    @Test
    public void whenCreateTechnology_thenReturnSavedTechnology() {
        TechnologyDTO technologyDTO = new TechnologyDTO();
        technologyDTO.setName("Java");
        technologyDTO.setLogo("java-logo.png");
        technologyDTO.setOverview("Java Overview");
        technologyDTO.setCategoryId(1L);

        Category category = new Category();
        Technology technology = new Technology();
        technology.setName("Java");
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(technologyRepository.save(any(Technology.class))).thenReturn(technology);

        Technology savedTechnology = technologyService.createTechnology(technologyDTO);

        assertNotNull(savedTechnology);
        assertEquals("Java", savedTechnology.getName());
        verify(categoryRepository, times(1)).findById(1L);
        verify(technologyRepository, times(1)).save(any(Technology.class));
    }
}
