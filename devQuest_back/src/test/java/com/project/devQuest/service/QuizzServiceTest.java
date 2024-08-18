package com.project.devQuest.service;

import com.project.devQuest.dto.QuizzDTO;
import com.project.devQuest.model.Difficulty;
import com.project.devQuest.model.Quizz;
import com.project.devQuest.model.Technology;
import com.project.devQuest.model.User;
import com.project.devQuest.repository.QuizzRepository;
import com.project.devQuest.repository.TechnologyRepository;
import com.project.devQuest.repository.UserRepository;
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
public class QuizzServiceTest {

    @InjectMocks
    private QuizzService quizzService;

    @Mock
    private QuizzRepository quizzRepository;

    @Mock
    private TechnologyRepository technologyRepository;

    @Mock
    private UserRepository userRepository;

    @Test
    public void whenFindById_thenReturnQuizz() {
        Quizz quizz = new Quizz();
        when(quizzRepository.findById(1L)).thenReturn(Optional.of(quizz));

        Optional<Quizz> foundQuizz = quizzService.findById(1L);

        assertTrue(foundQuizz.isPresent());
        verify(quizzRepository, times(1)).findById(1L);
    }

    @Test
    public void whenExistsById_thenReturnTrue() {
        when(quizzRepository.existsById(1L)).thenReturn(true);

        boolean exists = quizzService.existsById(1L);

        assertTrue(exists);
        verify(quizzRepository, times(1)).existsById(1L);
    }

    @Test
    public void whenGetAllQuizzes_thenReturnQuizzList() {
        Quizz quizz = new Quizz();
        when(quizzRepository.findAll()).thenReturn(Collections.singletonList(quizz));

        List<Quizz> quizzList = quizzService.getAllQuizzes();

        assertEquals(1, quizzList.size());
        verify(quizzRepository, times(1)).findAll();
    }

    @Test
    public void whenCreateQuizz_thenReturnQuizz() {
        QuizzDTO quizzDTO = new QuizzDTO();
        quizzDTO.setTitle("Test Quizz");
        quizzDTO.setTechnologyId(1L);
        quizzDTO.setCreatorId(1L);
        quizzDTO.setDifficulty(Difficulty.EASY);

        Technology technology = new Technology();
        User user = new User();
        Quizz quizz = new Quizz();
        quizz.setTitle("Test Quizz");

        when(technologyRepository.findById(1L)).thenReturn(Optional.of(technology));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(quizzRepository.save(any(Quizz.class))).thenReturn(quizz);

        Quizz createdQuizz = quizzService.createQuizz(quizzDTO);

        assertNotNull(createdQuizz);
        assertEquals("Test Quizz", createdQuizz.getTitle());
        verify(technologyRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).findById(1L);
        verify(quizzRepository, times(1)).save(any(Quizz.class));
    }

    @Test
    public void whenUpdateQuizz_thenReturnUpdatedQuizz() {
        Quizz quizz = new Quizz();
        quizz.setId(1L);
        quizz.setTitle("Updated Quizz");

        when(quizzRepository.existsById(1L)).thenReturn(true);
        when(quizzRepository.save(quizz)).thenReturn(quizz);

        Quizz updatedQuizz = quizzService.updateQuizz(quizz);

        assertNotNull(updatedQuizz);
        assertEquals("Updated Quizz", updatedQuizz.getTitle());
        verify(quizzRepository, times(1)).existsById(1L);
        verify(quizzRepository, times(1)).save(quizz);
    }

    @Test
    public void whenDeleteQuizz_thenVerifyDeletion() {
        doNothing().when(quizzRepository).deleteById(1L);

        quizzService.deleteQuizz(1L);

        verify(quizzRepository, times(1)).deleteById(1L);
    }

    @Test
    public void whenDeleteAllQuizzes_thenVerifyDeletion() {
        doNothing().when(quizzRepository).deleteAll();

        quizzService.deleteAllQuizzes();

        verify(quizzRepository, times(1)).deleteAll();
    }

    @Test
    public void whenGetQuizzesByTechnologyId_thenReturnQuizzList() {
        Quizz quizz = new Quizz();
        when(quizzRepository.findByTechnologyId(1L)).thenReturn(Collections.singletonList(quizz));

        List<Quizz> quizzList = quizzService.getQuizzesByTechnologyId(1L);

        assertEquals(1, quizzList.size());
        verify(quizzRepository, times(1)).findByTechnologyId(1L);
    }

    @Test
    public void whenGetQuizzesByCreatorId_thenReturnQuizzList() {
        Quizz quizz = new Quizz();
        when(quizzRepository.findByCreatorId(1L)).thenReturn(Collections.singletonList(quizz));

        List<Quizz> quizzList = quizzService.getQuizzesByCreatorId(1L);

        assertEquals(1, quizzList.size());
        verify(quizzRepository, times(1)).findByCreatorId(1L);
    }

    @Test
    public void whenGetQuizzesByTitle_thenReturnQuizzList() {
        Quizz quizz = new Quizz();
        when(quizzRepository.findByTitle("Test Quizz")).thenReturn(Collections.singletonList(quizz));

        List<Quizz> quizzList = quizzService.getQuizzesByTitle("Test Quizz");

        assertEquals(1, quizzList.size());
        verify(quizzRepository, times(1)).findByTitle("Test Quizz");
    }

    @Test
    public void whenGetQuizzesByDifficulty_thenReturnQuizzList() {
        Quizz quizz = new Quizz();
        quizz.setDifficulty(Difficulty.EASY);
        when(quizzRepository.findAll()).thenReturn(Collections.singletonList(quizz));

        List<Quizz> quizzList = quizzService.getQuizzesByDifficulty(Difficulty.EASY);

        assertEquals(1, quizzList.size());
        assertEquals(Difficulty.EASY, quizzList.get(0).getDifficulty());
        verify(quizzRepository, times(1)).findAll();
    }
}