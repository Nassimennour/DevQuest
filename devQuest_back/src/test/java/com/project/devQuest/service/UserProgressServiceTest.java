package com.project.devQuest.service;
import com.project.devQuest.model.UserProgress;
import com.project.devQuest.model.Technology;
import com.project.devQuest.repository.UserProgressRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserProgressServiceTest {

    @InjectMocks
    private UserProgressService userProgressService;

    @Mock
    private UserProgressRepository userProgressRepository;

    @Test
    public void whenGetAllUserProgress_thenReturnUserProgressList() {
        UserProgress userProgress = new UserProgress();
        when(userProgressRepository.findAll()).thenReturn(Collections.singletonList(userProgress));

        List<UserProgress> userProgressList = userProgressService.getAllUserProgress();

        assertEquals(1, userProgressList.size());
        verify(userProgressRepository, times(1)).findAll();
    }

    @Test
    public void whenGetUserProgressById_thenReturnUserProgress() {
        UserProgress userProgress = new UserProgress();
        when(userProgressRepository.findById(1L)).thenReturn(Optional.of(userProgress));

        UserProgress foundUserProgress = userProgressService.getUserProgressById(1L);

        assertNotNull(foundUserProgress);
        verify(userProgressRepository, times(1)).findById(1L);
    }

    @Test
    public void whenGetUserProgressByUserId_thenReturnUserProgressList() {
        UserProgress userProgress = new UserProgress();
        when(userProgressRepository.findByUserId(1L)).thenReturn(Collections.singletonList(userProgress));

        List<UserProgress> userProgressList = userProgressService.getUserProgressByUserId(1L);

        assertEquals(1, userProgressList.size());
        verify(userProgressRepository, times(1)).findByUserId(1L);
    }

    @Test
    public void whenGetUserProgressByUserIdAndTechnologyId_thenReturnUserProgress() {
        UserProgress userProgress = new UserProgress();
        when(userProgressRepository.findByUserIdAndTechnologyId(1L, 1L)).thenReturn(Optional.of(userProgress));

        UserProgress foundUserProgress = userProgressService.getUserProgressByUserIdAndTechnologyId(1L, 1L);

        assertNotNull(foundUserProgress);
        verify(userProgressRepository, times(1)).findByUserIdAndTechnologyId(1L, 1L);
    }

    @Test
    public void whenGetUserProgressByTechnologyIdOrderByProgressPercentageDesc_thenReturnUserProgressList() {
        UserProgress userProgress = new UserProgress();
        when(userProgressRepository.findByTechnologyIdOrderByProgressPercentageDesc(1L)).thenReturn(Collections.singletonList(userProgress));

        List<UserProgress> userProgressList = userProgressService.getUserProgressByTechnologyIdOrderByProgressPercentageDesc(1L);

        assertEquals(1, userProgressList.size());
        verify(userProgressRepository, times(1)).findByTechnologyIdOrderByProgressPercentageDesc(1L);
    }

    @Test
    public void whenGetUserProgressByUserIdAndLastActivityDateAfter_thenReturnUserProgressList() {
        UserProgress userProgress = new UserProgress();
        Date lastActivityDate = new Date();
        when(userProgressRepository.findByUserIdAndLastActivityDateAfter(1L, lastActivityDate)).thenReturn(Collections.singletonList(userProgress));

        List<UserProgress> userProgressList = userProgressService.getUserProgressByUserIdAndLastActivityDateAfter(1L, lastActivityDate);

        assertEquals(1, userProgressList.size());
        verify(userProgressRepository, times(1)).findByUserIdAndLastActivityDateAfter(1L, lastActivityDate);
    }

    @Test
    public void whenGetUserProgressByUserIdOrderByProgressPercentageDesc_thenReturnUserProgressList() {
        UserProgress userProgress = new UserProgress();
        when(userProgressRepository.findByUserIdOrderByProgressPercentageDesc(1L)).thenReturn(Collections.singletonList(userProgress));

        List<UserProgress> userProgressList = userProgressService.getUserProgressByUserIdOrderByProgressPercentageDesc(1L);

        assertEquals(1, userProgressList.size());
        verify(userProgressRepository, times(1)).findByUserIdOrderByProgressPercentageDesc(1L);
    }

    @Test
    public void whenDeleteUserProgress_thenVerifyDeletion() {
        doNothing().when(userProgressRepository).deleteById(1L);

        userProgressService.DeleteUserProgress(1L);

        verify(userProgressRepository, times(1)).deleteById(1L);
    }
}