package com.project.devQuest.service;
import com.project.devQuest.model.QuizzHistory;
import com.project.devQuest.repository.QuizzHistoryRepository;
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
public class QuizzHistoryServiceTest {

    @InjectMocks
    private QuizzHistoryService quizzHistoryService;

    @Mock
    private QuizzHistoryRepository quizzHistoryRepository;

    @Test
    public void whenFindByUserId_thenReturnQuizzHistoryList() {
        QuizzHistory quizzHistory = new QuizzHistory();
        when(quizzHistoryRepository.findByUserId(1L)).thenReturn(Collections.singletonList(quizzHistory));

        List<QuizzHistory> quizzHistoryList = quizzHistoryService.findByUserId(1L);

        assertEquals(1, quizzHistoryList.size());
        verify(quizzHistoryRepository, times(1)).findByUserId(1L);
    }

    @Test
    public void whenExistsByUserId_thenReturnTrue() {
        when(quizzHistoryRepository.existsByUserId(1L)).thenReturn(true);

        boolean exists = quizzHistoryService.existsByUserId(1L);

        assertTrue(exists);
        verify(quizzHistoryRepository, times(1)).existsByUserId(1L);
    }

    @Test
    public void whenFindAll_thenReturnQuizzHistoryList() {
        QuizzHistory quizzHistory = new QuizzHistory();
        when(quizzHistoryRepository.findAll()).thenReturn(Collections.singletonList(quizzHistory));

        List<QuizzHistory> quizzHistoryList = quizzHistoryService.findAll();

        assertEquals(1, quizzHistoryList.size());
        verify(quizzHistoryRepository, times(1)).findAll();
    }

    @Test
    public void whenFindById_thenReturnQuizzHistory() {
        QuizzHistory quizzHistory = new QuizzHistory();
        when(quizzHistoryRepository.findById(1L)).thenReturn(Optional.of(quizzHistory));

        Optional<QuizzHistory> foundQuizzHistory = quizzHistoryService.findById(1L);

        assertTrue(foundQuizzHistory.isPresent());
        verify(quizzHistoryRepository, times(1)).findById(1L);
    }

    @Test
    public void whenUpdateQuizzHistory_thenReturnUpdatedQuizzHistory() {
        QuizzHistory quizzHistory = new QuizzHistory();
        quizzHistory.setId(1L);
        when(quizzHistoryRepository.save(quizzHistory)).thenReturn(quizzHistory);
        when((quizzHistoryRepository.existsById(1L))).thenReturn(true);
        QuizzHistory updatedQuizzHistory = quizzHistoryService.updateQuizzHistory(quizzHistory);

        assertNotNull(updatedQuizzHistory);
        verify(quizzHistoryRepository, times(1)).save(quizzHistory);
    }
}